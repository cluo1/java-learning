/**
 * 初始化通知详情对话框
 */
var AnnouncementInfoDlg = {
    announcementInfoData: {},
    editor: null,
    validateFields: {
        title: {
            validators: {
                notEmpty: {
                    message: '标题不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 20,
                    message: '标题名称过长'
                },
            }
        }
    }
};

/**
 * 清除数据
 */
AnnouncementInfoDlg.clearData = function () {
    this.announcementInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AnnouncementInfoDlg.set = function (key, val) {
    this.announcementInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AnnouncementInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AnnouncementInfoDlg.close = function () {
    parent.layer.close(window.parent.Announcement.layerIndex);
}

/**
 * 收集数据
 */
AnnouncementInfoDlg.collectData = function () {
    this.announcementInfoData['content'] = encodeURIComponent(AnnouncementInfoDlg.editor.txt.html());
    this
        .set('id')
        .set('cid')
        .set('publisher')
        .set('uid')
        .set('num')
        .set('title')
        .set('status')
        .set('createtime');
}

/**
 * 验证数据是否为空
 */
AnnouncementInfoDlg.validate = function () {
    $('#announcementInfoForm').data("bootstrapValidator").resetForm();
    $('#announcementInfoForm').bootstrapValidator('validate');
    return $("#announcementInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
AnnouncementInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/announcement/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Announcement.table.refresh();
        AnnouncementInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.announcementInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AnnouncementInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/announcement/update", function (data) {
        Feng.success("修改成功!");
        window.parent.Announcement.table.refresh();
        AnnouncementInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.announcementInfoData);
    ajax.start();
}

$(function () {
    Feng.initValidator("announcementInfoForm", AnnouncementInfoDlg.validateFields);

    //初始化编辑器
    var E = window.wangEditor;
    var editor = new E('#editor');
    editor.create();
    editor.txt.html($("#contentVal").val());
    AnnouncementInfoDlg.editor = editor;
});
