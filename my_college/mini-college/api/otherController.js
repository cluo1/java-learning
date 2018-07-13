
var request = require('../utils/request.js');
var app = getApp();
module.exports = {
  //更新分享次数
  updateShare: function (callback) {
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token }
    request.post(app.globalData.domain + '/order/updateShare', reqData, function (res) {
      if (callback) {
        callback(res)
      }
    })

  },
}