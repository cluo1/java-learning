var app = getApp()
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var cardController = require('../../api/cardController.js');
var couponController = require('../../api/couponController.js');

Page({
  data: {
    distance: 1000,
    tag: '',
    inputShowed: true,
    page: 1,
    hasMore: true,
    keyword: '',
    placeholder: '',
    isShowLoading: false,
    cardlist: [],//用户信息列表（名片列表）
    couponlist: [],
  },
  onLoad: function (options) {
    var that = this;
    that.setData({
      tag: options.tag,
      placeholder: options.tag == "nearby" ? "请输入用户名" : "请输入门店名"
    });
    // 页面初始化 options为页面跳转所带来的参数
  },
  showInput: function () {
    this.setData({
      inputShowed: true
    });
  },
  hideInput: function () {
    this.setData({
      keyword: "",
      inputShowed: false
    });
  },
  clearInput: function () {
    this.setData({
      keyword: ""
    });
  },
  inputTyping: function (e) {
    var that = this;
    console.log(e.detail.value);
    that.setData({
      keyword: e.detail.value,
      page: 1,
      hasMore: true
    });
    if (that.data.tag == "nearby") {
      that.getCards();
    }
    if (that.data.tag == "coupons") {
      that.getAllCoupon();
    }
  },

  getCards: function () {

    var that = this;
    if (!that.data.hasMore) {
      return;
    }
    that.setData({
      isShowLoading: true
    });
    cardController.getList(that.data.keyword, that.data.distance, 0, that.data.page, function (res) {
      //更新数据
      var hasMore = !(that.data.page > 1 && res.data.data.length <= 0)
      var cardlist = that.data.page == 1 ? [] : that.data.cardlist;
      for (var i = 0; i < res.data.data.length; i++) {
        res.data.data[i].distance = util.formatDistance(parseInt(res.data.data[i].distance))
        cardlist.push(res.data.data[i]);
      }

      that.setData({
        isShowLoading: false,
        hasMore: hasMore,
        cardlist: cardlist
      })
    });
  },

  getAllCoupon: function () {

    var that = this;
    if (!that.data.hasMore) {
      return;
    }
    that.setData({
      isShowLoading: true
    });
    couponController.getAllCoupon(that.data.keyword, 0, that.data.page, function (res) {
      //更新数据
      var hasMore = !(that.data.page > 1 && res.data.data.length <= 0)
      var couponlist = that.data.page == 1 ? [] : that.data.couponlist;
      for (var i = 0; i < res.data.data.length; i++) {
        res.data.data[i].distance = util.formatDistance(parseInt(res.data.data[i].distance))
        res.data.data[i].type = res.data.data[i].type == 1 ? '优惠券' : '折扣券';
        res.data.data[i].start_time = util.formatTimestamp(res.data.data[i].start_time, 'yyyy.MM.dd');
        res.data.data[i].end_time = util.formatTimestamp(res.data.data[i].end_time, 'yyyy.MM.dd');
        couponlist.push(res.data.data[i]);
      }

      that.setData({
        isShowLoading: false,
        hasMore: hasMore,
        couponlist: couponlist
      })
    });

  },
  //下拉刷新
  onPullDownRefresh: function () {
    var that = this;
    wx.stopPullDownRefresh();
    that.setData({
      page: 1
    });
    if (that.data.tag == "nearby") {
      that.getCards();
    }
    if (that.data.tag == "coupons") {
      that.getAllCoupon();
    }
  },
  //上拉到底部加载新数据
  onReachBottom: function () {
    var that = this;
    that.setData({
      page: ++that.data.page
    });
    if (that.data.tag == "nearby") {
      that.getCards();
    }
    if (that.data.tag == "coupons") {
      that.getAllCoupon();
    }
  }
})