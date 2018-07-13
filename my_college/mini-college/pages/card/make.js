var app = getApp();
var util = require('../../utils/utils.js');
var layer = require('../../utils/layer.js');
var wx_api = require('../../api/wx/api.js');
var cardController = require('../../api/cardController.js');
var uploadController = require('../../api/uploadController.js');

Page({
  data: {
    showTopTips: false,
    industrylist: [],
    currIndex: 0,
    isAgree: false,
    userInfo: {},
    currIndustryId: 0,
    industry: '',
    uploadImages: [],
    maxUploadImageNum:9,
    imgWidth:100
  },
  //页面初始化
  onLoad: function (options) {
    //layer.loading();
    // 页面初始化 options为页面跳转所带来的参数
    var that = this
    //调用应用实例的方法获取全局数据
    app.getUserInfoSimple(function (userInfo) {
      //更新数据
      that.setData({
        userInfo: userInfo
      })

      that.getIndustry();

    })
    var res = app.getSystemInfo();
    that.setData({
      imgWidth: res.windowWidth / 3 - 18
    }); 
  },
  industryChange: function (e) {
    var that = this;
    console.log(e.detail.value);
    that.setData({
      currIndex: e.detail.value
    });
    that.data.currIndustryId = that.data.industrylist[that.data.currIndex].cid;
    that.data.userInfo.industry = that.data.industrylist[that.data.currIndex].name;
    that.setData({
      industry: that.data.userInfo.industry
    })

  },
  getlocation: function () {
    var that = this;
    wx.chooseLocation({
      success: function (res) {
        // success
        that.data.userInfo.address = res.address
        that.data.userInfo.com_lat = res.latitude;
        that.data.userInfo.com_lng = res.longitude;


        that.setData({
          userInfo: that.data.userInfo
        });
      },
      fail: function (res) {

        if (res.errMsg == "chooseLocation:fail auth deny") {
          layer.msg("请在【我的】-【设置】中允许获取您的地理位置");
        }
      }
    })
  },

  save: function () {
    var that = this;

    var userInfo = this.data.userInfo;
    if (!userInfo || !userInfo.name) {
      layer.msg("名称不能为空");
      return;
    }
    if (!userInfo.mobile) {
      layer.msg("请填写您的手机号");
      return;
    }
    if (!userInfo.company) {
      layer.msg("请填写您所在公司");
      return;
    }
    if (!userInfo.duty) {
      layer.msg("请填写您的职务");
      return;
    }
    // var reqData = { user_id: app.globalData.userInfo.user_id, access_token: app.globalData.userInfo.access_token, name: userInfo.name, mobile: userInfo.mobile, company: userInfo.company, industry: that.data.currIndustryId, duty: userInfo.duty, intro: userInfo.intro, avatar: userInfo.avatar, address: userInfo.address };
    userInfo.user_id = app.globalData.userInfo.user_id;
    userInfo.access_token = app.globalData.userInfo.access_token;
    userInfo.industry = that.data.currIndustryId;
    userInfo.photo = that.data.uploadImages.join(",");
    cardController.editCard(userInfo, function (res) {
      app.globalData.userInfo.avatar = userInfo.avatar;
      app.globalData.userInfo.name = userInfo.name;
      app.globalData.userInfo.duty = userInfo.duty;
      layer.msg("保存成功！", function (res) {
        app.globalData.userInfo.isChange = true;
        wx.navigateBack({
          delta: 1
        })
      })
    });

  },
  //获取我的名片详情
  getUserDetailInfo() {
    var that = this;
    cardController.getUserDetailInfo(app.globalData.userInfo.card_id, 0, function (res) {
      var data = that.data.industrylist;
      for (var i = 0; i < data.length; i++) {
        if (data[i].name == res.data.data.industry) {
          that.data.currIndustryId = data[i].cid;
        }
      }
      that.setData({ userInfo: res.data.data, industry: res.data.data.industry, uploadImages: res.data.data.photo})
    });
  },
  chooseAvatar: function () {
    var that = this;
    wx_api.chooseImages(1,function(res){
      // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片    
      var tempFilePaths = res.tempFilePaths
      uploadController.image(tempFilePaths[0], function (res) {
        console.log(res)
        that.data.userInfo.avatar = res.data.data[0];
        that.setData({
          userInfo: that.data.userInfo
        });
      })
    });
  },
  //选择图片并上传
  chooseImages: function () {
    var that = this;
    var count = that.data.maxUploadImageNum - that.data.uploadImages.length;
    wx_api.chooseImages(count, function (res) {
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
  deleteImage:function(e){
    var that=this;
    var index = e.currentTarget.dataset.index;
    that.data.uploadImages.splice(index, 1)
    that.setData({
      uploadImages: that.data.uploadImages
    });
  },
  //获取行业列表
  getIndustry: function () {
    var that = this;
    cardController.getIndustry(function (data) {
      that.setData({ industrylist: data })
      that.getUserDetailInfo();
    });
  },
  //输入框内容发生变化时触发
  inputChange: function (e) {
    this.data.userInfo[e.currentTarget.id] = e.detail.value;
    console.log(this.data.userInfo);
  },
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()
  }
});