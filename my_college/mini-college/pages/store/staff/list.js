var app = getApp();
var layer = require('../../../utils/layer.js');
var staffController = require('../../../api/staffController.js');

Page({
  data: {
    stafflist: [],//员工列表
    userInfo: {}//当前用户信息
  },
  onLoad: function (options) {
    var that = this
    //layer.loading();

    //调用应用实例的方法获取全局数据
    app.getUserInfoSimple(function (userInfo) {
      that.setData({
        userInfo: userInfo
      })
      that.getStaff();
    })
  },

  delstafftip: function (e) {
    var that = this;
    var staff_id = e.currentTarget.dataset.staff_id;

    layer.confirm('是否删除该记录？', function (res) {
      if (res.confirm) {
        staffController.delete(staff_id, function (res) {
          layer.success("成功删除核销员！");
          that.getStaff();
        });
      }
    })

  },
  getStaff: function () {
    var that = this;
    staffController.getStaff(function (res) {
      that.setData({
        stafflist: res.data.data
      })
    });
  },
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()
    this.getStaff();
  },
  onShareAppMessage: function () {
    console.log(app.globalData.userInfo.card_id);
    return {
      title: '成为核销员',
      path: '/pages/store/staff/add?card_id=' + app.globalData.userInfo.card_id,
      success: function (res) {
        // 分享成功
      },
      fail: function (res) {
        // 分享失败
      }
    }
  },

})