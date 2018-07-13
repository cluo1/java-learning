/**
 * Created by Mario on 2017-04-02.
 */
/** ******************************** 初始化 ******************************** */
var date = new Date();
var tel = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
var email = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
function getCurrentYear() {
    return date.getFullYear();
}
function getCurrentDate() {
    return (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date
            .getMonth() + 1)
        + '-'
        + (date.getDate() < 10 ? '0' + date.getDate() : date.getDate());
}
function getCurrentTime() {
    return (date.getHours() < 10 ? "0" + date.getHours() : date.getHours())
        + ":"
        + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date
            .getMinutes());
}
function forgotGetCode() {
    var step1CodeBtn = $('#step1CodeBtn');
    $('.step1Error').html('');
    step1CodeBtn.attr('disabled', true);
    var time = 90;
    var timer = setInterval(function () {
        step1CodeBtn.html(time + 's后重试');
        time--;
        if (time < 0) {
            step1CodeBtn.attr('disabled', false);
            step1CodeBtn.html('重新发送');
            clearInterval(timer);
        }
    }, 1000);
}
function getEmailCode() {
    var updEmailCodeBtn = $('#updEmailCodeBtn');
    $('#updEmailError').html('');
    updEmailCodeBtn.attr('disabled', true);
    var time = 90;
    var timer = setInterval(function () {
        updEmailCodeBtn.html(time + 's后重试');
        time--;
        if (time < 0) {
            updEmailCodeBtn.attr('disabled', false);
            updEmailCodeBtn.html('重新发送');
            clearInterval(timer);
        }
    }, 1000);
}
function getTelCode() {
    var updTelCodeBtn = $('#updTelCodeBtn');
    updTelCodeBtn.attr('disabled', true);
    $('#updTelError').html('');
    var time = 90;
    var timer = setInterval(function () {
        updTelCodeBtn.html(time + 's后重试');
        time--;
        if (time < 0) {
            updTelCodeBtn.attr('disabled', false);
            updTelCodeBtn.html('重新发送');
            clearInterval(timer);
        }
    }, 1000);
}
/** ******************************** 封装 ******************************* */
var user = {
    URL: {
        messageList: function (currentPage) {
            return '/message/list/' + currentPage;
        },
        getState: function () {
            return '/user/state';
        },
        reg: function () {
            return '/user';
        },
        act: function (telephone) {
            return '/user/activation/' + telephone;
        },
        login: function () {
            return '/user/login';
        },
        forgot: {
            send: function () {
                return '/user/forgot';
            },
            check: function () {
                return '/user/forgot';
            },
            update: function () {
                return '/user/forgot';
            }
        },
        save: function () {
            return "/message";
        }
    },
    //获取博文列表
    changeCurrentPage: function (currentPage) {
        //获取数据
        $.get(user.URL.messageList(currentPage), function (res, data) {
            if (currentPage >= 2 && data != 'success') {
                $('#appengMore').html('加载中');
            }
            if (data == 'success') {
                var messageList = res.messageList;
                var page = res.page;
                $('#currentPage').val(page.currentPage);
                //拼接博文列表
                $.each(messageList, function (index, message) {
                    if (message.postTime.substr(0, 4) == getCurrentYear()) {
                        user.appendPanel(message.content, ((message.postTime.substr(5, 5) == getCurrentDate()) ? message.postTime.substr(11, 5)
                            : message.postTime.substr(5, 11)), message.user.icon, message.user.nickname, page.currentPage);
                    } else {
                        user.appendPanel(message.content, message.postTime.substr(0, 16), message.user.icon, message.user.nickname, page.currentPage);
                    }
                });
                //1.2拼接按钮
                if (currentPage == 1) {
                    user.appendButton(page.totalPage);
                }
            }
        });
    },
    //拼接博文列表
    appendPanel: function (content, postTime, imgSrc, nickname, flag) {
        var div = "";
        div += "<div class='panel panel-default'><div class='panel-heading'></div>";
        div += "<div class='panel-body'><div class='media'>";
        div += "<div class='media-left'><a><img src="
            + (imgSrc == null ? '/resources/icon/dficon.png' : imgSrc)
            + " width='70' height='70' class='media-object img-circle'></a></div>";
        div += "<div class='media-body'><span class='media-heading'><strong>"
            + nickname + "</strong><br><small class='text-muted'>" + postTime
            + "</small><br>" + content + "</span><small class='text-muted'>浏览 (0) | 评论 (0)  | 点赞 (0)</small></div>";
        div += "</div></div></div>";
        if (flag == -1) {
            //已登录
            $(".content .panel-default:first").after(div);
        } else if (flag == 1) {
            //未登录，当前为第一页
            $(".content").append(div);
        } else {
            //加载更多
            $(".content .panel-default:last").after(div);
        }
    },
    appendButton: function (totalPage) {
        var div = "<button type='button' id='more' class='btn btn-default btn-block'" +
            "onclick='javascript:user.appendNextPage(" + totalPage + ")'>加载更多</button><br>";
        $(".content .panel-default:last").after(div);
    },
    appendNextPage: function (totalPage) {
        var currentPage = $('#currentPage').val();
        if (currentPage == totalPage) {
            $('#more').attr('disabled', true);
            $('#more').html('没有更多了');
        } else {
            user.changeCurrentPage(parseInt(currentPage) + 1);
        }
    },
    //邮箱注册获取激活邮件
    getEmail: function () {
        var regTip = $('#regTip');
        var regEmail = $('#regEmail');
        var regPsw = $('#regPsw');
        var regSubmit = $('#regSubmit');
        regTip.removeClass('text-danger');
        regTip.addClass('text-success');
        regTip.html('<b>邮件已发送，激活后即可登录</b>');
        regEmail.attr('disabled', true);
        regPsw.attr('disabled', true);
        var time = 90;
        var timer = setInterval(function () {
            regSubmit.html(time + 's后重试');
            time--;
            if (time < 0) {
                regSubmit.attr('disabled', false);
                regEmail.attr('disabled', false);
                regPsw.attr('disabled', false);
                regSubmit.html('重新发送');
                clearInterval(timer);
            }
        }, 1000);
    },
    //手机注册获取短信验证码
    getCode: function () {
        $('#mRegTip').html('');
        $('#mRegCodeBtn').attr('disabled', true);
        $('#mRegCode').focus();
        var time = 90;
        var timer = setInterval(function () {
            $('#mRegCodeBtn').html(time + 's后重试');
            time--;
            if (time < 0) {
                $('#mRegCodeBtn').attr('disabled', false);
                $('#mRegCodeBtn').html('重新发送');
                clearInterval(timer);
            }
        }, 1000);
    }
}


