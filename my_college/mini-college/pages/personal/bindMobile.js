var app = getApp();
var layer = require('../../utils/layer.js');
var smsController = require('../../api/smsController.js');
Page({
  data: {
    redirect_url: '',
    mobile: "",
    time: 0,
    text: '获取验证码',
    t: null,
    code: ''
  },
  onLoad: function (options) {
   
    this.setData({
      mobile: options.mobile ? options.mobile : '',
      redirect_url: options.redirect_url ? decodeURIComponent(options.redirect_url) : ''
    });

  },

  //发送验证码
  SendVerifyCode: function () {

    var that = this;
    if (that.data.time > 0) {
      return;
    }
    smsController.send(that.data.mobile, function (res) {
      layer.success('验证码已发送');
      //更新数据
      that.setData({
        time: 60
      });
      that.countdown();
    });
  },
  //验证手机号码
  save: function () {
    var that = this;
    smsController.verify(that.data.mobile, that.data.code, function (res) {
      app.globalData.userInfo.mobile = that.data.mobile;
      app.globalData.userInfo.ismobile = 1;
      layer.msg("验证成功", function (res) {
        if (that.data.redirect_url) {
          wx.redirectTo({ url: that.data.redirect_url })
        } else {
          wx.navigateBack({ delta: 1 })
        }
      })
    });

  },

  phoneChange: function (e) {
    this.data.mobile = e.detail.value

  },
  codeChange: function (e) {
    this.data.code = e.detail.value

  },
  countdown: function () {
    var that = this;
    that.setData({
      time: --that.data.time
    });
    that.data.t = setTimeout(that.countdown, 1000);
    if (that.data.time <= 0) {
      //time = 60;
      clearTimeout(that.data.t);
    }
  }
})