var app = getApp();
var layer = require('../../utils/layer.js');
Page({
  showOptions:function(){    
    layer.msg(app.globalData.options ? app.globalData.options:"参数为空");
  }
})