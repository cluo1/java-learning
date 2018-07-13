var layer = require('../utils/layer.js');
var request = require('../utils/request.js');

var app = getApp();
module.exports = {
  //1.0 获取门店分类
  getCategory: function (callback){
    
    var data = wx.getStorageSync("store_category")
    if (data) {      
      callback(data);
      return;
    }
    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, type: 1 };
    console.log(reqData);
    request.post(app.globalData.domain + '/Store/getCategory', reqData, function (res) {
      layer.close();
      if (res.data && res.data.data) {
        wx.setStorage({
          key: "store_category",
          data: res.data.data
        })
      }
      callback(res.data.data);
    })
  },
  //2.0 获取用户所有门店
  getUserStore: function (callback) {
    layer.loading();
   
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token }
    request.post(app.globalData.domain + '/Store/getUserStore', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "获取门店信息失败！");
        return;
      }
      callback(res)
    })
  },
  //3.0 获取门店信息
  getStore: function (store_id, callback) {

    layer.loading();
    var reqData = {
      user_id: app.globalData.userInfo.user_id,
      access_token: app.globalData.userInfo.access_token,
      store_id: store_id
    }
    request.post(app.globalData.domain + '/Store/getStore', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "获取门店信息失败！");
        return;
      }
      callback(res)
    })

  },
  //4.0 新增/编辑门店
  editStore: function (reqUrl, reqData, callback) {
    layer.loading();
    request.post(reqUrl, reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "保存失败！");
        return;
      }
      callback(res)
    })  
  },
  //5.0 删除门店
  delete: function (store_id, callback) {

    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, store_id: store_id }
    request.post(app.globalData.domain + '/store/delStore', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "删除失败！");
        return;
      }
      callback(res)
    })  

  },
  //6.0 获取门店信息
  getstore: function (store_id, callback) {

    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, store_id: store_id }
    request.post(app.globalData.domain + '/store/getstore', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "获取门店信息失败！");
        return;
      }
      callback(res)
    })
    
  },
  
}