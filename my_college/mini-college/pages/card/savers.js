var app = getApp();
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var cardController = require('../../api/cardController.js');
Page({
  data: {
    page: 1,
    num:10,
    hasMore: true,
    isShowLoading: false,
    userInfos: [],
    totalCount: 0
  },

  onLoad: function (options) {

    // 页面初始化 options为页面跳转所带来的参数
    var that = this
    that.getCollect();
 
  },
  getCollect: function () {
    var that = this;
    if (!that.data.hasMore) {
      return;
    }
    that.setData({
      isShowLoading: true
    });
    cardController.getCollect(that.data.page,that.data.num, function (res) {
      //更新数据
      var data = res.data.data.result
      var totalCount = res.data.data.count;
      var totalPage = totalCount % that.data.num == 0 ? totalCount / that.data.num : (totalCount / that.data.num + 1)
      var hasMore = totalPage > that.data.page + 1
      var userInfos = that.data.page == 1 ? [] : that.data.userInfos;
      for (var i = 0; i < data.length; i++) {
        var date = util.formatTime(new Date((data[i].addtime) * 1000));
        data[i].addtime = date.substring(5);
        userInfos.push(data[i]);
      }
      that.setData({
        hasMore: hasMore,
        isShowLoading: false,
        userInfos: userInfos,
        totalCount: totalCount
      });
    });
  },

  onPullDownRefresh: function () {
    var that = this;
    wx.stopPullDownRefresh()
    that.setData({
      page: 1,
      hasMore: true
    });
    that.getCollect();
  },

  onReachBottom: function () {
    var that = this;
    that.setData({
      page: ++that.data.page
    });

    that.getCollect();
  }
})