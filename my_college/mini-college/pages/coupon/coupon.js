var sliderWidth = 96; // 需要设置slider的宽度，用于计算中间位置
var app = getApp();
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var dic = require('../../utils/dictionary.js');

var couponController = require('../../api/couponController.js');
// 1、未使用；2、已使用；0、已过期
Page({
  data: {
    page: 1,
    tabs: ["未使用", "已使用", "已过期"],
    activeIndex: 0,
    sliderOffset: 0,
    sliderLeft: 0,
    coupons: [],
    dic: [{ index: 0, state: 1 }, { index: 1, state: 2 }, { index: 2, state: 0 }]
  },
  onLoad: function () {
    //layer.loading();

    var that = this;
    that.getCoupon(that.data.dic[that.data.activeIndex].state);
    var res = app.getSystemInfo();
    that.setData({
      sliderLeft: (res.windowWidth / that.data.tabs.length - sliderWidth) / 2,
      sliderOffset: res.windowWidth / that.data.tabs.length * that.data.activeIndex
    });

  },

  tabClick: function (e) {
    var that = this;
    that.setData({
      sliderOffset: e.currentTarget.offsetLeft,
      activeIndex: e.currentTarget.id,
      page: 1
    });
    that.getCoupon(that.data.dic[that.data.activeIndex].state);
  },

  //获取我领取的优惠券
  getCoupon(status) {
    var that = this;
    couponController.getUserCoupon(status, that.data.page, function (res) {
      var coupons = that.data.page == 1 ? [] : that.data.coupons;
      for (var i = 0; i < res.data.data.length; i++) {
        var item = res.data.data[i];
        item.start_time = util.formatTimestamp(item.start_time, 'yyyy.MM.dd');
        item.end_time = util.formatTimestamp(item.end_time, 'yyyy.MM.dd');
        item.addtime = util.formatTimestamp(item.addtime);
        for (var j = 0; j < dic.couponType.length; j++) {
          if (item.type == dic.couponType[j].type) {
            item.typeName = dic.couponType[j].name;
            break;
          }
        }
        coupons.push(item);
      }
      that.setData({
        coupons: coupons
      })
    });
  },

  //下拉刷新
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
    var that = this;
    that.setData({
      page: 1
    });
    that.getCoupon(that.data.dic[that.data.activeIndex].state);
  },

  //上拉到底部加载新数据
  onReachBottom: function () {
    var that = this;
    that.setData({
      page: ++that.data.page
    });
    that.getCoupon(that.data.dic[that.data.activeIndex].state);
  }

});