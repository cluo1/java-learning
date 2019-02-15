package cn.mariojd.tool.util.example;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * @author Jared
 * @date 2019/2/15 10:48
 */
public class VideoUtil {

    /**
     * 获取指定视频的帧并保存为图片至指定目录
     *
     * @param video         源视频文件
     * @param thumbnailPath 截取帧缩略图图片的存放路径
     */
    public static void getVideoThumbnail(File video, String thumbnailPath) {
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
        try {
            ff.start();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
        int length = ff.getLengthInFrames();

        int i = 0;
        int maxFrame = length / 2;
        Frame frame = null;
        while (i < length) {
            // 过滤前5帧，避免出现全黑的图片，依自己情况而定
            try {
                frame = ff.grabFrame();
                if ((i > maxFrame) && (frame.image != null)) {
                    break;
                }
                i++;
            } catch (FrameGrabber.Exception e) {
                e.printStackTrace();
            }
        }

        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage srcBi = converter.getBufferedImage(frame);
        int imgWidth = srcBi.getWidth();
        int imgHeight = srcBi.getHeight();
        // 对截取的帧进行等比例缩放
        int width = 480;
        int height = (int) (((double) width / imgWidth) * imgHeight);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        bi.getGraphics().drawImage(srcBi.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

        File targetFile = new File(thumbnailPath);
        try {
            ImageIO.write(bi, "jpg", targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ff.stop();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取视频时长，单位为秒
     *
     * @param video
     * @return 时长（s）
     */
    public static long getVideoDuration(File video) {
        long duration = 0L;
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
        try {
            ff.start();
            duration = ff.getLengthInTime() / (1000 * 1000);
            ff.stop();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
        return duration;
    }

}
