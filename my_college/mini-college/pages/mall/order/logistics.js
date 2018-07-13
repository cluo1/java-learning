var app = getApp();
var layer = require('../../../utils/layer.js');
var util = require('../../../utils/utils.js');
var orderController = require('../../../api/mall/orderController.js');

Page({
  data: {
    express: [
      {
        "time": "2016-07-07 13:35:14",
        "context": "客户已签收"
      },
      {
        "time": "2016-07-07 09:10:10",
        "context": "离开 [北京石景山营业厅] 派送中，递送员[温]，电话[]"
      },
      {
        "time": "2016-07-06 19:46:38",
        "context": "到达 [北京石景山营业厅]"
      },
      {
        "time": "2016-07-06 15:22:32",
        "context": "离开 [北京石景山营业厅] 派送中，递送员[温]，电话[]"
      },
      {
        "time": "2016-07-06 15:05:00",
        "context": "到达 [北京石景山营业厅]"
      },
      {
        "time": "2016-07-06 13:37:52",
        "context": "离开 [北京_同城中转站] 发往 [北京石景山营业厅]"
      }
    ],
  },
  onLoad: function (options) {
    var that = this;
    var orderid = options.orderid ? options.orderid : 0;
    app.getUserInfoSimple(function (userInfo) {
      orderController.getDetail(orderid, function (res) {
        var data = res.data.data
        console.log(data)
        var express = data.express
        if (data.com || data.nu) {
          var context = data.com ? data.com : '物流单号：' + "[" + data.nu + "]";
          var time = util.formatTimestamp(data.delivery_time);
          express.push({ context: context, time: time })
        }
     

        if (!express || express <= 0) {
          layer.msg("暂无物流进度", function () {
            wx.navigateBack({
              delta: 1
            })
          });
          return;
        }
        console.log(express)
        that.setData({
          express: express
        });
      });
    })

  },
  //下拉刷新
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
    this.onLoad();
  },
})