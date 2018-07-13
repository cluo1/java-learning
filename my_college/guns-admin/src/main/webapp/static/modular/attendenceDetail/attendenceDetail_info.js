/**
 * 初始化考勤明细详情对话框
 */
var AttendenceDetailInfoDlg = {
    attendenceDetailInfoData : {}
};

/**
 * 清除数据
 */
AttendenceDetailInfoDlg.clearData = function() {
    this.attendenceDetailInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AttendenceDetailInfoDlg.set = function(key, val) {
    this.attendenceDetailInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AttendenceDetailInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AttendenceDetailInfoDlg.close = function() {
    parent.layer.close(window.parent.AttendenceDetail.layerIndex);
}

/**
 * 收集数据
 */
AttendenceDetailInfoDlg.collectData = function() {
    this
    .set('id')
    .set('aid')
    .set('sid')
    .set('punch')
    .set('punchTime');
}

/**
 * 提交添加
 */
AttendenceDetailInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/attendenceDetail/add", function(data){
        Feng.success("添加成功!");
        window.parent.AttendenceDetail.table.refresh();
        AttendenceDetailInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.attendenceDetailInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AttendenceDetailInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/attendenceDetail/update", function(data){
        Feng.success("修改成功!");
        window.parent.AttendenceDetail.table.refresh();
        AttendenceDetailInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.attendenceDetailInfoData);
    ajax.start();
}

$(function() {

});
