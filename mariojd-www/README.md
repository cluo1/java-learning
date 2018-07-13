#This is the source code for my https://www.mariojd.cn web site

	说明：
	1.修改jdbc.properties中连接数据库参数，改成数据库地址、用户及密码
	2.修改dao文件夹下cache文件夹中的redisDao文件，修改redis服务器的地址
	3_1.修改Util包下的AliSendMailUtil，这是通过阿里云发送邮件的工具类，或者你可以直接替换掉，其中要修改的参数包括有发件人的账号和访问SMTP服务时需要提供的密码
	3_2.修改Util包下的AliSendMessageUtil，这是通过阿里云发送短信的工具类，或者你可以直接替换掉，其中要修改的参数包括有APPCODE，SignName和TemplateCode
	3_3.修改Util包下的BaiduUtil，这是调用百度翻译API接口的工具类，其中要修改的参数包括有APPID和APPKEY
	4.db.sql为数据库的脚本文件，其中部分数据表涉及后台管理系统中所用到的
	5.构建工具使用的是Maven

	
