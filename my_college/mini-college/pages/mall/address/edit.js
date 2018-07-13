var app = getApp();
var wx_api = require('../../../api/wx/api.js');
var layer = require('../../../utils/layer.js');
var util = require('../../../utils/utils.js');

var addressController = require('../../../api/mall/addressController.js');
Page({
  data: {
    address_id:0,
    addressInfo:{},
    redircet_url: '',
    addressFocus:false,
    saveText:"保存"

  },
  onLoad: function (options) {
    var that=this;
    that.data.address_id= options.address_id ? options.address_id:0;
    that.data.redirect_url = options.redirect_url ? options.redirect_url : '';
    that.setData({
      address_id: that.data.address_id,
      saveText: that.data.address_id > 0?that.data.saveText:"保存并使用该地址"
    });
    if (that.data.address_id>0){
      that.getDetail();
    }
  },
  save:function(){
     var that=this;
     var addressInfo = that.data.addressInfo;
     if (!addressInfo.name) {
       layer.msg("请填写收货人");
       return;
     }
     if (!addressInfo.tel || addressInfo.tel.length == 0 || addressInfo.tel.length != 11) {
       layer.msg("请填写正确的手机号");
       return;
     }
     if (!addressInfo.address ){
       layer.msg("请填写收货人地址");
       return;
     }
   
     var reqUrl = app.globalData.domain + '/address/add';     
     if (that.data.address_id != 0) {
       reqUrl = app.globalData.domain + '/address/edit';
     }
     addressInfo.user_id = app.globalData.userInfo.user_id;
     addressInfo.access_token = app.globalData.userInfo.access_token;
     addressController.edit(reqUrl,addressInfo,function(res){
       var redirect_url = '/pages/mall/address/list?redirect_url=' + encodeURIComponent("/pages/mall/order/confirm")
       if (that.data.redirect_url){
         redirect_url =decodeURIComponent(that.data.redirect_url)
       }
        wx.redirectTo({
          url: redirect_url,
        })
     });

  },
  getDetail:function(){
    var that=this;
    addressController.detail(that.data.address_id,function(res){
      that.setData({
        addressInfo:res.data.data
      });
    });
  },
  //输入框内容发生变化时触发
  inputChange: function (e) {
    this.data.addressInfo[e.currentTarget.id] = e.detail.value
    console.log(this.data.addressInfo);
  },
  delete: function (e) {
    var that = this;
    var id = that.data.address_id;
    layer.confirm('是否删除？', function (res) {
      if (res.confirm) {
        addressController.delete(id, function (res) {
          wx.navigateBack({
            delta: 1
          })
        });
      }
    })

  },
  //打开地图
  openMap: function () {
    var that = this;
    wx_api.chooseLocation(function(res){
      // success
      that.data.addressInfo.address = res.address
      that.data.addressInfo.lat = res.latitude;
      that.data.addressInfo.lng = res.longitude;
      that.setData({
        addressInfo: that.data.addressInfo,
        addressFocus:true
      });
    });    

  },
})