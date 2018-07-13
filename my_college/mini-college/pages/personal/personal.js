var app = getApp();
var layer = require('../../utils/layer.js');
var auth = require('../../utils/auth.js');
var cardController = require('../../api/cardController.js');
Page({
  data: {
    userInfo: {},
  },
  editorlink: function () {

  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    var that = this
    //调用应用实例的方法获取全局数据
    app.getUserInfoSimple(function (userInfo) {      
      //更新数据
      that.setData({
        userInfo: userInfo
      })
      if (!app.globalData.userInfo.hot){
        that.getUserDetailInfo()
      }
    })
  },
  //获取我的名片详情
  getUserDetailInfo() {
    var that = this;
    cardController.getUserDetailInfo(app.globalData.userInfo.card_id, 0, function (res) {
      layer.close();
      var userInfo = res.data.data;    
      app.globalData.userInfo.hot = userInfo.hot
      that.setData({ userInfo: userInfo})
    });
  },

  callPhone: function () {
    wx.makePhoneCall({
      phoneNumber: '400-999-7972',
      success: function () {
        console.log("拨打电话成功！")
      },
      fail: function () {
        console.log("拨打电话失败！")
      }
    })
  },
  //下拉刷新
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
    this.onLoad();
  },

  //跳转前验证
  redirectVerify: function (e) {
    console.log(e.currentTarget);
    var url = e.currentTarget.dataset.url;
    if (!url) {
      return;
    }
    if (auth.isVerifedMobile()){
      wx.navigateTo({url: url})
    }
  },
  //跳转到设置
  redirectSetting:function(){
    wx.navigateTo({ url: "/pages/personal/set/set" })
  },
  redirectRanking:function(){
    wx.navigateTo({ url: "/pages/card/ranking" })
  },
  redirectCoupon:function(){
    wx.navigateTo({ url: "/pages/coupon/coupon" })
  },
  red_url: function (e) {
    var url = e.currentTarget.dataset.url;
    wx.redirectTo({
      url: url,
    })
  },
  red_shop: function (e) {
    wx.navigateToMiniProgram({
      appId: 'wx8bd756366330c980',
      path: 'pages/market/market',
      extraData: {
      },
      envVersion: 'release',
      success(res) {
        // 打开成功
      }
    })
  },
})

