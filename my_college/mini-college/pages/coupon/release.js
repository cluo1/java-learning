var app = getApp();
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var auth = require('../../utils/auth.js');
var dic = require('../../utils/dictionary.js');
var couponController = require('../../api/couponController.js');
var storeController = require('../../api/storeController.js');

Page({
  data: {
    cid: 0,
    start_time: null,
    end_time: null,
    showTopTips: false,
    couponType: dic.couponType,
    coupontypeIndex: 0,
    isAgree: false,
    allChecked: false,
    // stores: [{ Name: "百果园棠下店", ID: 1, Checked: false }, { Name: "百果园东圃店", ID: 2, Checked: false }, { Name: "百果园大石店", ID: 3, Checked: false }],
    isShowStoreList: false,
    storelist: [],
    storeIndex: 0,
    coupon: {},
  },
  onLoad: function (options) {
    //layer.loading();
    var data = util.formatTime(new Date());
    var that = this;
    that.setData({
      start_time: data,
      end_time: data,
      cid: options.cid ? options.cid : 0
    });

    var redirect_url = encodeURIComponent('/pages/coupon/release');
    if (auth.isVerifedMobile(redirect_url)) {
      if (that.data.cid != 0) {
        that.getCoupon(that.data.cid);
      }
      that.getUserStore();
    }

  },
  //获取优惠券详情
  getCoupon: function (cid) {
    var that = this;
    couponController.getCouponByCid(cid, function (res) {
      if (res.data.data.type > 2) {
        res.data.data.type = 2
      }
      //更新数据
      that.setData({
        coupon: res.data.data,
        start_time: util.formatTimestamp(res.data.data.start_time, "yyyy-MM-dd"),
        end_time: util.formatTimestamp(res.data.data.end_time, "yyyy-MM-dd"),
        coupontypeIndex: parseInt(res.data.data.type) - 1
      })
    })

  },



  //保存优惠券信息
  save: function () {
    // if (!this.data.coupon.title) {
    //   layer.msg("请输入标题");
    //   return;
    // };
    var that = this;
    if (that.data.coupontypeIndex != 2 && !that.data.coupon.discount) {
      layer.msg("请输入优惠额度");
      return;
    };
    if (that.data.coupontypeIndex != 2 && !that.data.coupon.start_money) {
      layer.msg("请输入起用金额");
      return;
    };
    if (!that.data.coupon.total) {
      layer.msg("请输入预发张数");
      return;
    };
    if (!that.data.coupon.restrict) {
      layer.msg("请输入每人限取张数");
      return;
    };
    if (!that.data.coupon.remark) {
      layer.msg("请输入使用条件");
      return;
    };
    if (that.data.storelist.length <= 0) {
      layer.msg("没有门店信息，去添加门店", function (res) {
        if (res.confirm) {
          wx.redirectTo({ url: '/pages/store/edit' })
        }
      })
      return;
    }


    that.data.coupon.user_id = app.globalData.userInfo.user_id;
    that.data.coupon.access_token = app.globalData.userInfo.access_token;
    that.data.coupon.type = that.data.couponType[that.data.coupontypeIndex].type;
    that.data.coupon.start_time = that.data.start_time;
    that.data.coupon.end_time = that.data.end_time;
    that.data.coupon.store_id = that.data.storelist[that.data.storeIndex].sid;
    if (that.data.coupon.type == 1) {
      that.data.coupon.title = '满' + that.data.coupon.start_money + '减' + that.data.coupon.discount
    }
    if (that.data.coupon.type == 2) {
      if (parseFloat(that.data.coupon.discount) > 1) {
        layer.msg("折扣只能是大于0小于1");
        return;
      }
      that.data.coupon.title = '满' + that.data.coupon.start_money + '享受' + (parseFloat(that.data.coupon.discount) * 10.0).toFixed(1) + '折'
    }
    if (that.data.coupon.type == 3) {
      if (!that.data.coupon.title) {
        layer.msg("请输入标题");
        return;
      };
      that.data.coupon.discount = 0;
      that.data.coupon.start_money = 0;
    }
    var reqUrl = app.globalData.domain + '/Coupon/addCoupon';
    if (that.data.cid != 0) {
      reqUrl = app.globalData.domain + '/Coupon/editCoupon';
      that.data.coupon.cid = that.data.cid;
    }
    couponController.editCoupon(reqUrl, that.data.coupon, function (res) {
      layer.msg("保存成功！", function (res) {
        wx.redirectTo({ url: '/pages/coupon/myrelease' })
      })
    });

  },

  //获取用户门店信息
  getUserStore: function () {
    var that = this;
    storeController.getUserStore(function (res) {
      if (!res.data.data.store || res.data.data.store.length <= 0) {
        layer.msg("没有查询到门店信息，去添加门店", function (res) {
          if (res.confirm) {
            wx.redirectTo({ url: '/pages/store/edit' })
          }
        })
        return;
      }

      that.setData({ storelist: res.data.data.store })
    });
  },

  //输入框内容发生变化时触发
  inputChange: function (e) {
    this.data.coupon[e.currentTarget.id] = e.detail.value
    console.log(this.data.coupon);
  },

  coupontypeChange: function (e) {
    console.log(e.detail.value);
    this.setData({
      coupontypeIndex: e.detail.value
    });
  },
  storeChange: function (e) {
    console.log(e.detail.value);
    this.setData({
      storeIndex: e.detail.value
    });
  },

  start_timeChange: function (e) {
    this.setData({
      start_time: e.detail.value
    })
  },
  end_timeChange: function (e) {
    this.setData({
      end_time: e.detail.value
    })
  },
  //全选
  allChecked: function () {
    this.setData({
      allChecked: !this.data.allChecked
    });
    var newStores = this.data.stores;
    for (var i = 0; i < newStores.length; i++) {
      newStores[i].Checked = this.data.allChecked;
    }
    this.setData({
      stores: newStores
    })
  },
  checkboxChange: function (e) {
    console.log(e.detail.value);
    this.setData({
      allChecked: e.detail.value.length == this.data.stores.length
    });
  },
  chooseStore: function () {
    // this.setData({
    //   isShowStoreList: !this.data.isShowStoreList
    // })
  },

  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
  }
});