var app = getApp();
var util = require('../../../utils/utils.js');
var layer = require('../../../utils/layer.js');
var cardController = require('../../../api/cardController.js');
Page({

  data: {
    types: [{ privacy: "0", name: '所有人可见', image: '' }, { privacy: "1", name: '仅好友可见', image: '' }],
    privacy: "0",
  },

  onLoad: function (options) {

    var that = this

    app.getUserInfoSimple(function (userInfo) {
      for (var i = 0; i < that.data.types.length; i++) {
        that.data.types[i].image = that.data.types[i].privacy == userInfo.privacy ? '/images/checked.png' : '/images/null.png'
      }
      that.setData({
        privacy: userInfo.privacy,
        types: that.data.types
      })
    })
  },
  changeState: function (e) {
    var that = this;
    var privacy = e.currentTarget.dataset.privacy;
    app.globalData.userInfo.privacy = privacy
    for (var i = 0; i < that.data.types.length; i++) {
      that.data.types[i].image = that.data.types[i].privacy == privacy ? '/images/checked.png' : '/images/null.png'
    }
    console.log(that.data.types)
    cardController.setPrivacy(privacy, function (res) {
      that.setData({
        privacy: privacy,
        types: that.data.types
      });
    })
  },
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
  },

})