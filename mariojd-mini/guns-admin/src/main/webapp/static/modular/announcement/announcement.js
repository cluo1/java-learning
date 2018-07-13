/**
 * 通知管理初始化
 */
var Announcement = {
    id: "AnnouncementTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Announcement.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '班级', field: 'cid', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '发布人', field: 'publisher', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
        {title: '内容', field: 'content', align: 'center', valign: 'middle'},
        {title: '处理人', field: 'uid', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '排序', field: 'num', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Announcement.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Announcement.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加通知
 */
Announcement.openAddAnnouncement = function () {
    var index = layer.open({
        type: 2,
        title: '添加通知',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/announcement/announcement_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看通知详情
 */
Announcement.openAnnouncementDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '通知详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/announcement/announcement_update/' + Announcement.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除通知
 */
Announcement.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/announcement/delete", function (data) {
            Feng.success("删除成功!");
            Announcement.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("announcementId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询通知列表
 */
Announcement.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Announcement.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Announcement.initColumn();
    var table = new BSTable(Announcement.id, "/announcement/list", defaultColunms);
    table.setPaginationType("client");
    Announcement.table = table.init();
});
