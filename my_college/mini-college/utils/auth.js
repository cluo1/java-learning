var app = getApp();
var layer = require('layer.js');
//权限验证
module.exports = {
  //检查是否手机已验证
  //redirect_url  验证手机后跳回的路径  
  isVerifedMobile: function (redirect_url) {
    var url = '/pages/personal/bindMobile'
    if (redirect_url){
        redirect_url=encodeURIComponent(redirect_url);
        url += "?redirect_url=" + redirect_url
    }
    if (!app.globalData.userInfo.mobile || app.globalData.userInfo.ismobile == 0) {
      layer.confirm('需要先验证手机号码',function (res) {
        if (res.confirm) {
          wx.navigateTo({
            url: url
          })
        }
      });
      return false;
    }else{
      return true;
    }
    
  }
}