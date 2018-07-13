var layer = require('../utils/layer.js');
var request = require('../utils/request.js');

var app = getApp();
module.exports = {

  //获取投诉的所有选项
  getReason: function (callback) {

    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token }
    request.post(app.globalData.domain + '/Complain/reason', reqData, function (res) {
      layer.close();
      callback(res)
    })
  },
  //提交投诉
  submit: function (wid, rid, content, callback) {
    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, wid: wid, rid: rid, content: content }
    request.post(app.globalData.domain + '/Complain/submit', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : '提交失败');
        return;
      }
      callback(res)
    })

  },
}