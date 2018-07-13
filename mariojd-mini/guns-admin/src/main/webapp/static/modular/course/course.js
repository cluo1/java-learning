/**
 * 课程管理初始化
 */
var Course = {
    id: "CourseTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Course.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '班级', field: 'cid', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '课程名', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '任课老师', field: 'tid', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '上课日', field: 'weekDay', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '上课时间', field: 'startTime', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '下课时间', field: 'endTime', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '开课日期', field: 'startDate', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '结课日期', field: 'endDate', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '提交人', field: 'uid', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '排序', field: 'num', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Course.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Course.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加课程
 */
Course.openAddCourse = function () {
    var index = layer.open({
        type: 2,
        title: '添加课程',
        area: ['800px', '520px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/course/course_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看课程详情
 */
Course.openCourseDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '课程详情',
            area: ['800px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/course/course_update/' + Course.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除课程
 */
Course.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/course/delete", function (data) {
            Feng.success("删除成功!");
            Course.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("courseId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询课程列表
 */
Course.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Course.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Course.initColumn();
    var table = new BSTable(Course.id, "/course/list", defaultColunms);
    table.setPaginationType("client");
    Course.table = table.init();
});
