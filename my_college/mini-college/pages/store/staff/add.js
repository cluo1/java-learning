var app = getApp();
var layer = require('../../../utils/layer.js');
var staffController = require('../../../api/staffController.js');
var cardController = require('../../../api/cardController.js');
Page({
  data: {
    card_id: 0,
    cardInfo: {}
  },
  onLoad: function (options) {
    //layer.loading();
    var that = this
    that.data.card_id = options.card_id;
    //调用应用实例的方法获取全局数据
    app.getUserInfoSimple(function (userInfo) {
      that.getUserDetailInfo(options.card_id)
    })

  },
  confirm: function () {
    var that = this;

    staffController.applyStaff(that.data.card_id, function (res) {
      layer.msg("成功成为" + that.data.cardInfo.name + "核销员", function (res) {
        wx.switchTab({ url: '/pages/card/my' })
      })
    });

  },
  cancel: function () {
    wx.switchTab({ url: '/pages/card/my' })
  },
  getUserDetailInfo(card_id) {

    var that = this;
    cardController.getUserDetailInfo(card_id, 0, function (res) {
      that.setData({ cardInfo: res.data.data })
    })

  }
})
