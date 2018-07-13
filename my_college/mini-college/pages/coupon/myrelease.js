var sliderWidth = 96; // 需要设置slider的宽度，用于计算中间位置
var app = getApp();
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var couponController = require('../../api/couponController.js');
Page({
  data: {
    page: 1,
    tabs: ["未开始", "进行中", "已结束"],
    activeIndex: 1,
    sliderOffset: 0,
    sliderLeft: 0,
    coupons: []
  },
  onLoad: function () {
    //layer.loading();

    var that = this;
    that.getCoupon(that.data.activeIndex);

    var res = app.getSystemInfo();
    that.setData({
      sliderLeft: (res.windowWidth / that.data.tabs.length - sliderWidth) / 2,
      sliderOffset: res.windowWidth / that.data.tabs.length * that.data.activeIndex
    });

  },

  tabClick: function (e) {
    var that = this;
    console.log(e.currentTarget.id);
    this.setData({
      sliderOffset: e.currentTarget.offsetLeft,
      activeIndex: e.currentTarget.id,
      page: 1
    });
    that.getCoupon(e.currentTarget.id);
  },

  //获取我发布的优惠券
  getCoupon: function (status) {

    var that = this;
    couponController.getCoupon(status, that.data.page, function (res) {
      //更新数据
      var coupons = that.data.page == 1 ? [] : that.data.coupons;

      for (var i = 0; i < res.data.data.length; i++) {

        res.data.data[i].start_time = util.formatTimestamp(res.data.data[i].start_time, 'yyyy.MM.dd');
        res.data.data[i].end_time = util.formatTimestamp(res.data.data[i].end_time, 'yyyy.MM.dd');
        res.data.data[i].addtime = util.formatTimestamp(res.data.data[i].addtime);
        coupons.push(res.data.data[i]);
      }
      that.setData({
        coupons: coupons
      })
    })
  },

  //删除优惠券
  delete: function (e) {
    var that = this;
    layer.confirm('确认要删除优惠券？', function (res) {
      if (res.confirm) {
        var cid = e.currentTarget.dataset.cid;
        if (!cid) {
          layer.msg("操作失败！");
          return;
        }
        couponController.delCoupon(cid, function (res) {
          layer.success("成功！");
          that.setData({
            page: 1
          });
          that.getCoupon(that.data.activeIndex)
        });
      }
    })

  },

  //修改优惠券状态
  changeState: function (e) {
    var currState = e.currentTarget.dataset.state;
    var cid = e.currentTarget.dataset.cid;
    if (!cid || !currState) {
      layer.msg("操作失败！");
      return;
    }
    var status = currState == 0 ? 1 : 0;
    var that = this;
    layer.loading();
    couponController.changeState(status, cid, function (res) {
      that.setData({
        page: 1
      });
      that.getCoupon(that.data.activeIndex);
    })
  },

  //下拉刷新
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
    var that = this;
    that.setData({
      page: 1
    });
    that.getCoupon(that.data.activeIndex);
  },

  //上拉到底部加载新数据
  onReachBottom: function () {
    var that = this;
    that.setData({
      page: ++that.data.page
    });
    that.getCoupon(that.data.activeIndex);
  }
});