var app = getApp();
var wx_api = require('../../../api/wx/api.js');
var layer = require('../../../utils/layer.js');
var orderController = require('../../../api/mall/orderController.js');
var addressController = require('../../../api/mall/addressController.js');



Page({
  data: {
    addressInfo: null,
    orderInfo: null,
    totalAmount: 0,
    windowWidth: 0,
    isShowSuccessTips: false,
    isSubmited: false,
    isNavToAddress:false,
    proHeight: 0,
    mallPageDelta: -1//商城首页 相对与当前页面的delta值，用于回跳
  },
  onLoad: function (options) {
    var that = this;

    //获取/pages/mall/mall相对与当前页面的delta值，用于回跳
    var pages = getCurrentPages()
    for(var i=0;i<pages.length;i++){
      if (pages[i].route.indexOf('mall/mall')>0){
        that.data.mallPageDelta= pages.length-(i+1);
        break;
      }
    }
    console.log(that.data.mallPageDelta);
    
    var address_id = options.address_id;

    var res = app.getSystemInfo();
    that.setData({
      windowWidth: res.windowWidth,
      proHeight: ((res.windowWidth - 20) * 0.62) / 3,
    });

    app.getUserInfoSimple(function (userInfo) {
      addressController.getList(function (res) {
        if (res.data.data && res.data.data.length > 0) {
          var address = res.data.data[0];
          if (address_id) {
            for (var i = 0; i < res.data.data.length; i++) {
              if (res.data.data[i].id == address_id) {
                address = res.data.data[i];
                break;
              }
            }
          }
          that.setData({
            addressInfo: address
          });
        }
      });

      orderController.getCart(function (res) {
        var orderInfo = res.data.data;
        var totalAmount = 0;
        for (var i = 0; i < orderInfo.length; i++) {
          var total = 0;
          var pro_num = 0;
          for (var j = 0; j < orderInfo[i].goods.length; j++) {
            total += orderInfo[i].goods[j].price * orderInfo[i].goods[j].num
            pro_num += Number(orderInfo[i].goods[j].num)
          }
          totalAmount += total;
          orderInfo[i].total = total.toFixed(2);
          orderInfo[i].pro_num = pro_num
        }
        console.log(res.data.data);
        that.setData({
          totalAmount: totalAmount.toFixed(2),
          orderInfo: orderInfo
        });
      });
    })


  },
  onUnload: function () {
    // Do something when page close.
    var that = this;
    if (!that.data.isSubmited&&!that.data.isNavToAddress) {
      layer.msg("订单已取消");
    }
  },
  submit: function (e) {
    var that = this;

    if (!that.data.addressInfo) {
      layer.msg("请填写收货人信息", function () {
        that.data.isNavToAddress = true;
        var redirect_url = encodeURIComponent("/pages/mall/order/confirm")
        wx.redirectTo({
          url: '/pages/mall/address/edit?redirect_url=' + redirect_url,
        })
      });
      return;
    }
    var formId = e.detail.formId;
    console.log(formId);
    orderController.submit(that.data.addressInfo.id, "", function (res) {
      var orderid = res.data.data.orderid;
      that.setData({
        isSubmited: true
      });
      orderController.getPaySignature(1, orderid, formId, function (res) {
        var data = res.data.data;
        console.log(data)
        var nonce_str = data.nonceStr;
        var sign = data.sign
        var packageStr = data.package;
        var timeStamp = data.timeStamp
        wx_api.requestPayment(timeStamp, nonce_str, packageStr, sign, function (res) {
          that.setData({
            isShowSuccessTips: true
          })
        }, function (res) {
          wx.navigateBack({
            delta: 1
          })
        });
      });

    });



  },
  formSubmit: function (e) {
    console.log('form发生了submit事件，携带数据为：', e.detail.value)
  },
  closeTips: function () {
    var that = this;
    that.setData({
      isShowSuccessTips: false
    })
    wx.redirectTo({
      url: '/pages/mall/order/list?activeIndex=1',
    })

  },
  redirectAddress: function () {
    var that = this;
    var redirect_url = encodeURIComponent("/pages/mall/order/confirm")
    that.data.isNavToAddress=true;
    wx.redirectTo({
      url: '/pages/mall/address/list?redirect_url=' + redirect_url,
    })
  },
  redirectAddressEdit: function () {
    var that = this;
    var redirect_url = encodeURIComponent("/pages/mall/order/confirm")
    that.data.isNavToAddress = true;
    wx.redirectTo({
      url: '/pages/mall/address/edit?redirect_url=' + redirect_url,
    })
  },
  redirectMall:function(){
    var that=this;
    if (that.data.mallPageDelta>0){
      wx.navigateBack({
        delta: that.data.mallPageDelta
      })
    }else{
      wx.redirectTo({
        url: '/pages/mall/mall',
      })
    }
  }
})