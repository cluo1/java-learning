var app = getApp();
var layer = require('../../utils/layer.js');

var couponController = require('../../api/couponController.js');
Page({
  data: {
    wid: 0,
    coupon: {}
  },
  onLoad: function (options) {
    //layer.loading();
    var that = this;
    that.data.wid = options.wid;
    that.setData({
      wid:that.data.wid
    })
    that.getCouponInfo(options.wid);

  },
  getCouponInfo: function (wid) {
    var that = this;
    couponController.getUseCoupon(wid, function (res) {
      that.setData({ coupon: res.data.data })
    })
  },
  back: function () {
    wx.navigateBack()
  },
  redirectReport: function () {
    wx.navigateTo({ url: '/pages/coupon/report?wid=' + this.data.wid })
  },
  onShareAppMessage: function () {
    var that = this;
    return {
      title: '核销优惠券',
      path: '/pages/coupon/destroy?wid=' + that.data.wid,
      success: function (res) {
        // 分享成功
      },
      fail: function (res) {
        // 分享失败
      }
    }
  },
})