/** ******************************** 登录校验 ******************************** */
$(document).ready(function () {
    $('#loginForm').bootstrapValidator({
        container: 'tooltip',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    regexp: {
                        regexp: /^(([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})|(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8}))$/,
                        message: '用户名为邮箱或手机号'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 16,
                        message: '密码错误'
                    },
                }
            }
        }
    }).on('success.form.bv', function (e) {
        // Prevent form submission
        e.preventDefault();

        // Use Ajax to submit form data
        var rememberme = 0;
        var usernameVal = $('#username').val();
        var passwordVal = $('#password').val();
        if ($('#rememberme').is(':checked')) {
            rememberme = 1;
        }
        $.post(user.URL.login(), {
                'username': usernameVal,
                'password': passwordVal,
                'rememberme': rememberme,
            },
            function (res) {
                var code = res.code;
                var message = res.message;
                if (code == 201 || code == 202) {
                    $('#loginError').html(message);
                } else if (code == 200) {
                    if ($('#ck-message').length > 0) {
                        window.location.href = '/message';
                    } else if ($('#killModal').length > 0) {
                        window.location.reload();
                    } else {
                        window.location.href = '/';
                    }
                }
            });
    });
});

/** ******************************** 邮箱注册校验 ******************************** */
$(document).ready(function () {
    $('#regForm').bootstrapValidator({
        container: 'tooltip',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            regEmail: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    regexp: {
                        regexp: /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/,
                        message: '无效的邮箱地址'
                    }
                }
            },
            regPsw: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 16,
                        message: '密码长度为6~16位'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        // Prevent form submission
        e.preventDefault();
        // Use Ajax to submit form data
        var emailVal = $('#regEmail').val();
        var passwordVal = $('#regPsw').val();
        $.get(user.URL.getState(), {'username': emailVal}, function (res) {
            var code = res.code;
            if (code == -1 || code == 0) {
                user.getEmail();
                if (code == -1) {
                    // -1，未注册
                    $.post(user.URL.reg(), {'email': emailVal, 'password': passwordVal});
                } else {
                    // 0，未激活
                    $.post(user.URL.reg(), {
                        _method: 'PUT',
                        'username': emailVal,
                        'password': passwordVal,
                    });
                }
            } else {
                var regEmail = $('#regEmail');
                var regTip = $('#regTip');
                regTip.addClass('text-danger');
                regEmail.focus();
                regEmail.parent().parent().addClass('has-error');
                $('#regSubmit').attr('disabled', false);
                if (code == 1) {
                    regTip.html('<b>该邮箱已经注册，找回请<a target="_blank" href="/user/forgot">点击</a></b>');
                } else {
                    regTip.html('<b>该邮箱已被禁用，请联系管理员</b>');
                }
            }
        });
    });
});
/** ******************************** 获取手机注册验证码 ******************************** */
$('#mRegCodeBtn').click(function () {
    if (!$('#mRegTel').val().match(/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/)) {
        $('#mRegTel').parent().parent().addClass('has-error');
        $('#mRegTel').focus();
        return;
    }
    if (!($('#mRegPsw').val().length >= 6 && $('#mRegPsw')
            .val().length <= 16)) {
        $('#mRegPsw').parent().parent().addClass('has-error');
        $('#mRegPsw').focus();
        return;
    }
    $('#mRegCode').focus();
    $.get(user.URL.getState($('#mRegTel').val()), {},
        function (res) {
            var code = res.code;
            if (code == -1 || code == 0) {
                user.getCode();
                if (code == -1) {
                    // -1，未注册
                    $.post(user.URL.reg(), {
                        'telephone': $('#mRegTel').val(),
                        'password': $('#mRegPsw').val(),
                    });
                } else {
                    // 0，未激活
                    $.post(user.URL.reg(), {
                        _method: 'PUT',
                        'username': $('#mRegTel').val(),
                        'password': $('#mRegPsw').val(),
                    });
                }
            } else {
                $('#mRegTel').parent().parent().addClass('has-error');
                $('#mRegTip').addClass('text-danger');
                if (code == 1) {
                    // 1，已激活
                    $('#mRegTip').html('<b>该手机已经注册，找回请点击<a target="_blank" href="/user/forgot">这里</a></b>');
                } else {
                    // 2，已禁用
                    $('#mRegTip').html('<b>该手机号已被禁用，请联系管理员</b>');
                }
            }
        });
});
/** ******************************** 手机注册校验 ******************************** */
$(document).ready(function () {
    $('#mRegForm').bootstrapValidator({
        container: 'tooltip',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            mRegTel: {
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    },
                    regexp: {
                        regexp: /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/,
                        message: '无效的手机号'
                    }
                }
            },
            mRegPsw: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 16,
                        message: '密码长度为6~16位'
                    }
                }
            },
            mRegCode: {
                validators: {
                    notEmpty: {
                        message: '验证码不能为空'
                    },
                    regexp: {
                        regexp: /^\d{6}$/,
                        message: '验证码有误'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        // Prevent form submission
        e.preventDefault();

        $.post(user.URL.act($('#mRegTel').val()),
            {
                'password': $('#mRegPsw').val(),
                'code': $('#mRegCode').val(),
            },
            function (res) {
                var code = res.code;
                var message = res.message;
                if (code == 20001 || code == 20002) {
                    if (code == 20001) {
                        $('#mRegTip').addClass('text-danger');
                        // -1,用户名或验证码有误
                        $('#mRegTip').html('<b>' + message + '</b>');
                    } else {
                        // 0，申请过但未激活
                        $('#mRegTip').removeClass('text-danger');
                        $('#mRegTip').addClass('text-success');
                        $('#mRegTip').html('<b>' + message + '</b>');
                        var time = 5;
                        var timer = setInterval(
                            function () {
                                time--;
                                if (time < 0) {
                                    clearInterval(timer);
                                    window.location.href = '/user/mlogin';
                                }
                            }, 1000);
                    }
                } else {
                    $('#mRegTel').focus();
                    $('#mRegTel').parent().parent().addClass('has-error');
                    $('#mRegTip').addClass('text-danger');
                    if (code == 20003) {
                        // 1，已激活手机号
                        $('.mRegTip').html('<b>' + message + '，找回请点击<a target="_blank" href="/user/forgot">这里</a></b>');
                    } else {
                        // 2，已禁用手机号
                        $('.mRegTip').html('<b>' + message + '</b>');
                    }
                }
            });
    });
});


