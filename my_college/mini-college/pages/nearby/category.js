var app = getApp()
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var storeController = require('../../api/storeController.js');


Page({
  data: {
    category:[],
    itemWidth:0,//子选项的宽度
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    var that=this;
    that.getCategory();
    var res = app.getSystemInfo();
    that.setData({
      itemWidth: (res.windowWidth * 0.95)/4 -1      
    });
  },
  //获取门店分类
  getCategory: function () {
    var that = this;
    storeController.getCategory(function (data) {
      console.log(data);
      layer.close();
      that.setData({ category: data })
    })
  },
  redircetNeary:function(e){
    var that = this;
    var cid = e.currentTarget.dataset.cid;
    app.globalData.category_id=cid;
    wx.switchTab({
      url: '/pages/nearby/nearby',
    })
  }
})