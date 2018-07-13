package com.luwei.maven.message;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.List;

/**
 * @author jdq
 * @date 2017/11/27 14:28
 */
@Mojo(name = "gen-messages", defaultPhase = LifecyclePhase.COMPILE)
public class MessageMojo extends AbstractMojo {

    @Component
    private MavenProject mavenProject;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info(" ------ start generate messages.properties && ValidationMessages.properties ------ ");

        //定义类所在的包路径位置
        final String packagePath = "/src/main/java/com/luwei/common/exception/".replace('/', File.separatorChar);

        String messageFilePath = mavenProject.getBasedir().getPath() + packagePath + "MessageCodes.java";
        getLog().info(" ------ MessageCodes.class file path is " + messageFilePath);
        String validationFilePath = mavenProject.getBasedir().getPath() + packagePath + "ValidationCodes.java";
        getLog().info(" ------ ValidationCodes.class file path is " + validationFilePath);

        List<Message> messageList = MessageUtil.getMessageList(messageFilePath);
        getLog().info(" ------ messages.properties message number is " + messageList.size());
        List<Message> validationList = MessageUtil.getMessageList(validationFilePath);
        getLog().info(" ------ ValidationMessages.properties message number is " + validationList.size());

        MessageUtil.buildProperties(messageList, false, mavenProject);
        MessageUtil.buildProperties(validationList, true, mavenProject);

        getLog().info(" ------ end generate messages.properties && ValidationMessages.properties ------ ");
    }

}
