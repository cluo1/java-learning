/**
 * 省份管理管理初始化
 */
var Province = {
    id: "ProvinceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Province.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '省份名称', field: 'name', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '排序', field: 'num', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Province.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Province.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加省份
 */
Province.openAddProvince = function () {
    var index = layer.open({
        type: 2,
        title: '添加省份',
        area: ['600px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/province/province_add'
    });
    this.layerIndex = index;
}

/**
 * 打开查看省份详情
 */
Province.openProvinceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '省份详情',
            area: ['600px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/province/province_update/' + Province.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除省份
 */
Province.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/province/delete", function (data) {
            Feng.success("删除成功!");
            Province.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("provinceId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询省份列表
 */
Province.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Province.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Province.initColumn();
    var table = new BSTable(Province.id, "/province/list", defaultColunms);
    table.setPaginationType("client");
    Province.table = table.init();
});
