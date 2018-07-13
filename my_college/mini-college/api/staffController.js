var layer = require('../utils/layer.js');
var request = require('../utils/request.js');

var app = getApp();
module.exports = {
  getStaff: function (callback) {

    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token }
    request.post(app.globalData.domain + '/Staff/getStaff', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "获取核销员信息失败！");
        return;
      }
      callback(res)
    })

  },
  delete: function (staff_id, callback) {

    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, staff_id: staff_id }
    request.post(app.globalData.domain + '/Staff/delStaff', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : '删除失败');
        return;
      }
      callback(res)
    })

  },
  applyStaff: function (card_id, callback) {
    layer.loading();
    var reqData = {
      user_id: app.globalData.userInfo.user_id,
      access_token: app.globalData.userInfo.access_token,
      card_id: card_id,
    }
    request.post(app.globalData.domain + '/Staff/applyStaff', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "成为核销员失败！");
        return;
      }
      callback(res)
    })    
  },

}