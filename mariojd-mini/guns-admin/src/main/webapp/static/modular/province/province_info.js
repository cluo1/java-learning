/**
 * 初始化省份管理详情对话框
 */
var ProvinceInfoDlg = {
    provinceInfoData : {}
};

/**
 * 清除数据
 */
ProvinceInfoDlg.clearData = function() {
    this.provinceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProvinceInfoDlg.set = function(key, val) {
    this.provinceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProvinceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ProvinceInfoDlg.close = function() {
    parent.layer.close(window.parent.Province.layerIndex);
}

/**
 * 收集数据
 */
ProvinceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('num')
    .set('name')
    .set('status')
    .set('createtime');
}

/**
 * 提交添加
 */
ProvinceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/province/add", function(data){
        Feng.success("添加成功!");
        window.parent.Province.table.refresh();
        ProvinceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.provinceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ProvinceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/province/update", function(data){
        Feng.success("修改成功!");
        window.parent.Province.table.refresh();
        ProvinceInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.provinceInfoData);
    ajax.start();
}

$(function() {

});
