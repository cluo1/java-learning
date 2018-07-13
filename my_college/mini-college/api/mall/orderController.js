var layer = require('../../utils/layer.js');
var request = require('../../utils/request.js');

var app = getApp();
module.exports = {

  //1.0 更新购物车
  updateCart: function (cart_info, callback) {
    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, cart_info: cart_info};
    request.post(app.globalData.domain + '/order/updateCart', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "加入购物车失败！");
        return;
      }
      callback(res);
    })
  },

  //2.0 获取购物车信息
  getCart: function (callback) {
    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token};
    request.post(app.globalData.domain + '/order/getCart', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "获取购物车信息失败！");
        return;
      }
      callback(res);
    })
  },

  //3.0 提交订单
  submit: function (address_id, remark, callback) {
    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, address_id: address_id, remark: remark };
    request.post(app.globalData.domain + '/order/cartConfirm', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "提交订单失败！");
        return;
      }
      callback(res);
    })
  },

  //3.0 获取我的订单
  getList: function (status, pageIndex, callback) {
 
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, page: pageIndex, status: status};
    console.log(reqData);
    request.post(app.globalData.domain + '/order/getOrder', reqData, function (res) {
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message :"获取订单信息失败！");
        return;
      }
      callback(res);
    })
  },
  getDetail: function (orderid, callback) {
    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, orderid: orderid };
    //console.log(reqData);
    request.post(app.globalData.domain + '/order/getOrderInfo', reqData, function (res) {
      layer.close();
      
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "获取订单信息失败！");
        return;
      }
      callback(res);
    })
  },
 //4.0 设置我的订单状态
  setStatus: function (orderid, status, reason,callback){
    layer.loading();
    
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, orderid: orderid, status: status, reason: reason };
    console.log(reqData);
    request.post(app.globalData.domain + '/order/setStatus', reqData, function (res) {
      layer.close();
      
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message :"取消订单失败！");
        return;
      }
      callback(res);
    })
  },
 //5.0 提交退款申请(type  0:新增  1：修改)
  refund: function (type,reqData,callback){    
    layer.loading();
    
    reqData.user_id = app.globalData.userInfo.user_id;
    reqData.access_token = app.globalData.userInfo.access_token
    //console.log(reqData);
    var reqUrl = app.globalData.domain + (type == 0 ? '/order/refund':"/order/updateRefund");
    request.post(reqUrl, reqData, function (res) {
      layer.close();
      
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "提交退款申请失败！");
        return;
      }
      callback(res);
    })
  },
  //6.0 获取退款申请信息
  getRefund:function(orderid,callback){
    layer.loading();
    
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, orderid: orderid };
    //console.log(reqData);
    request.post(app.globalData.domain + '/order/getRefund', reqData, function (res) {
      layer.close();
      
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "获取退款信息失败！");
        return;
      }
      callback(res);
    })
  },
  //7.0 取消申请
  cancelRefund: function (orderid, callback){
    layer.loading();
    
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, orderid: orderid };
    //console.log(reqData);
    request.post(app.globalData.domain + '/order/cancelRefund', reqData, function (res) {
      layer.close();
      
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "撤销失败！");
        return;
      }
      callback(res);
    })
  },
  //8.0 获取支付签名
  getPaySignature(type, orderid, form_id,callback){
    layer.loading();
    
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, type: type, orderid: orderid, form_id: form_id};
    //console.log(reqData);
    request.post(app.globalData.domain + '/pay/signature', reqData, function (res) {
      layer.close();
      
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "获取支付签名失败！");
        return;
      }
      callback(res);
    })
  },
  getRanking(callback) {
    //layer.loading();

    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token };
    //console.log(reqData);
    request.post(app.globalData.domain + '/order/ranking', reqData, function (res) {
      //layer.close();

      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "获取排行榜失败！");
        return;
      }
      callback(res);
    })
  }
}