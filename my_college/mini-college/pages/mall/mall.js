var app = getApp();
var wx_api = require('../../api/wx/api.js');
var layer = require('../../utils/layer.js');
var productController = require('../../api/mall/productController.js');
var otherController = require('../../api/otherController.js');
Page({
  data: {
    page: 1,
    produces: [],
    ruleImageHeight:0
  },
  onLoad: function (options) {

   
    var that = this;
    var pages = getCurrentPages()
    console.log(pages);
    var res = app.getSystemInfo();
    that.setData({
      ruleImageHeight: (res.windowWidth * 0.3)*57/190,
      bannerHeight: res.windowWidth * 180 / 720
    });
    app.getUserInfoSimple(function (userInfo) {
      //that.getProducts();
    });
  },
  getProducts: function () {
    var that = this;
    productController.getList(that.data.page, function (res) {
      that.setData({
        produces: res.data.data
      });
    });
  },
  redirectDetail: function (e) {

    var pid = e.currentTarget.dataset.pid;
    wx.navigateTo({
      url: '/pages/mall/product/detail?pid=' + pid,
    })
  },
  redircetPrize:function(e){
    var pid = e.currentTarget.dataset.pid;
    wx.navigateTo({
      url: '/pages/activity/prize?pid=' + pid,
    })
  },
  //下拉刷新
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
    this.onLoad();
  },
  // 分享
  onShareAppMessage: function () {
    var that = this;
    var url = '/pages/mall/mall?share=1';
    url += "&user_id=" + app.globalData.userInfo.user_id;
    var title =  '转发有奖，万元豪礼等你来'
    return {
      title: title,
      path: url,
      success: function (res) {
        // 分享成功
        otherController.updateShare();
      },
      fail: function (res) {
        // 分享失败
      }
    }
  },


})