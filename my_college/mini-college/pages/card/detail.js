var app = getApp();
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var dic = require('../../utils/dictionary.js');
var cardController = require('../../api/cardController.js');
var wx_api = require('../../api/wx/api.js');
Page({
  data: {
    userInfo: {},
    cardInfo: {},
    isShowPage: false,
    isShowSaveTips: false,
    imgWidth: 100,
    hasAuth: false
  },
  onLoad: function (options) {
    //layer.loading();
    if (options.user_id) {
      app.globalData.referral = options.user_id
    }
    // 页面初始化 options为页面跳转所带来的参数
    var that = this
    //调用应用实例的方法获取全局数据
    app.getUserInfoSimple(function (userInfo) {
      var isShowSaveCardTip = wx.getStorageSync("isShowSaveCardTip");
      if(!isShowSaveCardTip){
        wx.setStorage({
          key: 'isShowSaveCardTip',
          data: '0',
        })
      }
      //更新数据
      that.setData({
        userInfo: userInfo,
        isShowSaveTips: isShowSaveCardTip?false:true
      })
      if (app.globalData.userInfo.card_id == options.card_id) {
        wx.redirectTo({ url: '/pages/card/my' })
      } else {
        that.getUserDetailInfo(options.card_id);
      }
    })
    var res = app.getSystemInfo();
    that.setData({
      imgWidth: res.windowWidth / 3 - 18
    }); 
  },
  //获取名片详情
  getUserDetailInfo(card_id) {

    var that = this;
    cardController.getUserDetailInfo(card_id, 1, function (res) {
      layer.close();
      var cardInfo = res.data.data;
      for (var i = 0; i < cardInfo.coupon.length; i++) {
        cardInfo.coupon[i].distance = util.formatDistance(cardInfo.coupon[i].distance);
        //cardInfo.coupon[i].type = cardInfo.coupon[i].type == 1 ? '优惠券' : '折扣券';
        cardInfo.coupon[i].start_time = util.formatTimestamp(cardInfo.coupon[i].start_time, 'yyyy.MM.dd');
        cardInfo.coupon[i].end_time = util.formatTimestamp(cardInfo.coupon[i].end_time, 'yyyy.MM.dd');

        for (var j = 0; j < dic.couponType.length; j++) {
          if (cardInfo.coupon[i].type == dic.couponType[j].type) {
            cardInfo.coupon[i].typeName = dic.couponType[j].name;
            break;
          }
        }   
      }
      that.data.hasAuth = cardInfo.privacy == 0 || (cardInfo.privacy == 1 && cardInfo.is_friend == 1)
      //cardInfo.privacy = 1
      //更新数据
      that.setData({ cardInfo: cardInfo, isShowPage: true, hasAuth: that.data.hasAuth })
    });

  },
  //打开地图
  openMap: function () {
    var that = this;

    if (!that.data.hasAuth) {
      return;
    }
    var lat = Number(that.data.cardInfo.com_lat);
    var lng = Number(that.data.cardInfo.com_lng);
    console.log("lat=" + lat);
    console.log("lng=" + lng);

    if (lng == 0 || lat == 0) {
      return;
    }
    wx_api.openLocation(lat, lng, that.data.cardInfo.company, that.data.cardInfo.address)
  },

  previewAvatar: function (e) {
    var that = this;
    var avatar = that.data.cardInfo.avatar;
    if (avatar) {
      wx_api.previewImage([avatar], avatar);    
    }
  },
  previewImage: function (e) {
    var that = this;
    var curr = e.currentTarget.dataset.src;
    if (that.data.cardInfo.photo) {
      wx_api.previewImage(that.data.cardInfo.photo,curr);     
    }
  },
  //收藏到我的名片夹
  collection: function () {
    var that = this;
    cardController.addUserCard(that.data.cardInfo.card_id, function (res) {
      that.data.cardInfo.fav = 1;
      that.setData({
        cardInfo: that.data.cardInfo
      })
      layer.success('添加成功！');
    })
  },
  //移出我的名片夹
  delete: function () {
    var that = this;
    cardController.delUserCard(that.data.cardInfo.card_id, function (res) {
      that.data.cardInfo.fav = 0;
      that.setData({
        cardInfo: that.data.cardInfo
      })
      layer.success('删除成功！');
    });
  },
  redirectCreateMyCard: function () {
    wx.redirectTo({
      url: '/pages/card/make',
    })
  },
  redirectMyCard: function () {
    wx.redirectTo({
      url: '/pages/card/my',
    })
  },
  sendApply: function () {
    var that = this;
    cardController.sendApply(that.data.cardInfo.card_id, function (res) {
      layer.msg('发送成功！');
    });
  },
  savePhoneContact: function () {
    var that = this;
    if (!that.data.hasAuth) {
      layer.msg('互换名片后才能保存对方名片信息哦');
      return;
    }
    if (!wx.addPhoneContact) {
      layer.msg('当前微信版本不支持保存名片');
      return;
    }
    var card = that.data.cardInfo;
    wx_api.getImageInfo(card.avatar, function (res) {
      var path = res.path;
      console.log("path=" + path);      
      wx_api.addPhoneContact(path, card.name, card.mobile, card.company, card.duty, card.address)
    }, function () {
      console.log("没有获取到图片信息");
      wx_api.addPhoneContact('', card.name, card.mobile, card.company, card.duty, card.address)
    });
  },
  //拨打电话
  callTel: function (e) {
    var that = this;
    if (!that.data.hasAuth) {
      return;
    }

    var mobile = this.data.cardInfo.mobile;
    if (!util.isMobile(mobile)) {
      layer.msg("用户手机号不正确");
      return;
    }
    wx_api.makePhoneCall(mobile)   
  },
  hideSaveTips:function(){
    this.setData({
      isShowSaveTips:false
    });
  },
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()

  },
  onShareAppMessage: function () {
    var that = this;
    var url = '/pages/card/detail?card_id=' + that.data.cardInfo.card_id
    url += "&user_id=" + app.globalData.userInfo.user_id;

    return {
      title: '分享一个新好友',
      path: url,
      success: function (res) {
        // 分享成功
      },
      fail: function (res) {
        // 分享失败
      }
    }
  },
})