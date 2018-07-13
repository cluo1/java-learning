var app = getApp();
var wx_api = require('../../api/wx/api.js');
var layer = require('../../utils/layer.js');
var productController = require('../../api/mall/productController.js');
Page({
  data: {


    produces: [{
      pid: 1, info: [
        "http://img.juworker.com/upfiles/2017/06/30/xqt_01.jpg",
        "http://img.juworker.com/upfiles/2017/06/30/xqt_02.jpg",
        "http://img.juworker.com/upfiles/2017/06/30/xqt_03.jpg",
        "http://img.juworker.com/upfiles/2017/06/30/xqt_04.jpg",

      ]
    },
    {
      pid: 2, info: [

        "http://img.juworker.com/upfiles/2017/06/30/sd_xqt_01.jpg",
        "http://img.juworker.com/upfiles/2017/06/30/sd_xqt_02.jpg",
        "http://img.juworker.com/upfiles/2017/06/30/sd_xqt_03.jpg",
        "http://img.juworker.com/upfiles/2017/06/30/sd_xqt_04.jpg",

      ]
    },
    {
      pid: 3, info: [
        "http://img.juworker.com/upfiles/2017/06/30/01.jpg",
        "http://img.juworker.com/upfiles/2017/06/30/02.jpg", 
        "http://img.juworker.com/upfiles/2017/06/30/03.jpg",
        "http://img.juworker.com/upfiles/2017/06/30/04.jpg",
      ]
    },
    ],
    images: []


  },
  onLoad: function (options) {
    var that = this;
    var pid = options.pid;
    var produces = that.data.produces;
    for (var i = 0; i < produces.length; i++) {
      if (pid == produces[i].pid) {
        that.setData({
          images: produces[i].info
        });
        break;
      }
    }
  },

  redirectDetail: function (e) {

    var pid = e.currentTarget.dataset.pid;
    wx.navigateTo({
      url: '/pages/mall/product/detail?pid=' + pid,
    })
  },
  //下拉刷新
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
    this.onLoad();
  },

})