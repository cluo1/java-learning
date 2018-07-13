var layer = require('../utils/layer.js');
var request = require('../utils/request.js');

var app = getApp();
module.exports = {

  //获取所有优惠券
  getAllCoupon: function (title, industry, page, callback) {
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, industry: industry, title: title, lng: app.globalData.userInfo.lng, lat: app.globalData.userInfo.lat, page: page }
    request.post(app.globalData.domain + '/Coupon/getAllCoupon', reqData, function (res) {
      if (res.data.code != 1) {
        layer.msg("获取优惠券信息失败！");
        return;
      }
      callback(res);
    })
  },

  //获取我发布的优惠券
  getCoupon: function (status, page, callback) {

    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, status: status, page: page }
    request.post(app.globalData.domain + '/Coupon/getCoupon', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg("获取优惠券信息失败！");
        return;
      }
      callback(res);
    })

  },
  //获取我领取的优惠券
  getUserCoupon: function (status, page, callback,lat,lng) {
    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, status: status, page: page, lat: lat ? lat : 0, lng: lng ? lng : 0}
    request.post(app.globalData.domain + '/Coupon/getUserCoupon', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg("获取优惠券信息失败！");
        return;
      }
      callback(res);
    })

  },
  //获取优惠券详情
  getCouponInfoByWid: function (wid, callback) {

    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, wid: wid }
    request.post(app.globalData.domain + '/Coupon/getCouponInfo', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg("获取优惠券信息失败！");
        return;
      }
      callback(res);
    })
  },
  //获取优惠券详情
  getCouponByCid: function (cid, callback) {

    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, cid: cid };

    request.post(app.globalData.domain + '/Coupon/getCouponOne', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg("获取优惠券信息失败！");
        return;
      }
      callback(res);
    })
  },
  //领取优惠券
  receiveCoupon: function (cid, callback) {

    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, cid: cid };
    request.post(app.globalData.domain + '/Coupon/receiveCoupon', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : '领取优惠券失败');
        return;
      }
      callback(res);
    })
  },
  //使用优惠券（验证优惠券）
  verifyCoupon: function (wid, callback) {

    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, wid: wid };
    request.post(app.globalData.domain + '/Coupon/verifyCoupon', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        // console.log("reqData="+reqData);
        // console.log("res="+res);
        layer.msg(res.data.message ? res.data.message : '核销优惠券失败');
        return;
      }
      callback(res);

      // layer.msg("核销优惠券成功！", function (res) {
      //   wx.switchTab({ url: '/pages/card/my' })
      // })


    
    })

  },
  //删除优惠券
  delCoupon: function (cid, callback) {
    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, cid: cid };
    request.post(app.globalData.domain + '/Coupon/delCoupon', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : '操作失败！');
        return;
      }
      callback(res);
    })    
  },
  //修改优惠券状态
  changeState: function (status, cid, callback) {
    layer.loading();
    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, status: status, cid: cid };
    request.post(app.globalData.domain + '/Coupon/setCouponStatus', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : '操作失败！');
        return;
      }
      callback(res);
    })
    
  },
  editCoupon: function (reqUrl, reqData, callback) {
    layer.loading();  
    request.post(reqUrl, reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : '保存失败！');
        return;
      }
      callback(res);
    })   
  },
  //获取使用时的优惠券信息（二维码）
  getUseCoupon(wid, callback) {
    layer.loading();

    var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, wid: wid };
    request.post(app.globalData.domain + '/Coupon/useCoupon', reqData, function (res) {
      layer.close();
      if (res.data.code != 1) {
        layer.msg(res.data.message ? res.data.message : '获取优惠券信息失败！');
        return;
      }
      callback(res);
    })
    
  },
  //获取领取优惠券的所有用户信息
  getReceiveList: function (cid, status, page, callback) {
    var params = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, cid: cid, status: status, page: page };
    request.post(app.globalData.domain + '/Coupon/getReceiveList', params, function (res) {
      if (res.data.code != 1) {
        layer.msg("获取用户信息失败！");
        return;
      }
      callback(res);
    });

  }
}