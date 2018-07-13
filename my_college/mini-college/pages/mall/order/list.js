var sliderWidth = 96;
var app = getApp();
var layer = require('../../../utils/layer.js');
var util = require('../../../utils/utils.js');
var dic = require('../../../utils/dictionary.js');
var wx_api = require('../../../api/wx/api.js');
var orderController = require('../../../api/mall/orderController.js');


Page({
  data: {
    isShowLoading: false,
    hasMore: true,
    page: 1,
    orders: [],
    willCancelOrderId: 0,
    tabs: ["待付款", "待发货", "待收货", '售后服务'],
    activeIndex: 0,
    sliderOffset: 0,
    sliderLeft: 0,
    order_no: [],
    totalAmount: 0,
    cancelReason: dic.cancelReason,
    reason: '',
    isShowCancelOrderPopup: false,
    proHeight: 0,
    addressWidth: 0
  },
  onLoad: function (options) {
    var that = this;
    var activeIndex = options.activeIndex ? options.activeIndex : 0

    var res = app.getSystemInfo();
    that.setData({
      sliderLeft: (res.windowWidth / that.data.tabs.length - sliderWidth) / 2,
      sliderOffset: res.windowWidth / that.data.tabs.length * activeIndex,
      activeIndex: activeIndex,
      proHeight: ((res.windowWidth - 20) * 0.62) / 3,
      addressWidth: res.windowWidth - 20 - 20
    });

    app.getUserInfoSimple(function (userInfo) {
      that.getList();
    })
  },
  tabClick: function (e) {
    var that = this;
    console.log(e.currentTarget.id);
    this.setData({
      sliderOffset: e.currentTarget.offsetLeft,
      activeIndex: e.currentTarget.id,
      page: 1,
      hasMore: true,
      order_no: []
    });
    that.getList();
  },
  getList: function () {
    var that = this;
    if (!that.data.hasMore) {
      return;
    }
    that.setData({
      isShowLoading: true
    });
    orderController.getList(that.data.activeIndex, that.data.page, function (res) {

      //更新数据
      var hasMore = !(that.data.page > 1 && res.data.data.length <= 0)
      var orders = that.data.page == 1 ? [] : that.data.orders;
      for (var i = 0; i < res.data.data.length; i++) {
        res.data.data[i].addtime = util.formatTimestamp(res.data.data[i].addtime);
        orders.push(res.data.data[i]);
      }
      that.setData({
        orders: orders,
        hasMore: hasMore,
        isShowLoading: false
      });
    });
  },
  showConfirmPopup: function (e) {
    var that = this;
    var orderid = e.currentTarget.dataset.orderid
    if (orderid) {
      that.data.willCancelOrderId = orderid;
    }
    that.setData({
      isShowCancelOrderPopup: !that.data.isShowCancelOrderPopup
    });
  },

  //取消订单
  cancel: function (e) {
    var that = this;
    var orderid = that.data.willCancelOrderId
    if (!that.data.reason) {
      layer.msg("请选择取消订单的原因");
      return;
    }
    orderController.setStatus(orderid, -1, that.data.reason, function (res) {
      that.setData({
        page: 1,
        hasMore: true,
        isShowCancelOrderPopup: false
      });
      that.getList();
    });
  },
  delete: function (e) {
    var that = this;
    var orderid = e.currentTarget.dataset.orderid;
    layer.confirm('是否删除？', function (res) {
      if (res.confirm) {
        orderController.setStatus(orderid, -2, '', function (res) {
          that.setData({
            page: 1,
            hasMore: true,
          });
          that.getList();
        });
      }
    })

  },
  callPay: function (e) {
    var that = this;
    var orderid = e.currentTarget.dataset.orderid;
    var formId = e.detail.formId;
    console.log(formId);
    orderController.getPaySignature(1, orderid, formId, function (res) {
      var data = res.data.data;
      console.log(data)
      var timeStamp = data.timeStamp
      var nonce_str = data.nonceStr;
      var packageStr = data.package;
      var sign = data.sign
      wx_api.requestPayment(timeStamp, nonce_str, packageStr, sign, function (res) {
        layer.msg("付款成功", function () {
          that.setData({
            page: 1,
            hasMore: true,
          });
          that.getList();
        });
      });
    });
  },
  mergeSubmit: function () {
    var that = this;
    if (!that.data.order_no || that.data.order_no.length <= 0) {
      layer.msg("至少选择一个订单");
      return;
    }
    orderController.getPaySignature(1, that.data.order_no.join(','),'', function (res) {
      var data = res.data.data;
      console.log(data)
      var nonce_str = data.nonceStr;
      var sign = data.sign
      var packageStr = data.package;
      var timeStamp = data.timeStamp;
      wx_api.requestPayment(timeStamp, nonce_str, packageStr, sign, function (res) {
        layer.msg("付款成功", function () {
          that.setData({
            page: 1,
            hasMore: true,
          });
          that.getList();
        });
      });
    });
  },
  //拨打电话
  callTel: function (e) {
    var that = this;
    var tel = e.currentTarget.dataset.tel;
    wx_api.makePhoneCall(tel)
  },
  
  //退款申请
  redirectRefund: function (e) {
    var that = this;
    var orderid = e.currentTarget.dataset.orderid;
    var total = e.currentTarget.dataset.total;
    wx.navigateTo({
      url: '/pages/mall/order/refund?orderid=' + orderid + "&total=" + total,
    })
  },
  confirmFinish: function (e) {
    var that = this;
    var orderid = e.currentTarget.dataset.orderid

    layer.confirm('确认已收到货物？', function (res) {
      if (res.confirm) {
        orderController.setStatus(orderid, 10, '', function (res) {
          that.setData({
            page: 1,
            activeIndex: 3,
            hasMore: true,
          });
          that.getList();
        });
      }
    })

  },
  //撤销申请
  cancelRefund: function (e) {
    var that = this;
    var orderid = e.currentTarget.dataset.orderid;
    layer.confirm('确认撤销申请？', function (res) {
      if (res.confirm) {
        orderController.cancelRefund(orderid, function (res) {
          if (res.data.data) {
            var status = res.data.data;
            if (status > 0 && status < 3) {
              that.setData({
                page: 1,
                hasMore: true,
                activeIndex: status
              });
              that.getList();
              return;
            }
          }
          that.setData({
            page: 1,
            hasMore: true,
          });
          that.getList();
        });
      }
    })

  },

  //跳转查看物流信息
  redirectLogistics: function (e) {
    var that = this;
    var orderid = e.currentTarget.dataset.orderid;
    wx.navigateTo({
      url: '/pages/mall/order/logistics?orderid=' + orderid,
    })
  },
  //选择订单
  selectOrder: function (e) {

    var that = this;
    var orderid = e.currentTarget.dataset.orderid;
    var orders = that.data.orders;

    for (var i = 0; i < orders.length; i++) {
      if (orders[i].id == orderid) {
        orders[i].checked = orders[i].checked ? "" : "checked";
        if (orders[i].checked) {
          that.data.totalAmount = parseFloat(that.data.totalAmount) + parseFloat(orders[i].order_amount);
          that.data.order_no.push(orderid);
        } else {
          that.data.totalAmount = parseFloat(that.data.totalAmount) - parseFloat(orders[i].order_amount);
          util.removeByValue(that.data.order_no, orderid);
        }
        break;
      }
    }
    console.log(that.data.order_no);
    that.setData({
      orders: orders,
      order_no: that.data.order_no,
      totalAmount: parseFloat(that.data.totalAmount).toFixed(2)
    });
  },
  cancelReasonChange: function (e) {
    var that = this;
    that.data.reason = e.detail.value;
  },
  //下拉刷新
  onPullDownRefresh: function () {
    var that=this;
    wx.stopPullDownRefresh();
    that.setData({
      page: 1,
      hasMore: true,
    });
    that.getList();
  },
  //上拉到底部加载新数据
  onReachBottom: function () {
    var that = this;
    that.setData({
      page: ++that.data.page
    });
    that.getList();
  },
})