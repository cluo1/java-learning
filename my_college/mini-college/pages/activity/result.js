var app = getApp();
var wx_api = require('../../api/wx/api.js');
var layer = require('../../utils/layer.js');
var cardController = require('../../api/cardController.js');
var orderController = require('../../api/mall/orderController.js');
Page({
  data: {
    animationData: {},
    page: 1,
    produces: [],
    userInfo: null,
    centerLeft: 0,
    shareNumLeft: 0,
    dealNumRight: 0,
    sortImageWidth: 0,
    isShowPage: false,
    isShowLoading: false,
    isShowRuleTips:false
  },
  onLoad: function (options) {
    var that = this
    var animation = wx.createAnimation({
      duration: 800,
      timingFunction: 'ease',
    })
    this.animation = animation
    var res = app.getSystemInfo();
    that.setData({
      centerLeft: res.windowWidth / 2.0 - (0.5 * res.windowWidth) / 2,
      shareNumLeft: res.windowWidth * 0.1597 - 26.5,
      sortImageWidth: 0.28 * res.windowWidth
    });
    //调用应用实例的方法获取全局数据
    app.getUserInfoSimple(function (userInfo) {

      that.setData({
        userInfo: userInfo
      });
      layer.loading();
      that.getRank();
    })

  },
  getRank: function () {
    var that = this;
    that.setData({
      isShowLoading: true
    });
    orderController.getRanking(function (res) {
      setTimeout(function () {
        that.setData({
          isShowLoading: false
        });
      }, 2000);

      layer.close();
      //更新数据
      that.setData({
        userInfo: res.data.data.user,
        userInfos: res.data.data.list,
        isShowPage: true
      });
    });
  },
  refresh: function () {
    var that = this;
    that.animation.rotate(360).step()
    that.animation.rotate(0).step()
    that.setData({
      animationData: that.animation.export()
    })
    that.getRank();
  },
  redirectMall: function () {
     wx.redirectTo({
       url: '/pages/mall/mall',
     })
  },
  showTips:function(){
    var that = this;
    that.setData({
      isShowRuleTips: !that.data.isShowRuleTips
    })
  },


})