var app = getApp();
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var cardController = require('../../api/cardController.js');
Page({
  data: {
    page: 1,
    num: 10,//页容量
    hasMore: true,
    isShowLoading: false,
    userInfos: []
  },

  onLoad: function (options) {
    
    // 页面初始化 options为页面跳转所带来的参数
    var that = this
    that.getApplys();
 
  },
  getApplys: function () {
    var that = this;
    if (!that.data.hasMore) {
      return;
    }
    that.setData({
      isShowLoading: true
    });
    cardController.getApplys(that.data.page, that.data.num, function (res) {
      //更新数据
      var hasMore = !(that.data.page > 1 && res.data.data.length <= 0)
      var userInfos = that.data.page == 1 ? [] : that.data.userInfos;
      for (var i = 0; i < res.data.data.length; i++) {
        userInfos.push(res.data.data[i]);
      }
      that.setData({
        hasMore: hasMore,
        isShowLoading: false,
        userInfos: userInfos
      });
    });
  },
  processApply:function(e){
    var that=this;
    var status = e.currentTarget.dataset.status;
    var msgid = e.currentTarget.dataset.msgid;
    if(status==0){
      cardController.processApply(msgid,1,function(res){
        var userinofs= that.data.userInfos;
        for (var i = 0; i < userinofs.length;i++){
          if (userinofs[i].msg_id==msgid){
            userinofs[i].status=1;
            break;
          }
        }

        that.setData({
          userInfos: userinofs
        });
      });
    }

  },
  redirectCardInfo:function(e){
    var that = this;
    var card_id = e.currentTarget.dataset.cardid;
    wx.navigateTo({
      url: '/pages/card/detail?card_id='+card_id,
    })
  },
  onPullDownRefresh: function () {
    var that = this;

    wx.stopPullDownRefresh()
    that.setData({
      page: 1,
      hasMore:true
    });
    that.getApplys();
  },

  onReachBottom: function () {
    var that = this;
    that.setData({
      page: ++that.data.page
    });

    that.getApplys();
  }
})