var app = getApp();
var layer = require('../../../utils/layer.js');
var addressController = require('../../../api/mall/addressController.js');

Page({
  data: {
    addresslist: [],
    redircet_url:'',
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    var that = this;
    that.data.redirect_url = options.redirect_url? decodeURIComponent(options.redirect_url):'';
    that.getList();
  },

  getList: function () {
    var that = this;
    addressController.getList(function (res) {
      // if (res.data.data.length <= 0) {
      //   // wx.navigateTo({
      //   //   url: '/pages/mall/address/edit',
      //   // })
      //   return;
      // }
      that.setData({
        addresslist: res.data.data
      });
    });
  },

  redirectEdit: function (e) {
    var id = e.currentTarget.dataset.id;
    wx.redirectTo({
      url: '/pages/mall/address/edit?address_id=' + id,
    })
  },
  redirectAdd:function(){
    var redirect_url = encodeURIComponent("/pages/mall/order/confirm")
    wx.redirectTo({
      url: '/pages/mall/address/edit?redirect_url=' + redirect_url,
    })
  },
  delete: function (e) {
    var that = this;
    var id = e.currentTarget.dataset.id;
    layer.confirm('是否删除？', function (res) {
      if (res.confirm) {
        addressController.delete(id, function (res) {
          that.getList();
        });
      }
    })

  },
  redirectOrderConfirm:function(e){
    var that=this;
    var redirect_url=that.data.redirect_url;   
    if (redirect_url){
      var address_id = e.currentTarget.dataset.id;
      redirect_url = redirect_url + "?address_id=" + address_id
       wx.redirectTo({
         url: redirect_url,
       })
    }
  },
  setDefault: function (e) {

    var that = this;
    var id = e.currentTarget.dataset.id;
    addressController.setDefault(id, function (res) {
      var addresslist = that.data.addresslist;
      for(var i=0;i<addresslist.length;i++){
        addresslist[i].def = addresslist[i].id==id?"1":"0";
      }
      that.setData({
        addresslist: addresslist
      });
    });
  },
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()
    this.getList();
  }
})