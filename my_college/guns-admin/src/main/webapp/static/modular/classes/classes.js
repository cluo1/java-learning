/**
 * 班级管理初始化
 */
var Classes = {
    id: "ClassesTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Classes.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '学校', field: 'sid', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '简称', field: 'simpleName', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '全称', field: 'fullName', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '排序', field: 'num', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Classes.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Classes.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加班级
 */
Classes.openAddClasses = function () {
    var index = layer.open({
        type: 2,
        title: '添加班级',
        area: ['800px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/classes/classes_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看班级详情
 */
Classes.openClassesDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '班级详情',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/classes/classes_update/' + Classes.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除班级
 */
Classes.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/classes/delete", function (data) {
            Feng.success("删除成功!");
            Classes.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("classesId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询班级列表
 */
Classes.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Classes.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Classes.initColumn();
    var table = new BSTable(Classes.id, "/classes/list", defaultColunms);
    table.setPaginationType("client");
    Classes.table = table.init();
});
