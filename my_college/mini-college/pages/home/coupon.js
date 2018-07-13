var app = getApp();
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var couponController = require('../../api/couponController.js');
Page({
  data: {
    banner: [{ route: "/pages/mall/mall", image: "/images/activity/banner-1.png" }, { route: "/pages/activity/audi", image: "/images/activity/banner-2.jpg" }],
    coupons: [],
    myCoupons: [],
    isShowNewTag: true,
    animationData: null,
    imgWidth: 90,
    bannerHeight: 0,
    interval: null
  },


  onLoad: function (options) {
    //layer.loading();
    var that = this;
    var res = app.getSystemInfo();
    that.setData({
      imgWidth: res.windowWidth * 90 / 375,
      bannerHeight: res.windowWidth * 180 / 720
    });

    //调用应用实例的方法获取全局数据
    app.getUserInfo(function (userInfo) {
      that.getAllCoupon();
      that.getMyCoupons(userInfo.lat, userInfo.lng);
    })


  },
  onUnload: function () {
    clearInterval(this.data.interval);
  },
  //获取我领取的优惠券
  getMyCoupons: function (lat, lng) {
    var that = this;
    couponController.getUserCoupon(1, 1, function (res) {
      var coupons = [];
      for (var i = 0; i < res.data.data.length; i++) {
        res.data.data[i].start_time = util.formatTimestamp(res.data.data[i].start_time, 'yyyy.MM.dd');
        res.data.data[i].end_time = util.formatTimestamp(res.data.data[i].end_time, 'yyyy.MM.dd');
        res.data.data[i].addtime = util.formatTimestamp(res.data.data[i].addtime);
        res.data.data[i].distance = util.formatDistance(res.data.data[i].distance);

        coupons.push(res.data.data[i]);
      }
      that.setData({
        myCoupons: coupons
      })
    }, lat, lng);
  },
  getAllCoupon: function () {
    var that = this;

    var coupons = [
      {
        "name": "当当驾培飞翔公园店",
        "logo": "https://api.juworker.com/Public/Uploads/20170427/5901ad3011d23.jpg",
        "cid": "23",
        "title": "满6380减100",
        "start_time": "1493222400",
        "end_time": "1498492800",
        "type": "1",
        "distance": "4406"
      },
      {
        "name": "鸿燕堂",
        "logo": "https://api.juworker.com/Public/Uploads/20170610/593ad06623d33.jpg",
        "cid": "185",
        "title": "满100减20",
        "start_time": "1497024000",
        "end_time": "1499616000",
        "type": "1",
        "distance": "6115"
      },
      {
        "name": "鸿燕堂",
        "logo": "https://api.juworker.com/Public/Uploads/20170610/593ad5f3cba5f.jpg",
        "cid": "186",
        "title": "满189减50",
        "start_time": "1497024000",
        "end_time": "1499616000",
        "type": "1",
        "distance": "6115"
      },
      {
        "name": "鸿燕堂",
        "logo": "https://api.juworker.com/Public/Uploads/20170610/593ad06623d33.jpg",
        "cid": "187",
        "title": "满300减80",
        "start_time": "1497024000",
        "end_time": "1499616000",
        "type": "1",
        "distance": "6115"
      },
      {
        "name": "国酒易购",
        "logo": "https://api.juworker.com/Public/Uploads/20170603/59324ced00fa9.jpg",
        "cid": "152",
        "title": "满1552减1450",
        "start_time": "1496419200",
        "end_time": "1501516800",
        "type": "1",
        "distance": "6396"
      },
      {
        "name": "AppsMe爱丝蜜",
        "logo": "https://api.juworker.com/Public/Uploads/20170526/5927aa8cbc439.jpg",
        "cid": "113",
        "title": "满39.99减1000",
        "start_time": "1495728000",
        "end_time": "1527264000",
        "type": "1",
        "distance": "6669"
      }
    ];
    for (var i = 0; i < coupons.length; i++) {
      coupons[i].distance = util.formatDistance(parseInt(coupons[i].distance))
      //coupons.push(res.data.data[i]);
    }

    that.setData({
      coupons: coupons
    })

    var animation = wx.createAnimation({
      duration: 1000,
      timingFunction: "ease",
      delay: 0
    })
    that.animation = animation
    var i = 1;
    var interval = setInterval(function () {
      var transY = -44 * i
      if (i >= coupons.length) {
        transY = 0;
        i = 0;
      }
      that.animation.translateY(transY).step()
      i++
      that.animation = animation
      that.setData({
        animationData: that.animation.export()
      })
    }, "2000");

    this.setData({
      interval: interval
    })
  },

  redirectCoupons: function () {
    this.setData({
      isShowNewTag: false
    })
    app.globalData.isReloadNearbyCoupons = true
     wx.navigateTo({
      url: '/pages/nearby/nearby',
    })
  },
  redirectMyCards: function () {
    wx.navigateTo({
      url: '/pages/card/card',
    })
  },
  redirectMyCoupons: function () {
    wx.navigateTo({
      url: '/pages/coupon/coupon',
    })
  },
  redirectActivity:function(e){
    var route = e.currentTarget.dataset.route;
    wx.navigateTo({
      url: route,
    })  
  },
  red_url: function (e) {
    var url = e.currentTarget.dataset.url;
    wx.redirectTo({
      url: url,
    })
  },
  red_shop: function (e) {
    wx.navigateToMiniProgram({
      appId: 'wx8bd756366330c980',
      path: 'pages/market/market',
      extraData: {
      },
      envVersion: 'release',
      success(res) {
        // 打开成功
      }
    })
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    var that = this;
    wx.stopPullDownRefresh();
    clearInterval(that.data.interval)
    that.getAllCoupon();
    that.getMyCoupons();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  }

})