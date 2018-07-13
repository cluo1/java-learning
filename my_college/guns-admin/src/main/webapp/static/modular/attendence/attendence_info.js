/**
 * 初始化考勤详情对话框
 */
var AttendenceInfoDlg = {
    attendenceInfoData : {}
};

/**
 * 清除数据
 */
AttendenceInfoDlg.clearData = function() {
    this.attendenceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AttendenceInfoDlg.set = function(key, val) {
    this.attendenceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AttendenceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AttendenceInfoDlg.close = function() {
    parent.layer.close(window.parent.Attendence.layerIndex);
}

/**
 * 收集数据
 */
AttendenceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('cid')
    .set('endTime')
    .set('type')
    .set('createTime');
}

/**
 * 提交添加
 */
AttendenceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/attendence/add", function(data){
        Feng.success("添加成功!");
        window.parent.Attendence.table.refresh();
        AttendenceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.attendenceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AttendenceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/attendence/update", function(data){
        Feng.success("修改成功!");
        window.parent.Attendence.table.refresh();
        AttendenceInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.attendenceInfoData);
    ajax.start();
}

$(function() {

});