/** ******************************** 找回密码点击获取验证码 ******************************** */
$('#step1CodeBtn').click(function () {
    var step1Username = $('#step1Username');
    var step1Code = $('#step1Code');
    if (!step1Username.val().match(/^(([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})|(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8}))$/)) {
        step1Username.parent().parent().addClass('has-error');
        step1Username.focus();
        return;
    }
    step1Code.focus();
    $.get(user.URL.getState(), {'username': step1Username.val()},
        function (res) {
            var step1Error = $('.step1Error');
            var code = res.code;
            if (code == -1 || code == 0) {
                // -1，未注册；0，未激活
                step1Error.html('账户尚未注册');
                step1Username.focus();
                step1Username.parent().parent().addClass('has-error');
            } else {
                if (code == 1) {
                    // 1，可找回
                    forgotGetCode();
                    step1Code.focus();
                    step1Username.parent().parent().addClass('has-success');
                    $.post(user.URL.forgot.send(), {'username': step1Username.val()})
                } else {
                    // 2，已禁用
                    step1Error.html('账户已被禁用，请联系管理员');
                    step1Username.parent().parent().addClass('has-error');
                    step1Username.focus();
                }
            }
        });
});
/** ******************************** 下一步校验逻辑 ******************************** */
$(document).ready(function () {
    $('#step1Form').bootstrapValidator({
        container: 'tooltip',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            step1Username: {
                validators: {
                    notEmpty: {
                        message: '请输入手机号或邮箱'
                    },
                    regexp: {
                        regexp: /^(([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})|(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8}))$/,
                        message: '无效的用户名'
                    }
                }
            },
            step1Code: {
                validators: {
                    notEmpty: {
                        message: '验证码不能为空'
                    },
                }
            }
        }
    }).on('success.form.bv', function (e) {
        // Prevent form submission
        e.preventDefault();
        var step1Username = $('#step1Username');
        var code = $('#step1Code');
        $.get(user.URL.forgot.check(), {'username': step1Username.val(), 'code': code.val()},
            function (res) {
                var step1Error = $('.step1Error');
                var flag = res.flag;
                var message = res.message;
                if (!flag) {
                    step1Error.html(message);
                    step1Username.parent().parent().addClass('has-error');
                    step1Username.focus();
                } else {
                    $('#step1').removeClass('in active');
                    $('#step1').addClass('sr-only');
                    $('#step2').removeClass('sr-only');
                    $('#in-step2').addClass('active');
                    $('#step2Username').val(step1Username.val());
                    $('#step2Psw').focus();
                }
            });
    });
});
/** ******************************** 下一步校验逻辑 ******************************** */
$(document).ready(function () {
    $('#step2Form').bootstrapValidator({
        container: 'tooltip',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            step2Psw: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 16,
                        message: '密码长度为6~16位'
                    }
                }
            },
            step2ConfPsw: {
                validators: {
                    notEmpty: {
                        message: '请再次输入密码'
                    },
                    stringLength: {
                        min: 6,
                        max: 16,
                        message: '密码长度为6~16位'
                    },
                    identical: {
                        field: 'step2Psw',
                        message: '两次输入的密码不一致'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        // Prevent form submission
        e.preventDefault();

        $.post(user.URL.forgot.update(), {
                _method: 'PUT',
                'username': $('#step2Username').val(),
                'password': $('#step2Psw').val()
            },
            function (data, status) {
                if (status == 'success') {
                    $('#step2').removeClass('in active');
                    $('#step2').addClass('sr-only');
                    $('#in-step3').addClass('active');
                    $('#step3').removeClass('sr-only');
                    var msg = document.getElementById('msg');
                    var time = 8;
                    var timer = setInterval(function () {
                        msg.innerHTML = time;
                        time--;
                        if (time < 0) {
                            window.location.href = '/';
                            clearInterval(timer);
                        }
                    }, 1000);
                } else {
                    var step2Error = $('#step2Error');
                    step2Error.addClass('text-danger');
                    step2Error.html('系统故障');
                }
            });
    });
});
/** ******************************** seckill ******************************** */
var seckill = {
    URL: {
        exposure: function (sid) {
            return '/seckill/' + sid + '/exposure';
        },
        execution: function (sid, md5) {
            return '/seckill/' + sid + '/' + md5 + '/execution';
        },
    },
    //点击按钮执行抢购,获取抢购地址，处理逻辑
    handleKill: function (sid, timeBox) {
        //判断是否已登录
        if (seckill.checkLogin($("#user").val())) {
            $.post(seckill.URL.exposure(sid), {}, function (result) {
                if (result && result['success']) {
                    //系统请求成功
                    var exposure = result['data'];
                    if (exposure['exposed']) {
                        //开始抢购，获取抢购地址
                        var killUrl = seckill.URL.execution(sid, exposure['md5']);
                        //绑定一次点击事件,执行禁用按钮、发送抢购请求和显示抢购结果
                        $('#killBtn').one('click', function () {
                            $.post(killUrl, {
                                uid: $('#uid').val(),
                                sprice: $('#sprice').val(),
                                'sname': $('#sname').val()
                            }, function (result) {
                                if (result && result['success']) {
                                    $('#killBtn').addClass('disabled');
                                    var stateInfo = result['data']['stateInfo'];
                                    $('#killBtn').html(stateInfo);
                                } else {
                                    $('#killBtn').html(result['error']);
                                    seckill.checkLogin($('#user').val());
                                }
                            })
                        });
                    } else {
                        //抢购未开始，重新执行倒计时逻辑
                        seckill.timeBefore(sid, timeBox, exposure['now'], exposure['start'], exposure['end']);
                    }
                } else {
                    //系统请求失败
                    $('#killBtn').html('系统故障，刷新重试');
                }
            });
        }
    },
    timeBefore: function (sid, timeBox, nowTimeTimestamp, startTimeTimestamp, endTimeTimestamp) {
        //抢购未开始，绑定倒计时事件
        if (nowTimeTimestamp < startTimeTimestamp && $("#snumber").val() != 0) {
            var killTime = new Date(startTimeTimestamp + 1000);
            timeBox.countdown(killTime, function (event) {
                var format = event.strftime('%D天 %H时 %M分 %S秒');
                timeBox.html(format);
            }).on('finish.countdown', function () {
                seckill.timeBetween(sid, timeBox, startTimeTimestamp, new Date().getTime(), endTimeTimestamp);
                seckill.handleKill(sid, timeBox);
                //倒计时结束后的回调函数，触发抢购结束倒计时，获取抢购地址
                $('#killBtn').attr('disabled', false);
                $('#killBtn').html('立即抢购');
                $('#killTip').html('距离活动结束还有');
            });
        }
    },
    timeBetween: function (sid, timeBox, startTimeTimestamp, nowTimeTimestamp, endTimeTimestamp) {
        if (nowTimeTimestamp >= startTimeTimestamp && nowTimeTimestamp <= endTimeTimestamp && $("#snumber").val() != 0) {
            //抢购正在进行，绑定倒计时事件和抢购地址
            seckill.handleKill(sid, timeBox);
            var endtime = new Date(endTimeTimestamp + 1000);
            timeBox.countdown(endtime, function (event) {
                var format = event.strftime('%D天 %H时 %M分 %S秒');
                timeBox.html(format);
            }).on('finish.countdown', function () {
                //倒计时结束后的回调函数，关闭抢购
                timeBox.html('');
                $('#killBtn').attr('disabled', true);
                $('#killBtn').html('活动结束');
                $('#killTip').html('抱歉，抢购活动已经结束');
            });
        }
    },
    checkLogin: function (user) {
        if (user == '') {
            $('#killBtn').html('点击登录');
            var killModal = $('#killModal');
            killModal.modal({
                show: true,//显示弹出层
                backdrop: 'static',//禁止位置改变
                keyboard: false,//关闭键盘事件
            });
            return false;
        }
        return true;
    }
};
$('#killBtn').click(function () {
    seckill.checkLogin($("#user").val());
});

