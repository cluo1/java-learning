var layer = require('../../utils/layer.js');
var request = require('../../utils/request.js');

var app = getApp();
module.exports = {

  //1.0 获取收货地址列表
   getList:function(callback){
     //layer.loading();
     var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token};
     console.log(reqData);
     request.post(app.globalData.domain + '/address/getAddr', reqData, function (res) {
       //layer.close();
       if (res.data.code != 1) {
         layer.msg(res.data.message ? res.data.message : "获取地址信息失败！");
         return;
       }
       callback(res);
     })
   },
   

   //2.0 新增/编辑收货地址
   edit: function ( reqUrl,reqData, callback) {
     layer.loading();
     request.post(reqUrl, reqData, function (res) {
       layer.close();
       if (res.data.code != 1) {
         layer.msg(res.data.message ? res.data.message : "保存失败！");
         return;
       }
       callback(res);
     })
   },
   //3.0 设置默认地址
   setDefault:function(address_id,callback){
     
     var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, id:address_id };
     console.log(reqData);
     request.post(app.globalData.domain + '/address/set', reqData, function (res) {
      
       if (res.data.code != 1) {
         layer.msg(res.data.message ? res.data.message : "设置默认地址失败！");
         return;
       }
       callback(res);
     })
   },
   //4.0 删除收货地址
   delete: function (address_id, callback){
     layer.loading();
     var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, id: address_id};
     console.log(reqData);
     request.post(app.globalData.domain + '/address/delete', reqData, function (res) {
       layer.close();
       if (res.data.code != 1) {
         layer.msg(res.data.message ? res.data.message : "删除地址失败！");
         return;
       }
       callback(res);
     })

   },
   //5.0 获取商品详情
   detail: function (address_id, callback) {
     layer.loading();
     var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, id: address_id };
     console.log(reqData);
     request.post(app.globalData.domain + '/address/getAddrInfo', reqData, function (res) {
       layer.close();
       if (res.data.code != 1) {
         layer.msg(res.data.message ? res.data.message : "获取地址信息失败！");
         return;
       }
       callback(res);
     })
   },

}