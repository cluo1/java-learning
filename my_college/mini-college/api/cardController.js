var layer = require('../utils/layer.js');
var request = require('../utils/request.js');

var app = getApp();
module.exports = {
  //1.0 获取附近名片列表
  getList: function (keyword, distance, industry, page, callback) {
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, title: keyword, distance: distance, industry: industry, page: page };
    //console.log(reqData);
    request.post(app.globalData.domain + '/Card/getList', reqData, function (res) {
      callback(res);
    })
  },
  //2.0 获取名片详细信息
  getUserDetailInfo: function (card_id, coupon, callback) {
    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, coupon: coupon, card_id: card_id };
    request.post(app.globalData.domain + '/Card/getCard', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg("获取用户信息失败！");
        return;
      }
      callback(res);
    })
  },
  //3.0 获取我的二维码
  getMyQrcode: function (callback) {
    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token };
    request.post(app.globalData.domain + '/Card/getMyQrcode', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg("获取个人二维码失败！");
        return;
      }
      callback(res);
    })
  },
  //4.0 获取用户名片夹
  getUserCard: function (keyword, page, callback) {

    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, page: page, title: keyword };
    request.post(app.globalData.domain + '/Card/getUserCard', reqData, function (res) {
      if (res.data.code != 1) {
        layer.msg("获取用户信息失败！");
        return;
      }
      callback(res);
    })

  },
  //5.0 收藏到我的名片夹
  addUserCard: function (card_id, callback) {

    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, card_id: card_id };
    request.post(app.globalData.domain + '/Card/addUserCard', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "添加失败！");
        return;
      }
      callback(res);
    })
  },
  //6.0 移出我的名片夹
  delUserCard: function (card_id, callback) {
    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, card_id: card_id };
    request.post(app.globalData.domain + '/Card/delUserCard', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "删除失败！");
        return;
      }
      callback(res);
    })
  },
  //7.0 修改名片
  editCard: function (reqData, callback) {
    layer.loading();
    request.post(app.globalData.domain + '/Card/editCard', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "保存失败！");
        return;
      }
      callback(res);
    })
  },
  //8.0 获取行业列表
  getIndustry: function (callback) {
    
    var data = wx.getStorageSync("industry")
    if(data){      
      callback(data);
      return;
    }
    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token };
    request.post(app.globalData.domain + '/Card/getIndustry', reqData, function (res) {
      layer.close();
      if (res.data && res.data.data){
        wx.setStorage({
          key: "industry",
          data: res.data.data
        })   
      }
      callback(res.data.data);
    })
  },
  //9.0 设置用户权限 privacy:0 所有人可见 1 仅好友可见
  setPrivacy: function (privacy, callback) {
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, privacy: privacy };
    request.post(app.globalData.domain + '/Card/setPrivacy', reqData, function (res) {
      //console.log(res);
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "保存失败！");
        return;
      }
      callback(res);
    })
  },
  //11.0 获取查看我名片的人
  getLookers: function (page, num, callback) {
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, page: page, num: num };
    request.post(app.globalData.domain + '/User/getVisitor', reqData, function (res) {
      //console.log(res);
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "获取用户信息失败！");
        return;
      }
      callback(res);
    })

  },
  //12.0 获取所有交换名片的请求
  getApplys: function (page, num, callback) {
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, page: page, num: num };
    request.post(app.globalData.domain + '/User/getFriendMsg', reqData, function (res) {
      //console.log(res);
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "获取用户信息失败！");
        return;
      }
      callback(res);
    })

  },
  //13.0 获取保存过我的人
  getCollect: function (page, num, callback) {
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, page: page, num: num };
    request.post(app.globalData.domain + '/User/getCollect', reqData, function (res) {
      //console.log(res);
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "获取用户信息失败！");
        return;
      }
      callback(res);
    })

  },
  //14.0 发送交换名片的请求
  sendApply: function (card_id, callback) {
    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, card_id: card_id };
    request.post(app.globalData.domain + '/User/addFriend', reqData, function (res) {
      //console.log(res);
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "发起失败！");
        return;
      }
      callback(res);
    })

  },
  //15.0 获取当前用户有多少个交换请求
  getApplysCount: function (callback) {
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token };
    request.post(app.globalData.domain + '/User/getFriendMsgCount', reqData, function (res) {
      //console.log(res);
      callback(res);
    })
  },
  //16.0 处理请求(1、同意；-1、拒绝)
  processApply: function (msg_id, status, callback) {
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, msg_id: msg_id, status: status };
    request.post(app.globalData.domain + '/User/checkFriendMsg', reqData, function (res) {
      //console.log(res);
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "操作失败！");
        return;
      }
      callback(res);
    })

  },
  //17.0 获取人气排行榜
  getRank: function (callback) {
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token };
    request.post(app.globalData.domain + '/Card/getRank', reqData, function (res) {
      //console.log(res);
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : "获取排行榜失败！");
        return;
      }
      callback(res);
    })

  },
  //数量统计  id:统计ID，1、优惠券领取数量；2、名片创建数量
  getStat:function(id,callback){
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, id: id };
    request.post(app.globalData.domain + '/Card/getStat', reqData, function (res) {

      if (res.data.code != 1) {        
        return;
      }
      callback(res);
    })
  }
}