/** ******************************** setting ******************************** */
var uid = $('#uid').val();
var userEmail = $('#userEmail').val();
var userTel = $('#userTel').val();
var nickname = $('#nickname');
var nicknameVal = $('#nickname').val();
var nicknameSave = $('#nicknameSave');
var User = {
    URL: {
        saveIcon: function () {
            return '/user/' + uid + '/icon';
        },
        updCode: function () {
            return '/user/' + uid + '/code';
        },
        checkCode: function () {
            return '/user/' + uid + '/code';
        },
        updUsername: function () {
            return '/user/' + uid + '/username';
        },
        saveNickname: function () {
            return '/user/' + uid + '/nickname';
        },
    }
}
/** ******************修改、绑定邮箱************************** */
$('#updEmail').focus(function () {
    var updEmail = $('#updEmail');
    if (updEmail.val().length > 0) {
        if (!updEmail.val().match(email)) {
            updEmail.parent().addClass('has-error');
        }
    }
});
$('#updEmail').blur(function () {
    var updEmail = $('#updEmail');
    if (!updEmail.val().match(email)) {
        updEmail.parent().addClass('has-error');
    }
});
$('#updEmailCodeBtn').click(function () {
    var updEmailError = $('#updEmailError');
    var updEmail = $('#updEmail');
    if (!updEmail.val().match(email)) {
        updEmail.parent().addClass('has-error');
        return;
    }
    if (userEmail == $('#updEmail').val()) {
        updEmailError.addClass('text-danger');
        updEmailError.html('不支持操作你的当前邮箱账户');
        return;
    }
    $.get(user.URL.getState(), {
        'username': updEmail.val(),
    }, function (res) {
        if (res.code == -1) {
            // 不存在：可绑定及修改，根据uid保存验证码并发送邮箱信息...
            getEmailCode();
            $('#updEmailCode').focus();
            $.post(User.URL.updCode(), {
                _method: 'PUT',
                'username': updEmail.val(),
            })
        } else {
            // 存在：不可绑定或修改
            updEmailError.addClass('text-danger');
            updEmailError.html('该邮箱已经注册，不支持其它账户绑定');
            updEmail.focus();
        }
    });
});
$('#updateEmailSub').click(function () {
    var updEmail = $('#updEmail');
    if (!updEmail.val().match(email)) {
        updEmail.parent().addClass('has-error');
        updEmail.focus();
        return;
    }
    var updEmailCode = $('#updEmailCode');
    if (!updEmailCode.val().length > 0) {
        updEmailCode.parent().addClass('has-error');
        updEmailCode.focus();
        return;
    }
    var updEmailError = $('#updEmailError');
    if (userEmail == $('#updEmail').val()) {
        updEmailError.addClass('text-danger');
        updEmailError.html('不支持操作你的当前邮箱账户');
        return;
    }
    $.get(User.URL.checkCode(), {
        'code': updEmailCode.val()
    }, function (res) {
        var flag = res.flag;
        var message = res.message;
        if (!flag) {
            // 错误的邮箱或验证码
            updEmailError.addClass('text-danger');
            updEmailError.html(message);
        } else {
            // 2，绑定（修改）成功
            updEmailError.removeClass('text-danger');
            updEmailError.addClass('text-success');
            updEmailError.html(message);
            $.post(User.URL.updUsername(), {
                _method: 'PUT',
                'username': updEmail.val()
            }, function (data, status) {
                if (status == 'success') {
                    window.location.reload();
                }
            })
        }
    });
});
$('#updateEmailSub').keydown(function (e) {
    var e = e || event, keycode = e.which || e.keyCode;
    if (keycode == 13 && !$('#updEmailCodeBtn').prop('disabled')) {
        $('#updEmailForm').click();
    }
});
/** ******************绑定、修改手机************************** */
$('#updTel').focus(function () {
    var _this = $(this);
    if (_this.val().length > 0) {
        if (!_this.val().match(tel)) {
            _this.parent().addClass('has-error');
        }
    }
});
$('#updTel').blur(function () {
    var updTel = $('#updTel');
    if (!updTel.val().match(tel)) {
        updTel.parent().addClass('has-error');
    }
});
$('#updTelCodeBtn').click(function () {
    var updTel = $('#updTel');
    if (!updTel.val().match(tel)) {
        updTel.parent().addClass('has-error');
        return;
    }
    var updTelError = $('#updTelError');
    if (userTel == $('#updTel').val()) {
        updTelError.addClass('text-danger');
        updTelError.html('不支持操作当前绑定的手机号码');
        return;
    }
    $.get(user.URL.getState(), {
        'username': updTel.val(),
    }, function (res) {
        if (res.code == -1) {
            // 不存在：可绑定及修改，根据uid保存验证码并发送邮箱信息...
            getTelCode();
            $('#updTelCode').focus();
            $.post(User.URL.updCode(), {
                _method: 'PUT',
                'username': updTel.val(),
            })
        } else {
            // 存在：不可绑定或修改
            updTelError.addClass('text-danger');
            updTelError.html('该手机已经注册，不支持额外绑定或修改');
            updTel.focus();
        }
    });
});
$('#updateTelSub').click(function () {
    var updTel = $('#updTel');
    if (!updTel.val().match(tel)) {
        updTel.parent().addClass('has-error');
        updTel.focus();
        return;
    }
    var updTelCode = $('#updTelCode');
    if (!updTelCode.val().length > 0) {
        updTelCode.parent().addClass('has-error');
        updTelCode.focus();
        return;
    }
    var updTelError = $('#updTelError');
    if (userTel == $('#updTel').val()) {
        updTelError.addClass('text-danger');
        updTelError.html('不支持操作当前绑定的手机号码');
        return;
    }
    $.get(User.URL.checkCode(), {
        'code': updTelCode.val()
    }, function (res) {
        var flag = res.flag;
        var message = res.message;
        if (!flag) {
            //错误的手机号或验证码
            updTelError.addClass('text-danger');
            updTelError.html(message);
        } else {
            // 2，绑定（修改）成功
            updTelError.removeClass('text-danger');
            updTelError.addClass('text-success');
            $('#updTelError').html(message);
            $.post(User.URL.updUsername(), {
                _method: 'PUT',
                'username': updTel.val()
            }, function (data, status) {
                if (status == 'success') {
                    window.location.reload();
                }
            })
        }
    });
});
$('#updateTelSub').keydown(function (e) {
    var e = e || event, keycode = e.which || e.keyCode;
    if (keycode == 13 && !$('#updTelCodeBtn').prop('disabled')) {
        $('#updRTelForm').click();
    }
});
/** ******************修改昵称************************** */
$('#bj').click(function () {
    nickname.focus().val(nickname.val());
});
nicknameSave.click(function () {
    if (nicknameVal == $('#nickname').val()) {
        return;
    }
    nicknameSave.addClass('disabled');
    nicknameSave.html('修改中');
    $.post(User.URL.saveNickname(), {
        _method: 'PUT',
        'nickname': $('#nickname').val(),
    }, function (data, status) {
        if (status == 'success') {
            nicknameSave.html('已修改');
            nicknameVal = data;
            var time = 3;
            var timer = setInterval(function () {
                time--;
                if (time <= 0) {
                    nicknameSave.removeClass('disabled');
                    nicknameSave.html('保存');
                    clearInterval(timer);
                }
            }, 1000);
        }
    });
});
/** ******************************** 提交消息、留言事件 ******************************** */
$('#messageSubmit').click(
    function () {
        var scontent = $("#scontent");
        var messageSubmit = $('#messageSubmit');
        if (ue.hasContents() || scontent.val().length > 0) {
            messageSubmit.addClass('disabled');
            messageSubmit.html('<span class=\'text-primary\'>正在提交</span>');
            var uid = $('#uid').val();
            var content = ue.hasContents() ? ue.getContent() : '<p>' + scontent.val() + '<p>';
            $.post(user.URL.save(), {'uid': uid, 'content': content}, function (data, status) {
                if (status == 'success') {
                    user.appendPanel(content, getCurrentTime(), $('#icon').val(), $('#nickname').val(), -1);
                    ue.focus() || scontent.focus();
                    ue.setContent('', false) || scontent.val('');
                    messageSubmit.html('<span class=\'text-primary\'>发表</span>');
                } else {
                    messageSubmit.html('<span class=\'text-danger\'>提交失败</span>');
                }
                messageSubmit.removeClass('disabled');
            });
        } else {
            ue.focus() || scontent.focus();
            messageSubmit.html('<span class=\'text-danger\'>内容为空</span>');
            var time = 2;
            var timer = setInterval(function () {
                time--;
                if (time <= 0) {
                    messageSubmit.html('<span class=\'text-primary\'>发表</span>');
                    clearInterval(timer);
                }
            }, 1000);
        }
    });