var app = getApp();
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var reportController = require('../../api/reportController.js');
Page({
  data: {
    remark: '',
    rid: 0,
    wid: 0,
    wordCount: 0,
    categorys: []
  },
  onLoad: function (options) {
    //layer.loading();

    var that = this;
    that.data.wid = options.wid ? options.wid : 0;
    if (that.data.wid == 0) {
      wx.navigateBack({ delta: 1 });
    }

    that.getReason();

  },
  checkChange: function (e) {
    console.log(e);
    var that = this;
    var id = e.currentTarget.dataset.id;
    var categorys = that.data.categorys;
    that.data.rid = id;
    for (var i = 0; i < categorys.length; i++) {
      categorys[i].checked = categorys[i].rid == id;
    }
    that.setData({
      categorys: categorys
    });
  },
  //输入框内容发生变化时触发
  inputChange: function (e) {
    var remark = e.detail.value;
    this.setData({
      wordCount: remark.length,
      remark: remark
    });

  },
  getReason: function () {

    var that = this;
    reportController.getReason(function (res) {
      var categorys = res.data.data;
      that.setData({
        categorys: categorys
      });
    });


  },
  submit: function () {
    var data = this.data;
    reportController.submit(data.wid, data.rid, data.remark, function (res) {
      layer.msg("保存成功！", function (res) {
        wx.navigateBack({ delta: 1 })
      })
    });
  }
})