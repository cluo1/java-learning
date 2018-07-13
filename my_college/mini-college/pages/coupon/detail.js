var app = getApp();
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var auth = require('../../utils/auth.js');

var couponController = require('../../api/couponController.js');
Page({
  data: {
    wid: 0,
    cid: 0,
    share: 0,//是否分享页面打开
    isShowPage: false,
    isShowTips: false,
    isShowBusinessInfo: false,
    userInfo: {},//当前登录用户信息
    coupon: {},
    t: null,
    imgWidth: 100,
    canIUseShare: wx.canIUse('button.open-type.share')

  },
  onLoad: function (options) {

    if (options.user_id){
      app.globalData.referral = options.user_id
    }
    //layer.loading();

    // 页面初始化 options为页面跳转所带来的参数
    var that = this;
    console.log(options);
    that.data.wid = options.wid ? options.wid : 0
    that.data.cid = options.cid ? options.cid : 0
    that.data.share = options.share ? options.share : 0


    //调用应用实例的方法获取全局数据
    app.getUserInfoSimple(function (userInfo) {
      that.setData({
        userInfo: userInfo,
        wid: that.data.wid,
        cid: that.data.cid,
        share: that.data.share
      });
      if (that.data.wid > 0) {
        that.getCouponInfo(that.data.wid);

      } else {
        that.getCouponOne(that.data.cid);
      }
    })
    var res = app.getSystemInfo();
    that.setData({
      imgWidth: res.windowWidth / 3 - 18
    }); 


  },
  //领取优惠券
  receiveCoupon: function () {
    var that = this;
    var redirect_url = '/pages/coupon/detail?cid=' + that.data.cid
    if (auth.isVerifedMobile(redirect_url)) {
      couponController.receiveCoupon(that.data.coupon.cid, function (res) {
        layer.success("领取优惠券成功！");
        that.setData({
          wid: res.data.data.wid
        });
      })
    }

  },
  //获取优惠券信息
  getCouponInfo: function (wid) {
    var that = this;
    couponController.getCouponInfoByWid(wid, function (res) {
      //更新数据
      var coupon = res.data.data;
      if ((coupon.type == 1 || coupon.type == 2)&& coupon.discount) {
        coupon.discount = coupon.type == 1 ? ('￥' + coupon.discount) : ((coupon.discount * 10).toFixed(1) + '折');
      } else {
        coupon.discount = coupon.title
      }
      coupon.start_time = util.formatTimestamp(coupon.start_time, 'yyyy.MM.dd')
      coupon.end_time = util.formatTimestamp(coupon.end_time, 'yyyy.MM.dd')
      //coupon.photo = ["https:\/\/api.juworker.com\/Public\/Uploads\/20170526\/5927c652b57c0.jpg", "https:\/\/api.juworker.com\/Public\/Uploads\/20170526\/5927c652bec87.jpg"]
      that.setData({ coupon: coupon, isShowPage: true })
    });
  },
  //获取优惠券信息
  getCouponOne: function (cid) {
    var that = this;
    couponController.getCouponByCid(cid, function (res) {
      console.log(res.data.data)
      //更新数据
      var coupon = res.data.data;
      if ((coupon.type == 1 || coupon.type == 2) && coupon.discount) {
        coupon.discount = coupon.type == 1 ? ('￥' + coupon.discount) : ((coupon.discount * 10).toFixed(1) + '折');
      } else {
        coupon.discount = coupon.title
      }
      coupon.start_time = util.formatTimestamp(coupon.start_time, 'yyyy.MM.dd')
      coupon.end_time = util.formatTimestamp(coupon.end_time, 'yyyy.MM.dd')  
      that.setData({ coupon: coupon, isShowPage: true })
    })

  },
  //拨打电话
  callTel: function () {
    var tel = this.data.coupon.tel;
    if (!tel) {
      return;
    }
    wx.makePhoneCall({
      phoneNumber: tel,
      success: function () {
        console.log("拨打电话成功！")
      },
      fail: function () {
        console.log("拨打电话失败！")
      }
    })
  },
  showBusinessInfo: function () {
    var that = this;
    that.setData({
      isShowBusinessInfo: !that.data.isShowBusinessInfo
    });
  },
  //跳转到使用优惠券页面
  redirectUse: function () {
    if (this.data.share == 1){
      wx.switchTab({
        url: '/pages/home/coupon',
      })
    }else{
      wx.redirectTo({
        url: '/pages/coupon/use?wid=' + this.data.wid
      })
    }
  
  },
  //跳转到发布者名片信息页面
  redirectCardInfo: function () {
    if (this.data.coupon.card_id) {
      wx.navigateTo({
        url: '/pages/card/detail?card_id=' + this.data.coupon.card_id,
      })
    }
  },
  redirectHome: function () {
    wx.switchTab({ url: "/pages/personal/personal" });
  },
  //跳转到券场
  redirectCoupons: function () {
    wx.switchTab({
      url: '/pages/nearby/nearby',
    })
  },
  //打开地图
  openMap: function () {
    var that = this;
    var lat = Number(that.data.coupon.lat);
    var lng = Number(that.data.coupon.lng);
    console.log("lat=" + lat);
    console.log("lng=" + lng);

    if (lng == 0 || lat == 0) {
      return;
    }
    wx.openLocation({
      latitude: lat,
      longitude: lng,
      name: that.data.coupon.name,
      address: that.data.coupon.address,
      scale: 28,
      success: function (res) {
        console.log(res);
      },
      fail: function (res) {
        console.log(res);
      },
    })
  },
  // 分享
  onShareAppMessage: function () {
    var that = this;
    var url = '/pages/coupon/detail?share=1&cid=' + that.data.coupon.cid;
    url += "&user_id=" + app.globalData.userInfo.user_id;
    return {
      title: '领取商家红包',
      path: url ,
      success: function (res) {
        // 分享成功
      },
      fail: function (res) {
        // 分享失败
      }
    }
  },
  //显示分享提示
  showsharestips: function () {
    var that = this;
    if (that.data.canIUseShare) {
      return;
    }
    that.setData({
      isShowTips: (!that.data.isShowTips)
    })
    if (that.data.isShowTips) {
      that.data.t = setTimeout(function () {
        that.showsharestips()
      }, 1000)
    } else {
      clearTimeout(that.data.t);
    }
  },
  //下拉刷新
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()
  },
  previewImage: function (e) {
    var that = this;
    var curr = e.currentTarget.dataset.src;
    if (that.data.coupon.photo) {
      wx.previewImage({
        current: curr,
        urls: that.data.coupon.photo
      })
    }
  },

})