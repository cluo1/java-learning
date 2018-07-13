var app = getApp();
var layer = require('../../utils/layer.js');
var storeController = require('../../api/storeController.js');
Page({
  data: {
    storelist: [],//门店列表
    userInfo: {}//当前用户信息
  },
  onLoad: function (options) {
    //layer.loading();
    var that = this  

    //调用应用实例的方法获取全局数据
    app.getUserInfoSimple(function (userInfo) {
      //更新数据
      that.setData({
        userInfo: userInfo
      })
      that.getStores();
    })
  },
  //获取用户所有门店信息
  getStores: function () {
    var that = this;
    storeController.getUserStore(function (res) {
      if (res.data.code != 1) {
        layer.msg("获取门店信息失败！");
        return;
      }
      if (res.data.data.store.length<=0){
        wx.navigateTo({
          url: '/pages/store/edit',
        })
        return;
      }
      //更新数据    
      that.setData({
        storelist: res.data.data.store
      })
    });

  },
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()
    this.getStores();
  }
})