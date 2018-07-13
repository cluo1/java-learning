/**
 * 用户管理初始化
 */
var MiniUser = {
    id: "MiniUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MiniUser.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '姓名', field: 'realName', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '性别', field: 'gender', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '学号', field: 'studentId', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '班级', field: 'cids', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '角色', field: 'role', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '微信名', field: 'nickname', visible: true, align: 'center', valign: 'middle'},
        {
            title: '头像',
            field: 'avatar',
            visible: true,
            align: 'center',
            valign: 'middle',
            formatter: function (value) {
                return '<img style="width: 50px;height: 50px;" src="' + value + '" class="img-rounded">';
            }
        },
        {title: '注册时间', field: 'createtime', visible: true, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
MiniUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        MiniUser.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户
 */
MiniUser.openAddMiniUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/miniUser/miniUser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户详情
 */
MiniUser.openMiniUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/miniUser/miniUser_update/' + MiniUser.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户
 */
MiniUser.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/miniUser/delete", function (data) {
            Feng.success("删除成功!");
            MiniUser.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("miniUserId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户列表
 */
MiniUser.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MiniUser.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MiniUser.initColumn();
    var table = new BSTable(MiniUser.id, "/miniUser/list", defaultColunms);
    table.setPaginationType("client");
    MiniUser.table = table.init();
});
