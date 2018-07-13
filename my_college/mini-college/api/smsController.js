var layer = require('../utils/layer.js');
var request = require('../utils/request.js');

var app = getApp();
module.exports = {
    //发送短信
    send: function (mobile, callback) {
        layer.loading();
        var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, mobile: mobile, tpl: 10010 }
        request.post(app.globalData.domain + '/Sms/send', reqData, function (res) {
          layer.close();
          if (res.data.code != 1) {
            layer.msg(res.data.message ? res.data.message : '发送短信失败');
            return;
          }
          callback(res)
        })       
    },
    //验证
    verify: function (mobile,code,callback) {
      
        layer.loading();
        var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, mobile: mobile, tpl: 10010, code: code }
        request.post(app.globalData.domain + '/Sms/verify', reqData, function (res) {
          layer.close();
          if (res.data.code != 1) {
            layer.msg(res.data.message ? res.data.message : '验证失败');
            return;
          }
          callback(res)
        })
                
    },
}