var layer = require('../../../utils/layer.js');
Page({

  data: {
    stroageSize:"0"
  },

  onLoad: function (options) {
    var that=this;
    wx.getStorageInfo({
      success: function (res) {
        that.setData({
          stroageSize: that.formatSize(res.currentSize)
        });
        console.log(res.keys)
        console.log(res.currentSize)
        console.log(res.limitSize)
      }
    })
  },
  redirectWxSet:function(){
    wx.openSetting({
      success: (res) => {
        console.log(res);
      }
    })
  },
  redirectSecret:function(){
    wx.navigateTo({ url: "/pages/personal/set/secret" })
  },
  clearStorage:function(){
    var that=this;
    layer.confirm('是否所有清除缓存？', function (res) {
      if (res.confirm) {
        wx.clearStorageSync()
        layer.success("成功清除缓存" + that.data.stroageSize);
        that.setData({
          stroageSize:"0"
        });
      }
    })
  },
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
  },
  onReachBottom: function () {
  
  },
  formatSize:function(size){
    if(size<1024){
      return size+"KB";
    }
    if(size>=1024){
      return (size/1024.0).toFixed(2)+"M"
    }
  },
  onShareAppMessage: function () {
  
  }
})