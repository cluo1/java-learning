// 初始化echarts实例
var user = echarts.init(document.getElementById('report_user')),
    message = echarts.init(document.getElementById('report_message')),
    seckill = echarts.init(document.getElementById('report_seckill')),
    order = echarts.init(document.getElementById('report_order'));
// 指定图表的配置项和数据
var userOption = {
    baseOption: {
        color: ['#58B7FF'],
        tooltip: {
            trigger: 'axis',
            formatter: "{b} : {c}  个",
            axisPointer: {// 坐标轴指示器，坐标轴触发有效
                type: 'shadow'// 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: ['用户来源及状况统计']
        },
        xAxis: {
            data: ['总数', '微信', '网站', '禁用', '未激活'],
            axisTick: {
                alignWithLabel: true
            }
        },
        yAxis: {},
        series: [{
            name: '用户来源及状况统计',
            type: 'bar',
            barWidth: '60%',
            data: []
        }]
    }
};
var messageOption = {
    baseOption: {
        color: ['#13CE66'],
        tooltip: {
            trigger: 'axis',
            formatter: "{b} : {c}  条",
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'line'// 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: ['用户留言相关统计']
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['今天', '昨天', '前天', '3天前', '7天前', '15天前']
        },
        yAxis: {},
        series: [{
            name: '用户留言相关统计',
            type: 'line',
            data: []
        }]
    }
};
var seckillOption = {
    baseOption: {
        title: {
            text: '商品分类及价格分别统计',
            x: 'center',
            textStyle: {
                fontSize: 16
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            //'[0,1500)', '[1500,3000)', '[3000,5000)', '[5000,10000)', '[10000,+∞)',
            data: ['手机', '笔记本', '其它']
        },
        series: [
            {
                name: '所属类别',
                type: 'pie',
                selectedMode: 'single',
                radius: [0, '35%'],
                center: ['50%', '60%'],
                label: {
                    normal: {
                        position: 'inner'
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                }
            },
            {
                name: '价格区间',
                type: 'pie',
                radius: ['50%', '70%'],
                center: ['50%', '60%']
            }
        ]
    }
};
var piePatternSrc = 'http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/09/ChMkJ1bKzjmIYl6KAADBibkXykEAALJIwGSepIAAMGh811.png';
var piePatternImg = new Image();
piePatternImg.src = piePatternSrc;
var itemStyle = {
    normal: {
        opacity: 0.7,
        color: {
            image: piePatternImg,
            repeat: 'repeat'
        },
        borderWidth: 3,
        borderColor: '#235894'
    }
};
var orderOption = {
    baseOption: {
        title: {
            text: '订单状态统计',
            textStyle: {
                fontSize: 16,
                color: '#3A3A3A'
            },
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: ['已作废', '未付款', '已付款', '已发货']
        },
        series: [{
            name: '订单状态',
            type: 'pie',
            selectedMode: 'single',
            selectedOffset: 30,
            clockwise: true,
            radius: ['0', '70%'],
            center: ['50%', '60%'],
            label: {
                normal: {
                    textStyle: {
                        color: '#235894'
                    }
                }
            },
            labelLine: {
                normal: {
                    lineStyle: {
                        color: '#235894'
                    }
                }
            },
            itemStyle: itemStyle
        }]
    }
};
//数据加载完毕前的loading动画
user.showLoading();
message.showLoading();
seckill.showLoading();
order.showLoading();
$.get('/reports').done(function (res) {
    //数据加载完毕关闭loading动画
    user.hideLoading();
    message.hideLoading();
    seckill.hideLoading();
    order.hideLoading();
    //设置数据
    user.setOption({
        series: [{
            data: res.data.user_report
        }]
    });
    message.setOption({
        series: [{
            data: res.data.message_report
        }]
    });
    seckill.setOption({
        series: [
            {
                data: [
                    {value: res.data.seckill_report[0], name: '手机'},
                    {value: res.data.seckill_report[1], name: '笔记本'},
                    {value: res.data.seckill_report[2], name: '其它'}
                ]
            },
            {
                data: [
                    {value: res.data.seckill_report[3], name: '[0,1500)'},
                    {value: res.data.seckill_report[4], name: '[1500,3000)'},
                    {value: res.data.seckill_report[5], name: '[3000,5000)'},
                    {value: res.data.seckill_report[6], name: '[5000,10000)'},
                    {value: res.data.seckill_report[7], name: '[10000,+∞)'}
                ]
            }
        ]
    });
    order.setOption({
        series: [{
            data: [
                {value: res.data.order_report[0], name: '已作废'},
                {value: res.data.order_report[1], name: '未付款'},
                {value: res.data.order_report[2], name: '已付款'},
                {value: res.data.order_report[3], name: '已发货'}
            ]
        }]
    });
});
//展示
user.setOption(userOption);
message.setOption(messageOption);
seckill.setOption(seckillOption);
order.setOption(orderOption);