/**
 * 考勤管理初始化
 */
var Attendence = {
    id: "AttendenceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Attendence.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '课程id', field: 'cid', visible: true, align: 'center', valign: 'middle'},
            {title: '考勤结束时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'},
            {title: '考勤方式 0-教师 1-班干 2-学生', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Attendence.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Attendence.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加考勤
 */
Attendence.openAddAttendence = function () {
    var index = layer.open({
        type: 2,
        title: '添加考勤',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/attendence/attendence_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看考勤详情
 */
Attendence.openAttendenceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '考勤详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/attendence/attendence_update/' + Attendence.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除考勤
 */
Attendence.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/attendence/delete", function (data) {
            Feng.success("删除成功!");
            Attendence.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("attendenceId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询考勤列表
 */
Attendence.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Attendence.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Attendence.initColumn();
    var table = new BSTable(Attendence.id, "/attendence/list", defaultColunms);
    table.setPaginationType("client");
    Attendence.table = table.init();
});
