#This is the source code for my https://admin.mariojd.cn web site

	说明：
	1.修改application.yml中连接数据库参数，改成数据库地址、用户及密码
	2_1.修改Util包下的AliSendMailUtil，这是通过阿里云发送邮件的工具类，或者你可以直接替换掉，其中要修改的参数包括有发件人的账号和访问SMTP服务时需要提供的密码
	2_2.修改Util包下的AliSendMessageUtil，这是通过阿里云发送短信的工具类，或者你可以直接替换掉，其中要修改的参数包括有APPCODE，SignName和TemplateCode
	2_3.修改Util包下的BaiduUtil，这是调用百度翻译API接口的工具类，其中要修改的参数包括有APPID和APPKEY
	3.db.sql为数据库的脚本文件
	4.构建工具使用的是Maven

	
	
	
