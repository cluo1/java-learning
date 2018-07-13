var app = getApp();
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var cardController = require('../../api/cardController.js');
var wx_api = require('../../api/wx/api.js');
Page({
  data: {
    animationData:null,
    isShowTips: false,
    userInfo: {},
    qrcode: '',
    isShowPage: false,
    isPerfect: true,
    t: null,
    imgWidth: 100,
    canIUseShare: wx.canIUse('button.open-type.share'),
    msgCount:0
  },
  onShow: function () {
    var that = this

    var animation = wx.createAnimation({
      duration: 500,
      timingFunction: "ease",
      delay: 0
    })
    that.animation = animation

    cardController.getApplysCount(function (res) {
      that.setData({
        msgCount: res.data.data.count
      });
    });
    if (!app.globalData.userInfo || !app.globalData.userInfo.isChange) {
      return;
    }
    //layer.loading();
    app.getUserInfoSimple(function (userInfo) {
      //更新数据
      that.setData({
        userInfo: userInfo
      })
      that.getUserDetailInfo();
      app.globalData.userInfo.isChange = false;
    })
  },
  onLoad: function (options) {
    layer.loading();
    if (options.user_id) {
      app.globalData.referral = options.user_id
    }
    var that = this
    app.getUserInfoSimple(function (userInfo) {
      //更新数据
      that.setData({
        userInfo: userInfo
      })
      that.getUserDetailInfo();
      // cardController.getApplysCount(function (res) {
      //   that.setData({
      //     msgCount: res.data.data.count
      //   });
      // });
    })
    var res = app.getSystemInfo();
    that.setData({
      imgWidth: res.windowWidth / 3 - 18
    }); 

  },
  //获取我的名片详情
  getUserDetailInfo() {
    var that = this;
    that.data.isPerfect = true;
    cardController.getUserDetailInfo(app.globalData.userInfo.card_id, 0, function (res) {
      layer.close();
      var userInfo = res.data.data;
      console.log(userInfo);
      if (!userInfo.mobile || !userInfo.company || !userInfo.name || !userInfo.duty) {
        that.data.isPerfect = false;
      }
      app.globalData.userInfo.hot = userInfo.hot
      that.setData({ userInfo: userInfo, isPerfect: that.data.isPerfect, isShowPage: true })
    });
  },
  //获取我的名片二维码
  getMyQrcode: function () {
    var that = this;
    layer.loading();
    cardController.getMyQrcode(function (res) {
      that.setData({ qrcode: res.data.data.qrcode })
    });
  },
  //跳转到绑定手机号页面
  redirectBindMobile: function () {
    wx.navigateTo({
      url: '/pages/personal/bindMobile?mobile=' + this.data.userInfo.mobile,
    }) 
  },
  redirectLookers: function () {
    wx.navigateTo({
      url: '/pages/card/lookers',
    })
  },
  redirectSavers:function(){
    wx.navigateTo({
      url: '/pages/card/savers',
    })
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
  previewAvatar: function (e) {
    var that = this;
    var avatar = that.data.userInfo.avatar;
    if (avatar) {
      wx_api.previewImage([avatar],avatar)    
    }
  },
  previewImage: function (e) {
    var that = this;
    var curr = e.currentTarget.dataset.src;
    if (that.data.userInfo.photo) {
      wx_api.previewImage(that.data.userInfo.photo, curr)      
    }
  },
  //显示我的名片二维码
  showmycardcode: function () {
    var that = this;

    that.animation.scale(1, 1).opacity(1).step()
    that.setData({
      animationData: that.animation.export()
    })   
    if (!that.data.qrcode) {
      that.getMyQrcode();
    }
  },
  hidemycardcode:function(){
    var that = this;

    that.animation.scale(0, 0).opacity(0).step()
    that.setData({
      animationData: that.animation.export()
    })  
  },
  //打开地图
  openMap: function () {
    var that = this;
    var lat = Number(that.data.userInfo.com_lat);
    var lng = Number(that.data.userInfo.com_lng);
    console.log("lat=" + lat);
    console.log("lng=" + lng);

    if (lng == 0 || lat == 0) {
      return;
    }
    wx_api.openLocation(lat, lng, that.data.userInfo.company, that.data.userInfo.address)
  
  },
  redirectApplicants: function () {
    wx.navigateTo({
      url: '/pages/card/apply',
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
  onShareAppMessage: function () {
    var card_id = this.data.userInfo.card_id;
    var url = '/pages/card/detail?card_id=' + card_id
    url += "&user_id=" + app.globalData.userInfo.user_id;

    return {
      title: '这是我的名片，请惠存',
      path: url,
      success: function (res) {
        // 分享成功
      },
      fail: function (res) {
        // 分享失败
      }
    }
  },
  onPullDownRefresh: function () {
    var that=this;
    wx.stopPullDownRefresh()
    that.getUserDetailInfo();
  }
})
