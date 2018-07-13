var layer = require('layer.js');
//HTTPS 请求 类
module.exports = {
    post: function (url, params, success, fail, complete) {
        wx.request({
            url: url,
            data: params,
            method: 'POST',
            header: {
                'content-type': 'application/json'
            },
            dataType: 'json',
            success: function (res) {
                if (success) {
                    success(res);
                }
            },
            fail: function (res) {
                if (fail) {
                    fail(res);
                }else{
                    layer.close();
                    layer.msg("网络不太好哦，请稍后再试试");
                }
            },
            complete: function (res) {
                if (complete) {
                    complete(res);
                }
            }
        })
    }
}