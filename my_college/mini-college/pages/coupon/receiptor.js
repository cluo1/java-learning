var app = getApp();
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var couponController = require('../../api/couponController.js');
Page({

  data: {
    cid: 0,
    tabs: [{ tag: "未使用", status: 1, total: 10 }, { tag: "已使用", status: 2, total: 10 }],
    activeIndex: 0,
    page: 1,
    hasMore: true,
    isShowLoading: false,
    userlist: [
    ]
  },

  onLoad: function (options) {
    //layer.loading();

    var that = this;

    if (options.cid) {
      that.data.cid = options.cid
      var send_num = options.send_num
      var use_num = options.use_num
      that.data.tabs[0].total = Number(send_num) - Number(use_num)
      that.data.tabs[1].total = Number(use_num)
      that.setData({
        tabs: that.data.tabs
      });
      that.getReceiveList(that.data.tabs[that.data.activeIndex].status);
    }

  },
  tabClick: function (e) {
    var that = this;
    that.setData({
      sliderOffset: e.currentTarget.offsetLeft,
      activeIndex: e.currentTarget.id,
      page: 1,
      hasMore: true
    });
    that.getReceiveList(that.data.tabs[that.data.activeIndex].status);
  },
  getReceiveList: function (status) {
    var that = this;
    if (!that.data.hasMore) {
      return;
    }
    that.setData({
      isShowLoading: true
    });
    couponController.getReceiveList(that.data.cid, status, that.data.page, function (res) {
      var hasMore = !(that.data.page > 1 && res.data.data.length <= 0)
      var userlist = that.data.page == 1 ? [] : that.data.userlist;
      for (var i = 0; i < res.data.data.length; i++) {
        res.data.data[i].addtime = util.formatTimestamp(res.data.data[i].addtime);
        userlist.push(res.data.data[i]);
      }
      that.setData({
        isShowLoading: false,
        hasMore: hasMore,
        userlist: userlist
      })
    });
  },
  // 页面上拉触底事件的处理函数  
  onReachBottom: function () {
    var that = this;
    that.setData({
      page: ++that.data.page
    });
    that.getReceiveList(that.data.tabs[that.data.activeIndex].status);

  },

  //页面相关事件处理函数--监听用户下拉动作   
  onPullDownRefresh: function () {
    var that = this;
    wx.stopPullDownRefresh();
    that.setData({
      page: 1,
      hasMore: true
    });
    that.getReceiveList(that.data.tabs[that.data.activeIndex].status);

  }
})