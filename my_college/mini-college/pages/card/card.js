var app = getApp();
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var cardController = require('../../api/cardController.js');
Page({
  data: {
    hasMore: true,
    isShowLoading: false,
    keyword: '',
    page: 1,
    cardlist: [],
    hasNewApply:false
  },

  onLoad: function (options) {    
    // 页面初始化 options为页面跳转所带来的参数
    var that = this
    //调用应用实例的方法获取全局数据
    app.getUserInfoSimple(function (userInfo) {
      layer.close();
      //更新数据
      that.setData({
        userInfo: userInfo
      })
      that.getUserCard();
      cardController.getApplysCount(function (res) {
        that.setData({
          hasNewApply: res.data.data.count>0
        });
      });
    })
  },
  //拨打电话
  calling: function (e) {
    var moblie = e.currentTarget.dataset.value;
    if (!moblie) {
      layer.msg("用户没有手机号");
      return;
    }
    wx.makePhoneCall({
      phoneNumber: moblie,
      success: function () {
        console.log("拨打电话成功！")
      },
      fail: function () {
        console.log("拨打电话失败！")
      }
    })
  },
  //获取名片列表
  getUserCard: function () {
    var that = this;
    var page = that.data.page;
    var keyword = that.data.keyword;
    if (!that.data.hasMore) {
      return;
    }
    that.setData({
      isShowLoading: true
    });
    cardController.getUserCard(keyword, page, function (res) {
      
      //更新数据
      var hasMore = !(that.data.page > 1 && res.data.data.length <= 0)
      var cardlist = page == 1 ? [] : that.data.cardlist;
      for (var i = 0; i < res.data.data.length; i++) {
        cardlist.push(res.data.data[i]);
      }
      that.setData({
        cardlist: cardlist,
        hasMore: hasMore,
        isShowLoading: false
      })
    })
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
    console.log(e.detail.value);
    this.setData({
      keyword: e.detail.value,
      hasMore:true
    });
    this.getUserCard();
  },
  redirectApplicants:function(){
    wx.navigateTo({
      url: '/pages/card/apply',
    })
  },
  redirectLookers:function(){
    wx.navigateTo({
      url: '/pages/card/lookers',
    })
  },
  onPullDownRefresh: function () {
    var that=this;
    wx.stopPullDownRefresh()
    that.setData({
      page: 1,
      hasMore:true
    });
    this.getUserCard();
    cardController.getApplysCount(function (res) {
      that.setData({
        hasNewApply: res.data.data.count > 0
      });
    });
  },
  onReachBottom: function () {

    this.setData({
      page: ++this.data.page
    });
    this.getUserCard();
  }
})
