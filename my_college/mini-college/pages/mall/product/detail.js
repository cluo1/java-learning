var app = getApp();
var wx_api = require('../../../api/wx/api.js');
var layer = require('../../../utils/layer.js');
var productController = require('../../../api/mall/productController.js');
var orderController = require('../../../api/mall/orderController.js');


Page({
  data: {
    productInfo:{},
    productNum:1,
    total:0,
    windowWidth:0,
    submitText:"立即购买",
    btn_submit_bgcolor: "#48a7dd",
    textColor:"#ccc"
  },
  onLoad: function (options) {
    var that=this;    
    if (options.user_id) {
      app.globalData.referral = options.user_id
    }
    var pid= options.pid
    var res = app.getSystemInfo();
    that.setData({
      windowWidth: res.windowWidth,

    });
    app.getUserInfoSimple(function (userInfo) {
      that.getDetail(pid);
    })

  },
  getDetail:function(pid){
    var that=this;
    productController.detail(pid, function (res) {
      var productInfo = res.data.data;
      productInfo.price = parseFloat(productInfo.price).toFixed(2)
      productInfo.market_price = parseFloat(productInfo.market_price).toFixed(2)
      that.setData({
        productInfo: productInfo,
        total: (res.data.data.price * that.data.productNum).toFixed(2)
      });
      if (that.data.productInfo.quantity <= 0) {
        that.setData({
          productNum: 0,
          submitText: "库存不足",
          btn_submit_bgcolor: "#ccc"
        });
      }
    });
  },
  
  addProductNum:function(){
    var that=this;
    if (that.data.productInfo.quantity <= 0) {
      layer.msg("库存不足啦~");
      return;
    }
    that.setData({
      textColor: "#000",
      productNum: ++that.data.productNum,
      total: (that.data.productInfo.price * that.data.productNum).toFixed(2)
    });
  },
  deleteProductNum:function(){
    var that = this;
    
    if (that.data.productNum <= 1) {
      return;
    }
    var produceNum = --that.data.productNum;
    that.setData({
      productNum: produceNum ,
      textColor: produceNum <= 1 ? "#ccc":"#000",
      total: (that.data.productInfo.price * that.data.productNum).toFixed(2)
    });
  },
  submit:function(){
    var that=this;

    if (that.data.productInfo.quantity<=0){
      layer.msg("库存不足啦~");
      return;
    }
    var cart_info = that.data.productInfo.gid + ":"+that.data.productNum;    
    orderController.updateCart(cart_info,function(res){
      wx.navigateTo({
        url: '/pages/mall/order/confirm',
      })
    });
  },
  // 分享
  onShareAppMessage: function () {
    var that = this;
    var url = '/pages/mall/product/detail?share=1&pid=' + that.data.productInfo.gid;
    url += "&user_id=" + app.globalData.userInfo.user_id;
    var title = that.data.productInfo.title ? that.data.productInfo.title:'发现一个好东西，分享给你'
    return {
      title: title,
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