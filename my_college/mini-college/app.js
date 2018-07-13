var layer = require('/utils/layer.js');
var request = require('/utils/request.js');
var api = require('/api/wx/api.js');

App({
    onLaunch: function (options) {
        var that = this;
        var res = that.getSystemInfo();
        //检查用户安装的微信版本
        if (Number(res.version[0] + res.version[2] + res.version[4]) < 656) {
            layer.msg("当前微信版本过低，请升级到最新微信版本后重试。");
        }
    },
    globalData: {
        token: null,//当前登录用户信息
    },
    //获取用户信息（调起微信授权获取用户信息（如果用户拒绝授权，不能继续下一步操作））
    getUserInfo: function (callback) {
        var that = this;
        layer.loading();

        api.login(function (code, res) {
            layer.close();
            var encryptedData = res.encryptedData
            var iv = res.iv;
            layer.loading();

            // var userInfo = that.globalData.userInfo
            // if (userInfo && userInfo.lng) {
            //   that.login(code, encryptedData, iv, callback);
            //   return;
            // }
            // api.login(function (res) {
            //   layer.close();
            //   that.login(code, encryptedData, iv, callback);
            // });
            that.getUserInfoSimple(callback);
        });
    },

    //获取用户信息（不获取用户地理位置）
    getUserInfoSimple: function (callback) {
        console.log("getUserInfoSimple");
        var that = this;
        var timestamp = Math.round(new Date().getTime() / 1000);
        //检查access_token是否已过期，过期则重新登录
        if (that.globalData.userInfo && that.globalData.userInfo.expires > timestamp) {
            // layer.close();
            typeof callback == "function" && callback(this.globalData.userInfo)
        } else {
            api.login(function (code, res) {
                that.login(code, res.encryptedData, res.iv, callback);
            });
        }
    },
    getSystemInfo: function () {
        var res = wx.getStorageSync("systemInfo");
        if (!res) {
            res = wx.getSystemInfoSync();
            wx.setStorage({
                key: "systemInfo",
                data: res
            })
        }
        return res;
    },

    //平台登录
    login: function (code, encryptedData, iv, callback) {
        var that = this;
        layer.loading();
        var reqData = { code: code, data: encryptedData, iv: iv}
        //console.log(reqData);
        request.post('https://mini.mariojd.cn/mini/authorize', reqData, function (res) {
            layer.close();
            console.log(res)
            if (res.data.code != 1 || !res.data.data) {
                layer.msg("登录失败！请尝试关闭微信重新进入");
                return;
            }
            that.globalData.roken = token;
            typeof callback == "function" && callback(that.globalData.userInfo);
        })
    },

});