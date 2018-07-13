package com.luwei.maven.message;

import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.IOUtil;
import org.stringtemplate.v4.ST;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jdq
 * @date 2017/12/4 10:03
 */
class MessageUtil {

    /**
     * 定义待匹配信息的正则表达式及规则
     */
    private static final Pattern PATTERN = Pattern.compile("^String (.+) = \"(.+)\";\\s*//(.*)$");

    /**
     * 获得类中所匹配信息
     *
     * @param path
     * @return
     */
    static List<Message> getMessageList(String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                //读取文件的每一行
                List<String> lines = Files.readAllLines(file.toPath());
                List<Message> messageList = new ArrayList<>(lines.size());

                for (String line : lines) {
                    //匹配每一行信息
                    Matcher matcher = PATTERN.matcher(line.trim());
                    if (matcher.matches()) {
                        //拿到key(code)的信息
                        String code = matcher.group(2);
                        //拿到value(message)信息
                        String msg = matcher.group(3);
                        Message message = new Message(code, msg);
                        messageList.add(message);
                    }
                }
                return messageList;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return Collections.emptyList();
    }

    /**
     * 生成properties文件
     *
     * @param messageList
     * @param project
     * @param validationProperties
     */
    static void buildProperties(List<Message> messageList, boolean validationProperties, MavenProject project) {
        if (!messageList.isEmpty()) {
            String targetOutputDir = project.getBuild().getDirectory() + File.separator + "classes" + File.separator;
            //创建生成properties文件所在目录的文件夹
            FileUtils.mkdir(targetOutputDir);

            StringBuilder sb = new StringBuilder();
            for (Message message : messageList) {
                //定义ST模板格式
                ST line = new ST("<code>=<msg>\r\n");
                String code = message.getCode();
                if (validationProperties) {
                    //读取的是ValidationMessages.properties文件
                    code = code.replace("{", "").replace("}", "");
                }
                line.add("code", code);
                line.add("msg", message.getMsg());
                sb.append(line.render());
            }

            //获得String Template模板对象
            ST content = new ST(getPropertiesTemplate());
            content.add("lines", sb.toString());
            try {
                FileWriter writer;
                if (validationProperties) {
                    writer = new FileWriter(targetOutputDir + "ValidationMessages.properties");
                } else {
                    writer = new FileWriter(targetOutputDir + "messages.properties");
                }
                writer.write(content.render());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 读取根路径下的properties模板文件
     *
     * @return
     */
    private static String getPropertiesTemplate() {
        //读取模板文件
        InputStream inputStream = MessageMojo.class.getResourceAsStream("/template.properties");
        StringWriter stringWriter = new StringWriter();
        try {
            IOUtil.copy(inputStream, stringWriter, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringWriter.toString();
    }

}
