var app = getApp();
var wx_api = require('../../api/wx/api.js');

Page({
  data: {
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
  //打开地图
  redirectMap: function () {

    wx_api.openLocation(22.963610,113.300300, "广州兴迪奥迪", "番禺区G105国道以东创源路（大夫山北门）")
  },
  //拨打电话
  callTel: function (e) {
    var that = this;
    var tel = e.currentTarget.dataset.tel;
    wx_api.makePhoneCall(tel)
  },
  // 分享
  onShareAppMessage: function () {
    var that = this;
    var url = '/pages/activity/audi';
    var title = '广州兴迪奥迪'
    return {
      title: title,
      path: url,
      success: function (res) {
        // 分享成功
    
      },
      fail: function (res) {
        // 分享失败
      }
    }
  },
})