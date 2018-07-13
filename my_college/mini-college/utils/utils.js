

module.exports = {
  //验证130-139,150-159,180-189号码段的手机号码
  isMobile: function (mobile) {
    if (!mobile || mobile.length == 0 || mobile.length != 11) {
      return false;
    }
    // var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    // if (!myreg.test(mobile)) {
    //   return false;
    // }
    // return true
  },
  formatDistance: function (distance) {

    if (isNaN(distance) || distance == 0) {
      return '正在定位...'
    }
    if (0 < distance && distance < 1000) {
      return distance + 'm';
    }
    if (1000 <= distance && distance <= 100000) {
      return (distance / 1000.0).toFixed(2) + 'km'
    }
    if (100000 < distance && distance <= 500000) {
      return '>100km'
    }
  },
  formatTimestamp: function (timestamp, fmt) {
    if (!fmt) {
      fmt = "yyyy-MM-dd hh:mm:ss"
    }
    var date = new Date(timestamp * 1000)
    //var date = unixTimestamp.toLocaleString()
    var o = {
      "M+": date.getMonth() + 1, //月份 
      "d+": date.getDate(), //日 
      "h+": date.getHours(), //小时 
      "m+": date.getMinutes(), //分 
      "s+": date.getSeconds(), //秒 
      "q+": Math.floor((date.getMonth() + 3) / 3), //季度 
      "S": date.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
      if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
  },
  formatTime: function (date) {
    var year = date.getFullYear()
    var month = date.getMonth() + 1
    var day = date.getDate();
    return year + "-" + (month > 9 ? month : ("0" + month)) + "-" + day;
  },
  //根据值从数组中移除
  removeByValue: function (arr, val){
    for (var i = 0; i < arr.length; i++) {
      if (arr[i] == val) {
        arr.splice(i, 1);
        break;
      }
    }
  }
  

}