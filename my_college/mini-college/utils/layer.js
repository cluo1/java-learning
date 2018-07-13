
//弹出提示层 工具类
module.exports = {
  loading: function (msg) {
    if (wx.showLoading) {
      wx.showLoading({ title: msg ? msg : '加载中...', mask: true })
    } else {
      wx.showToast({ title: msg ? msg : "加载中", icon: 'loading', duration: 2000 })
    }
  },
  msg: function (msg, callback) {
    wx.showModal({ title: "",
     content: msg,
      showCancel:false,
       success: function (res) {
        //  console.log(res)
          if (callback)
           { 
             callback(res)
              }
               } 
               })
  },
  success: function (msg) {
    //暂未使用
    //因为android能正常使用，ios 不起作用
    var systemInfo = wx.getStorageSync("systemInfo")
    if (systemInfo.platform!="ios"){
    wx.showToast({ title: msg ? msg : "成功", icon:"success", duration: 2000 })
    }else{
      wx.showModal({ title: "", content: msg ? msg : "成功", showCancel:false})
    }
  },
  error: function (msg) {
    wx.showModal({ title: "", content: msg ? msg : "错误", showCancel: false })
  },
  close: function () {
    if (wx.hideLoading) {
      wx.hideLoading();
    }
  },
  confirm: function (msg, callback) {
    wx.showModal({
      title: '',
      content: msg,
      success: function (res) {
        if (callback && typeof callback == "function") { callback(res) }
      }
    });
  }
}