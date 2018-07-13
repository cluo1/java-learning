var app = getApp();
var layer = require('../../utils/layer.js');
var storeController = require('../../api/storeController.js');
Page({
  data: {
    userInfo: {},
    storeInfo: {}
  },
  onLoad: function (options) {
    //layer.loading();

    // 页面初始化 options为页面跳转所带来的参数
    var that = this
    //调用应用实例的方法获取全局数据
    app.getUserInfoSimple(function (userInfo) {
      //更新数据
      that.setData({
        userInfo: userInfo
      })
      that.getStoreDetailInfo(options.store_id);
    });
  },
  //删除门店
  delstore: function () {
    var that = this;
    wx.showModal({
      title: '',
      content: "是否删除该记录？",
      success: function (res) {
        if (res.confirm) {
          storeController.delete(that.data.storeInfo.sid, function (res) {
            layer.success("删除成功！");
            wx.redirectTo({ url: '/pages/store/list' })
          });
        }
      }
    });
  },

  getStoreDetailInfo(store_id) {
    var that = this;
    storeController.getstore(store_id, function (res) {
      //更新数据
      that.setData({ storeInfo: res.data.data })
    })
  },

  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()
    that.getStoreDetailInfo(that.data.storeInfo.sid);
  }
})