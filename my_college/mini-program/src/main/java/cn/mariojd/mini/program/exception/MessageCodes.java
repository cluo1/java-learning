package cn.mariojd.mini.program.exception;


public interface MessageCodes {

    String AUTH_TOKEN = "auth.token.wrong";//token错误或已过期,请重新登录
    String AUTH_TOKEN_EMPTY = "auth.token.empty";//token为空
    String AUTH_ACCOUNT_PASSWORD_WRONG = "auth.account.password.wrong";//账号或密码错误
    String ACCOUNT_HAS_DISABLED = "account.has.disabled";//账号已被禁用
    String AUTH_PERMISSION = "auth.permission";//权限不足
    String REQUEST_ARGUMENT = "request.argument";//请求参数错误或者参数为空
    String INTERNAL_SERVER_ERROR = "server.internal";//未知错误

    //验证码相关
    String AUTH_PICCAPTCHA_WRONG = "auth.captcha.wrong";//验证码错误
    String AUTH_PICCAPTCHA_LOST = "auth.captcha.lost";//验证码已失效
    String AUTH_PHOCAPTCHA_SEND_FAIL = "auth.phone.captcha.send.fail";//验证码发送失败

    //协议相关
    String TITLE_CONFLICT = "proto.title.conflict";//协议标题冲突，当前协议或已上传

}
