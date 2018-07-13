var layer = require('../../utils/layer.js');
//调用微信接口
module.exports = {
  //1.0 调用授权登录接口
  login: function (callback) {
    wx.login({
      success: function (res0) {
        var code = res0.code;
        wx.getUserInfo({
          success: function (res) {
            callback(code, res)
          },
          fail: function (res) {
            console.log(res);
            layer.close();
            if (res.errMsg == "getUserInfo:fail auth deny") {
              layer.msg("您未授权获取您的信息，如需继续使用，请开启授权");
            } else {
              layer.msg("网络不佳，请稍后再试");
            }
          },
          complete: function (res) {
          }
        })
      },
      complete: function (res) {
        console.log("wx.login");
        console.log(res);
      }
    })
  },
  //2.0 调用授权获取用户地理位置接口
  getLocation: function (callback, fail) {
    wx.getLocation({
      success: function (res) {
        callback(res);
      },
      fail: function (res) {
        console.log('getLocation：fail=' + res);
        if (fail) {
          fail(res)
        }
      },
      complete: function () {

      }
    });
  },
  //2.0 调用获取图片信息的接口
  getImageInfo: function (src, callback, fail) {
    wx.getImageInfo({
      src: src,//图片的路径，可以是相对路径，临时文件路径，存储文件路径，网络图片路径
      success: function (res) {
        console.log("getImageInfo:success");

        var path = res.path;
        callback(res);
      },
      fail: function () {
        console.log("getImageInfo:fail");
        if (fail) {
          fail();
        }
      }
    })
  },
  //3.0 调用保存信息到手机通讯录的接口
  addPhoneContact: function (photoFilePath, firstName, mobilePhoneNumber, organization, title, remark) {
    if (!wx.addPhoneContact) {
      layer.msg('当前微信版本不支持保存名片');
      return false;
    }
    wx.addPhoneContact({
      photoFilePath: photoFilePath,//头像本地文件路径
      firstName: firstName,//名字
      mobilePhoneNumber: mobilePhoneNumber,
      organization: organization,//公司
      title: title,//职位
      remark: remark
    })
    return true;
  },
  //4.0 调用拨打电话
  makePhoneCall: function (mobile) {
    wx.makePhoneCall({
      phoneNumber: mobile,
      success: function () {
        console.log("拨打电话成功！")
      },
      fail: function () {
        console.log("拨打电话失败！")
      }
    })
  },
  //5.0 调起预览图片接口
  previewImage: function (images, current) {
    wx.previewImage({
      current: current,
      urls: images
    })
  },
  //6.0 调起微信内置地图查看位置
  openLocation: function (lat, lng, name, address, scale) {
    wx.openLocation({
      latitude: lat,
      longitude: lng,
      name: name,
      address: address,
      scale: scale ? scale : 28,
      success: function (res) {
        console.log(res);
      },
      fail: function (res) {
        console.log(res);
      },
    })
  },
  //7.0 调起微信选择位置的视图，需要用户授权获取地理位置
  chooseLocation: function (callback) {
    wx.chooseLocation({
      success: function (res) {
        // success
        callback(res)
      },
      fail: function (res) {
        if (res.errMsg == "chooseLocation:fail auth deny") {
          layer.msg("请在天天卡券的【我的】-【设置】中授权允许获取您的地理位置");
        }
      },
      complete: function (res) {
        // complete
      }
    })
  },
  //8.0 选择图片
  chooseImages: function (count, callback) {

    wx.chooseImage({
      count: count, // 最多可以选择的图片张数，默认9
      sizeType: ['original', 'compressed'], // original 原图，compressed 压缩图，默认二者都有
      sourceType: ['album', 'camera'], // album 从相册选图，camera 使用相机，默认二者都有
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片    
        callback(res);
        // var tempFilePaths = res.tempFilePaths
        // for (var i = 0; i < tempFilePaths.length; i++) {
        //   uploadController.image(tempFilePaths[i], function (res) {
        //     console.log(res)
        //     that.data.uploadImages.push(res.data.data[0]);
        //     that.setData({
        //       uploadImages: that.data.uploadImages
        //     });
        //   })
        // }

      },
      fail: function (res) {

      },
      complete: function (res) {
        console.log(res)
      }
    })

  },
  //9.0 调起微信支付
  requestPayment: function (timeStamp, nonceStr, packageStr, paySign, callback, fail) {
    layer.loading("调起微信支付...");
    console.log({ timeStamp: timeStamp, nonceStr: nonceStr, packageStr: packageStr, paySign: paySign });
    wx.requestPayment({
      'timeStamp': timeStamp.toString(),
      'nonceStr': nonceStr,
      'package': packageStr,
      'signType': 'MD5',
      'paySign': paySign,
      'success': function (res) {
        layer.close();
        console.log(res)
        callback(res)
      },
      'fail': function (res) {
        layer.close();
        console.log(res)
        if (fail) {
          fail(res);
        }
      }
    })

  }



}