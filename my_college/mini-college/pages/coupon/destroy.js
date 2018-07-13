var app = getApp();

var layer = require('../../utils/layer.js');
var couponController = require('../../api/couponController.js');
Page({
  data: {
    wid: 0
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    //layer.loading();
    var that = this;
    that.data.wid = options.wid;
    app.getUserInfoSimple(function (userInfo) {
      that.getCouponInfo(that.data.wid)
    })
  },
  //核销优惠券
  verifyCoupon: function () {
 
    var that = this;
    couponController.verifyCoupon(that.data.wid, function (res) {
      
      layer.msg("核销优惠券成功！！", function (res) {
         wx.redirectTo({ url: '/pages/card/my' })
      })
    })
  },
  cancel: function () {
    wx.redirectTo({ url: '/pages/card/my' })
  },
  //获取优惠券信息
  getCouponInfo: function (wid) {
    var that = this;
    couponController.getCouponInfoByWid(wid, function (res) {
      //更新数据
      var coupon = res.data.data;
      that.setData({ coupon: coupon })
    });
  }
})