var layer = require('../../utils/layer.js');
var request = require('../../utils/request.js');
var app = getApp();
module.exports = {

  //1.0 获取商品列表
  getList: function (page, callback) {
    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, page: page };
    console.log(reqData);
    request.post(app.globalData.domain + '/Goods/getList', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "获取商品信息失败！");
        return;
      }
      callback(res);
    })
  },
  //2.0 获取商品详情
  detail: function (pro_id, callback) {

    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, gid: pro_id };
    request.post(app.globalData.domain + '/Goods/getGoodsInfo', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "获取商品信息失败！");
        return;
      }
      callback(res);
    })

  }


}