// pages/card/ranking.js
//人气排行榜
var app = getApp();
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var cardController = require('../../api/cardController.js');
Page({
  data: { 
    isShowLoading: false,
    userInfos: [],
    userInfo:null
  },

  onLoad: function (options) {

    // 页面初始化 options为页面跳转所带来的参数
    var that = this
    //调用应用实例的方法获取全局数据
    app.getUserInfoSimple(function (userInfo) {
      layer.close();
      userInfo.hot =app.globalData. userInfo.hot
      that.setData({
        userInfo: userInfo
      });
      that.getRank();
    })
  },
  getRank: function () {
    var that = this;   
    that.setData({
      isShowLoading: true
    });
    cardController.getRank( function (res) {
      //更新数据
     
      that.setData({
        isShowLoading: false,
        userInfos: res.data.data,
      });
    });
  },

  onPullDownRefresh: function () {
    var that = this;
    wx.stopPullDownRefresh()
    that.getRank();
  },

})