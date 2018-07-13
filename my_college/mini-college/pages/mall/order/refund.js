
var sliderWidth = 96;
var app = getApp();
var layer = require('../../../utils/layer.js');
var dic = require('../../../utils/dictionary.js');
var wx_api = require('../../../api/wx/api.js');
var orderController = require('../../../api/mall/orderController.js');
var uploadController = require('../../../api/uploadController.js');


Page({
  data: {
    refundInfo:null,
    maxAmount:0,
    refundType: dic.refundType,
    deliveryStatus: dic.deliveryStatus,
    uploadImages: [],
    maxUploadImageNum: 3,
    imgWidth: 100,
    refundReason: dic.refundReason[1].reason,
    refundReasonIndex:0
  },
  onLoad: function (options) {
    var that=this;
    
    var refundInfo={
      type:1,
      delivery:2,
      orderid: options.orderid ? options.orderid:0,
      total: options.total ? options.total:0,
    }
    that.setData({
      refundInfo: refundInfo,
      maxAmount: parseFloat(refundInfo.total).toFixed(2)
    });
    that.getRefund(refundInfo.orderid);
    var res = app.getSystemInfo();
    that.setData({
      imgWidth: res.windowWidth / 3 - 18
    }); 
  },
  getRefund: function (orderid){
    var that=this;
    orderController.getRefund(orderid,function(res){
      if(!res.data.data){
        return;
      }
      that.setData({
        refundInfo:res.data.data,
        uploadImages: res.data.data.photo? res.data.data.photo.split(','):[],
      });
    });
  },
  //退款类型修改时触发
  refundTypeChange:function(e){
    var that=this;
    console.log(e.detail.value);
    that.data.refundInfo.type = e.detail.value;
    that.setData({
      refundInfo: that.data.refundInfo
    });
  },
  //收货状态修改时触发
  deliveryStatusChange:function(e){
    var that = this;
    console.log(e.detail.value);
    that.data.refundInfo.delivery = e.detail.value;
    for (var i = 0; i < dic.refundReason.length;i++){
      if (e.detail.value == dic.refundReason[i].delivery){
        that.setData({
          refundReason: dic.refundReason[i].reason,
          refundReasonIndex:0
        });
      }
    }
    that.setData({
      refundInfo: that.data.refundInfo
    });
  },
  //退货原因修改时触发
  refundReasonChange:function(e){
    var that = this;
    that.data.refundInfo.reason = that.data.refundReason[e.detail.value];
    that.setData({
      refundReasonIndex: e.detail.value
    });
  },
  //输入框内容发生变化时触发
  inputChange: function (e) {
    this.data.refundInfo[e.currentTarget.id] = e.detail.value
    console.log(this.data.refundInfo);
  },
  //选择图片并上传
  chooseImages: function () {
    var that = this;
    var count = that.data.maxUploadImageNum - that.data.uploadImages.length;
    wx_api.chooseImages(count,function(res){
      var tempFilePaths = res.tempFilePaths
      for (var i = 0; i < tempFilePaths.length; i++) {
        uploadController.image(tempFilePaths[i], function (res) {
          console.log(res)
          that.data.uploadImages.push(res.data.data[0]);
          that.setData({
            uploadImages: that.data.uploadImages
          });
        })
      }
    });  
  },
  //删除图片
  deleteImage: function (e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    that.data.uploadImages.splice(index, 1)
    that.setData({
      uploadImages: that.data.uploadImages
    });
  },
  //提交表单
  submit:function(){
     var that=this;
     var refundInfo = that.data.refundInfo;
     if (!refundInfo.total){
       layer.msg("请输入退款金额");
       return;
     }
     refundInfo.photo = that.data.uploadImages.join(",");
     orderController.refund(refundInfo.id?1:0,refundInfo,function(res){
        layer.msg("提交申请成功！",function(){
          wx.redirectTo({
            url: '/pages/mall/order/list?activeIndex=3',
          })
        });
       
     });
  }

})