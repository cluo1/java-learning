var app = getApp();
var cardController = require('../../api/cardController.js');
Page({
  data: {
    qrcode: '',
    userInfo: {}
  },
  onLoad: function (options) {
    var that = this
    //调用应用实例的方法获取全局数据
    app.getUserInfoSimple(function (userInfo) {
      //更新数据
      that.setData({
        userInfo: userInfo
      })
      that.getMyQrcode();
    })
  },  
  getMyQrcode: function () {
    var that = this;
    cardController.getMyQrcode(function (res) {
      that.setData({ qrcode: res.data.data.qrcode })
    });
  },
})