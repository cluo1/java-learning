var layer = require('../utils/layer.js');

var app = getApp();
module.exports = {
    //上传图片
    image: function (filePath,callback) {        
        layer.loading();
        //上传到服务器
        wx.uploadFile({
            url: app.globalData.domain + '/Upload/image',
            filePath: filePath,
            name: 'file',
            formData: { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, code: filePath },
            success: function (res) {
                layer.close();
                res.data = JSON.parse(res.data);
                if (res.data.code != 1||res.data.data.length<=0) {
                  console.log(res)
                    layer.msg("上传图片失败！");
                    return;
                }
                callback(res);
              
            },
            fail: function (res) {
                layer.close();
                console.log(res)
                layer.msg("上传图片失败！");
                return '';
            }
        })

    },
}