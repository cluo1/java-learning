/**
 * 考勤明细管理初始化
 */
var AttendenceDetail = {
    id: "AttendenceDetailTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
AttendenceDetail.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '考勤外键id', field: 'aid', visible: true, align: 'center', valign: 'middle'},
            {title: '学生id', field: 'sid', visible: true, align: 'center', valign: 'middle'},
            {title: '是否已考勤', field: 'punch', visible: true, align: 'center', valign: 'middle'},
            {title: '考勤时间', field: 'punchTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
AttendenceDetail.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        AttendenceDetail.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加考勤明细
 */
AttendenceDetail.openAddAttendenceDetail = function () {
    var index = layer.open({
        type: 2,
        title: '添加考勤明细',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/attendenceDetail/attendenceDetail_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看考勤明细详情
 */
AttendenceDetail.openAttendenceDetailDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '考勤明细详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/attendenceDetail/attendenceDetail_update/' + AttendenceDetail.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除考勤明细
 */
AttendenceDetail.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/attendenceDetail/delete", function (data) {
            Feng.success("删除成功!");
            AttendenceDetail.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("attendenceDetailId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询考勤明细列表
 */
AttendenceDetail.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    AttendenceDetail.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = AttendenceDetail.initColumn();
    var table = new BSTable(AttendenceDetail.id, "/attendenceDetail/list", defaultColunms);
    table.setPaginationType("client");
    AttendenceDetail.table = table.init();
});
