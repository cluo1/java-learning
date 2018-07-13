var app = getApp()
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var auth = require('../../utils/auth.js');
var cardController = require('../../api/cardController.js');
var storeController = require('../../api/storeController.js');
var couponController = require('../../api/couponController.js');
var dic = require('../../utils/dictionary.js');
Page({
  data: {
    animationData: null,
    animationData1: null,
    tag: 'coupons',
    page: 1,
    distance: 200,//当前选择的距离
    industry: 0,//当前选择的行业
    category_id:0,
    industryStr: '行业',
    hasMore: true,
    isShowLoading: false,
    industrylist: [],//行业数据
    cardlist: [],//用户信息列表（名片列表）
    couponlist: [],
    userInfo: {},//当前用户信息
    isShowTip: false,
    store_category:[],
    receiveCouponCount:0,
    createCardCount:0
  },
  onShow: function (options) {
    var that = this;
    if (app.globalData.isReloadNearbyCoupons) {
      that.setData({
        tag: "coupons",
        page: 1,
        hasMore: true
      })
      app.globalData.isReloadNearbyCoupons = false
      that.onLoad();
    }

    if (app.globalData.category_id>0){
      that.setData({
        page: 1,
        category_id: app.globalData.category_id
      })
      that.getAllCoupon();  
    }
  },

  //页面初始化
  onLoad: function (options) {
    var that = this
    that.initialAnimation();
    if (options) {
      //通过扫描小程序码进入小程序 判断
      var scene = options.scene
      app.globalData.options = JSON.stringify(options);
      that.wxacodeRedirect(scene);
    }
    
    var tag = that.data.tag;
    
    //调用应用实例的方法获取全局数据
    app.getUserInfo(function (userInfo) {
      //更新数据
      that.setData({
        userInfo: userInfo,
        isShowTip: true
      })
      that.getIndustry();
      that.getCategory();
      cardController.getStat(1, function (res) {
        that.setData({
          receiveCouponCount: res.data.data
        });
      });
      cardController.getStat(2, function (res) {
        that.setData({
          createCardCount: res.data.data
        });
      });
      if (tag == "nearby") {        
        that.getCards();  
      }
      if (tag == "coupons") {        
        that.getAllCoupon();     
      }

    })



  },
  //通过小程序码进入小程序-重定向
  wxacodeRedirect: function (scene) {
    // type = 1 & card_id=   //名片二维码
    // type = 2 & wid=         //优惠券二维码
    // type = 3 & card_id=   //成为核销员二维码
    //scene = "type=1&card_id="

    if (scene && scene.indexOf("type") >= 0) {
      scene = decodeURIComponent(scene);
      console.log(scene);
      var params = scene.split("&");
      var typeValue = params[0].split("=")[1];
      var redirect_url = '';
      params.splice(0, 1);
      params = params.join("&");
      switch (typeValue) {
        case "1": redirect_url = "/pages/card/detail?" + params; break;
        case "2": redirect_url = "/pages/coupon/destroy?" + params; break;
        case "3": redirect_url = "/pages/store/staff/add?" + params; break;
        default: break;
      }
      console.log("redirect_url=" + redirect_url);
      if (redirect_url) {
        wx.navigateTo({
          url: redirect_url,
        })
      }
    }
  },




  //获取行业类别
  getIndustry: function () {
    var that = this;
    cardController.getIndustry(function (data) {
      that.setData({ industrylist: data })
    })
  },
  //获取门店分类
  getCategory: function () {
    var that = this;
    storeController.getCategory(function (data) {
      console.log(data);
      that.setData({ store_category: data })
    })
  },

  searchByCategory:function(e){
    var that = this;
    var cid = e.currentTarget.dataset.cid;
    console.log("cid=" + cid);
    that.setData({
      category_id: cid,
      page: 1,
      hasMore: true
    });
    that.getAllCoupon();
    
  },
  //获取附近的人
  getCards: function () {
    var that = this;
    var userInfo = that.data.userInfo;
    if (userInfo.mobile && userInfo.name && userInfo.duty) {
      that.data.isShowTip = false;
    }
    that.setData({
      isShowTip: that.data.isShowTip
    });
    if (!that.data.hasMore) {
      return;
    }
    that.setData({
      isShowLoading: true
    });
    cardController.getList('', that.data.distance, that.data.industry, that.data.page, function (res) {
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

      //that.animationFromRight();
    });
  },
  //获取周边的券
  getAllCoupon: function () {

    var that = this;
    if (!that.data.hasMore) {
      return;
    }
    that.setData({
      isShowLoading: true
    });
    couponController.getAllCoupon('', that.data.category_id, that.data.page, function (res) {
      //更新数据
      var hasMore = !(that.data.page > 1 && res.data.data.length <= 0)
      var couponlist = that.data.page == 1 ? [] : that.data.couponlist;
      for (var i = 0; i < res.data.data.length; i++) {
        var item = res.data.data[i];
        item.distance = util.formatDistance(parseInt(item.distance))
  
        item.start_time = util.formatTimestamp(item.start_time, 'yyyy.MM.dd');
        item.end_time = util.formatTimestamp(item.end_time, 'yyyy.MM.dd');
        for (var j = 0; j < dic.couponType.length; j++) {
          if (item.type == dic.couponType[j].type) {
            item.typeName = dic.couponType[j].name;
            break;
          }
        }   
        couponlist.push(item);
      }

      that.setData({
        isShowLoading: false,
        hasMore: hasMore,
        couponlist: couponlist
      })
      //that.animationFromRight();


    });

  },
  redirectSearch: function () {
    wx.navigateTo({ url: '/pages/nearby/search?tag=' + this.data.tag })
  },
  redirectCreate: function () {
    var tag = this.data.tag;
    if (tag == "nearby") {
      wx.navigateTo({
        url: '/pages/card/make',
      })
    }
    if (tag == "coupons") {
      var redirect_url = encodeURIComponent('/pages/coupon/release');
      if (auth.isVerifedMobile(redirect_url)) {
        wx.navigateTo({
          url: '/pages/coupon/release',
        })
      }
    }
  },
  redirectCategory: function () {
    wx.navigateTo({
      url: '/pages/nearby/category',
    })
  },
  industryChange: function (e) {
    var that = this;

    var cid = e.target.dataset.id;
    console.log("cid=" + cid);
    //that.animationLeaveToLeft(function () {
    that.setData({
      industry: cid,
      page: 1,
      hasMore: true
    });
    if (that.data.tag == "nearby") {
      that.getCards();
    }
    if (that.data.tag == "coupons") {
      that.getAllCoupon();
    }
    //  });
  },

  //“附近的人”/“券场”标签切换事件
  tagChange: function (e) {
    var that = this;
    //that.animationLeaveToLeft(function () {
    var tag = e.currentTarget.dataset.tag;
    that.setData({
      tag: tag,
      page: 1,
      industry: 0,
      category_id:0,
      cardlist: [],
      couponlist: [],
      hasMore: true
    });
    if (tag == "nearby") {
      that.getCards();
    }
    if (tag == "coupons") {
      app.globalData.category_id=0;
      that.getAllCoupon();
    }
    //});
  },
  hideTip: function () {
    var that = this;
    that.animation1.scale(0, 0).opacity(0).step({ duration: 500 })
    that.setData({
      animationData1: that.animation.export()
    })
    setTimeout(function () {
      that.setData({
        isShowTip: false
      })
    }, 500)
  },

  //分享
  onShareAppMessage: function () {

    return {
      title: '天天卡券',
      path: '/pages/nearby/nearby',
      success: function (res) {
        // 分享成功
      },
      fail: function (res) {
        // 分享失败
      }
    }
  },
  //下拉刷新
  onPullDownRefresh: function () {
    var that = this;
    wx.stopPullDownRefresh();
    that.setData({
      page: 1,
      hasMore: true,
      category_id:0
    });
    that.getIndustry();
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
  },
  //初始化动画实例animation
  initialAnimation: function () {
    var that = this;
    var animation = wx.createAnimation({
      duration: 400,
      timingFunction: "ease",
      delay: 0
    })
    that.animation = animation
    that.animation1 = animation
  },
  //从右侧出现的动画
  animationFromRight: function () {
    var that = this;
    that.animation.translate(0, 0).scale(1, 1).opacity(1).step({ duration: 500 })//水平平移+放大到正常+显示
    that.setData({
      animationData: that.animation.export()
    })
  },
  //从左侧离开的动画
  animationLeaveToLeft: function (callback) {
    var that = this;
    that.animation.translate(-375, 0).scale(0.5, 0.5).opacity(0).step()//水平平移+缩小+隐藏
    that.animation.translate(375, 0).step({ duration: 1 })//还原初始位置
    that.setData({
      animationData: that.animation.export()
    })
    setTimeout(function () {
      callback();
    }, 400);
  },
  red_url:function(e){
    var url =e.currentTarget.dataset.url;
    wx.redirectTo({
      url:url,
    })
  },
  red_shop:function(e){
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
})