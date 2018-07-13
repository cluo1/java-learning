/**
 * 初始化用户详情对话框
 */
var MiniUserInfoDlg = {
    miniUserInfoData : {}
};

/**
 * 清除数据
 */
MiniUserInfoDlg.clearData = function() {
    this.miniUserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MiniUserInfoDlg.set = function(key, val) {
    this.miniUserInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MiniUserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MiniUserInfoDlg.close = function() {
    parent.layer.close(window.parent.MiniUser.layerIndex);
}

/**
 * 收集数据
 */
MiniUserInfoDlg.collectData = function() {
    this
    .set('id')
    .set('nickname')
    .set('avatar')
    .set('gender')
    .set('realName')
    .set('studentId')
    .set('openId')
    .set('phone')
    .set('role')
    .set('cids')
    .set('createtime');
}

/**
 * 提交添加
 */
MiniUserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/miniUser/add", function(data){
        Feng.success("添加成功!");
        window.parent.MiniUser.table.refresh();
        MiniUserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.miniUserInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MiniUserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/miniUser/update", function(data){
        Feng.success("修改成功!");
        window.parent.MiniUser.table.refresh();
        MiniUserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.miniUserInfoData);
    ajax.start();
}

$(function() {

});
