var app = getApp();
var layer = require('../../utils/layer.js');
var wx_api = require('../../api/wx/api.js');
var cardController = require('../../api/cardController.js');
var uploadController = require('../../api/uploadController.js');
var storeController = require('../../api/storeController.js');
Page({
  data: {
    store_id: 0,
    logo: '',
    address: "",
    isShowLocation: false,
    isAgreement: true,//是否同意协议
    store: {},
    industrylist: [],
    industry: '',
    currIndustryId: 0,
    subclass: [],//子分类列表
    // subclassId:0,//当前选择子分类id
    uploadImages: [],
    maxUploadImageNum: 9,
    imgWidth: 100
  },
  onLoad: function (options) {

    //layer.loading();
    var that = this;
    that.data.store_id = options.store_id ? options.store_id : 0;
    //调用应用实例的方法获取全局数据
    app.getUserInfoSimple(function (userInfo) {
      //layer.close();
      storeController.getCategory(function (data) {
        that.setData({ industrylist: data })
        //更新数据
        if (that.data.store_id != 0) {
          that.getStores(that.data.store_id);
        }
      });
    })

    var res = app.getSystemInfo();
    that.setData({
      imgWidth: res.windowWidth / 3 - 18
    });

  },
  getStores: function (store_id) {
    var that = this;
    storeController.getStore(store_id, function (res) {
      var store = res.data.data;
      var data = that.data.industrylist;
      var subclass = [];
      for (var i = 0; i < data.length; i++) {
        if (data[i].name == res.data.data.industry) {
          that.data.currIndustryId = data[i].cid;

          subclass = data[i].small
        }
      }
      if (subclass && subclass.length > 0) {
        subclass.push({ cid: 0, name: "其他" });
        for (var i = 0; i < subclass.length; i++) {
          subclass[i].checked = subclass[i].cid == res.data.data.subclass;
        }
      }
      //更新数据
      that.setData({
        logo: store.logo,
        store: store,
        address: store.address,
        isShowLocation: store.address ? true : false,
        industry: store.industry,
        uploadImages: store.photo,
        subclass: subclass
      })
    });
  },

  industryChange: function (e) {
    var that = this;
    var cid = e.detail.value;
    var industrylist = that.data.industrylist;
    console.log(cid);
    that.data.currIndustryId = industrylist[cid].cid;
    that.data.store.industry = industrylist[cid].cid;
    var subclass = industrylist[cid].small;
    var isExist=false;
    for(var i=0;i<subclass.length;i++){
      if (subclass[i].cid==0){
        isExist=true;
        break;
      }
    }
    if(!isExist){
      subclass.push({ cid: 0, name: "其他" });
    }
    that.setData({
      industry: industrylist[cid].name,
      subclass: subclass
    })


  },
  //选择子分类
  subclassChange: function (e) {
    var that = this;
    var cid = e.currentTarget.dataset.cid
    var subclass = that.data.subclass;
    for (var i = 0; i < subclass.length; i++) {
      subclass[i].checked = subclass[i].cid == cid;
    }
    that.data.store.subclass = cid
    that.setData({
      subclass: subclass
    })
  },
  //输入框内容发生变化时触发
  inputChange: function (e) {
    this.data.store[e.currentTarget.id] = e.detail.value
    console.log(this.data.store);
  },
  //是否同意协议
  checkboxChange: function (e) {
    this.setData({
      isAgreement: !this.data.isAgreement
    });
  },

  //打开地图
  openMap: function () {
    var that = this;
    wx.chooseLocation({
      success: function (res) {
        // success
        that.data.store.address = res.address
        that.data.store.lat = res.latitude;
        that.data.store.lng = res.longitude;


        that.setData({
          address: res.address,
          isShowLocation: true
        });
      },
      fail: function (res) {
        if (res.errMsg == "chooseLocation:fail auth deny") {
          layer.msg("请在【我的】-【设置】中允许获取您的地理位置");
        }
      },
      complete: function (res) {
        // complete
      }
    })
  },
  //保存店铺信息
  save: function () {
    if (!this.data.store.logo) {
      layer.msg("请上传门店logo");
      return;
    };
    if (!this.data.store.name) {
      layer.msg("请输入门店名称");
      return;
    };
    if (!this.data.store.intro) {
      layer.msg("请输入门店简介");
      return;
    };
    if (!this.data.store.tel) {
      layer.msg("请输入门店电话");
      return;
    };
    if (!this.data.store.linkman) {
      layer.msg("请输入联系人");
      return;
    };
    if (!this.data.store.address) {
      layer.msg("请选择门店地址");
      return;
    };
    if (!this.data.isAgreement) {
      layer.msg("需同意用户服务协议");
      return;
    }
    var that = this;
    that.data.store.user_id = app.globalData.userInfo.user_id;
    that.data.store.photo = that.data.uploadImages.join(",");
    that.data.store.access_token = app.globalData.userInfo.access_token;
    that.data.store.industry = that.data.currIndustryId;
    var reqUrl = app.globalData.domain + '/Store/addStore';
    var message = "添加成功！立即去发布优惠券？"
    if (that.data.store_id != 0) {
      that.data.store.store_id = that.data.store_id
      reqUrl = app.globalData.domain + '/Store/editStore';
      message = "保存成功！";
    }
    storeController.editStore(reqUrl, that.data.store, function (res) {
      if (that.data.store_id == 0) {
        layer.confirm(message, function (res) {
          wx.redirectTo({ url: res.confirm ? '/pages/coupon/release' : '/pages/store/list' })
        })
      } else {
        layer.msg(message, function () {
          wx.redirectTo({ url: '/pages/store/list' })
        });
      }
    })
  },

  //选择并上传logo图
  chooseLogo: function () {
    var that = this;
    wx_api.chooseImages(1,function(res){
      var tempFilePaths = res.tempFilePaths
      uploadController.image(tempFilePaths[0], function (res) {
        console.log(res)
        that.data.store.logo = res.data.data[0];
        that.setData({
          logo: that.data.store.logo
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

 //删除图片
  deleteImage: function (e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    that.data.uploadImages.splice(index, 1)
    that.setData({
      uploadImages: that.data.uploadImages
    });
  },
  //下拉刷新
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh()

  }
})