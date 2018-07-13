var app = getApp();
var wx_api = require('../../api/wx/api.js');
var layer = require('../../utils/layer.js');
var productController = require('../../api/mall/productController.js');
Page({
  data: {

    page: 1,
    produces: []

  },
  onLoad: function (options) {
    var that = this;

  },

  redirectDetail: function (e) {

    var pid = e.currentTarget.dataset.pid;
    wx.navigateTo({
      url: '/pages/mall/product/detail?pid=' + pid,
    })
  },
  //下拉刷新
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
    this.onLoad();
  },

})