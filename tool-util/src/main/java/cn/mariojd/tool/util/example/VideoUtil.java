package cn.mariojd.tool.util.example;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * @author Jared
 * @date 2019/2/14 20:48
 */
public class VideoUtil {

    private static final String JPG = "jpg";

    private static final String MP4_SUFFIX = ".mp4";

    /**
     * 截取视频获得指定帧的图片
     *
     * @param video   源视频文件
     * @param picPath 截图存放路径
     */
    public static void getVideoPic(File video, String picPath) {
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
        try {
            ff.start();

            // 截取中间帧图片(具体依实际情况而定)
            int i = 0;
            int length = ff.getLengthInFrames();
            int middleFrame = length / 2;
            Frame frame = null;
            while (i < length) {
                frame = ff.grabFrame();
                if ((i > middleFrame) && (frame.image != null)) {
                    break;
                }
                i++;
            }

            // 截取的帧图片
            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage srcImage = converter.getBufferedImage(frame);
            int srcImageWidth = srcImage.getWidth();
            int srcImageHeight = srcImage.getHeight();

            // 对截图进行等比例缩放(缩略图)
            final int width = 480;
            int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
            BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            thumbnailImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

            File picFile = new File(picPath);
            ImageIO.write(thumbnailImage, JPG, picFile);

            ff.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取视频时长，单位为秒
     *
     * @param video 源视频文件
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

    /**
     * 其它视频文件转换成 MP4 格式
     *
     * @param video
     */
    public static void transferToMP4(File video) {
        String[] paths = video.getPath().split("\\.");
        if (!MP4_SUFFIX.contains(paths[paths.length - 1])) {
            String mp4Path = paths[0] + MP4_SUFFIX;
            FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
            FFmpegFrameRecorder recorder;

            try {
                ff.start();
                recorder = new FFmpegFrameRecorder(mp4Path, ff.getImageWidth(), ff.getImageHeight(),
                        ff.getAudioChannels());
                recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
                recorder.setFrameRate(ff.getFrameRate());
                recorder.setSampleRate(ff.getSampleRate());
                recorder.start();
                Frame capturedFrame;

                while (true) {
                    try {
                        capturedFrame = ff.grabFrame();
                        if (capturedFrame == null) {
                            break;
                        }
                        recorder.setTimestamp(ff.getTimestamp());
                        recorder.record(capturedFrame);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                recorder.stop();
                recorder.release();
                ff.stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String videoPath = ResourceUtils.CLASSPATH_URL_PREFIX + "video.mp4";
        File video = null;
        try {
            video = ResourceUtils.getFile(videoPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String picPath = "video.jpg";
        getVideoPic(video, picPath);

        long duration = getVideoDuration(video);
        System.out.println("videoDuration = " + duration);
    }

}
