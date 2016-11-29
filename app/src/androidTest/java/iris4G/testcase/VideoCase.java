package iris4G.testcase;

import org.hamcrest.Asst;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.util.HashSet;
import java.util.logging.Logger;

import ckt.base.VP2;
import iris4G.action.CameraAction;
import iris4G.action.FileManagerAction;
import iris4G.action.Iris4GAction;
import iris4G.page.Iris4GPage;

/**
 * @Author elon
 * @Description 所有视频质量[72030|72060|108030] * Video Angle
 */

public class VideoCase extends VP2{
    Logger logger = Logger.getLogger(VideoCase.class.getName());
    @Before
    public void setup() throws Exception {
        Iris4GAction.initIris4G();
    }
    private void Video(String quality,String angle) throws Exception {
        //String quality = Iris4GPage.video_quality[0];
        //String angle =Iris4GPage.video_Angle[0];
        CameraAction.configVideoQuality(quality);
        CameraAction.configVideoAngle(angle);
        CameraAction.cameraVideo();

        HashSet<String> beforeTakeVideoList = Iris4GAction.FileList("/sdcard/video");
        Iris4GAction.cameraKey();
        CameraAction.cameraRecordTime();
        waitTime(10);
        CameraAction.cameraRecordTime();
        Iris4GAction.cameraKey();
        waitTime(5);
        HashSet<String> afterTakeVideoList = Iris4GAction.FileList("/sdcard/Video");
        HashSet<String> resultHashSet = Iris4GAction.result(afterTakeVideoList, beforeTakeVideoList);

        if (resultHashSet.size()==1) {
            String videoPath = resultHashSet.iterator().next();
            logger.info("new file:"+videoPath);
            String videoName = new File(videoPath).getName();
            Iris4GAction.VideoInfo(videoPath);
            FileManagerAction.playVideoByFileManager(videoName);

            if (text_exists_match("^Can't play this video.*")) {
                logger.info(videoName+" 播放失败" + "-Can't play this video");
                clickById("android:id/button1");
                Asst.fail("Can't play this video");
            }else {
                logger.info(videoName+" 播放成功");
            }
        }else {
            Asst.fail("expect only one video in folder");
        }
    }
    @Test
    public void testV72030fpsSuperWide() throws Exception {
        Video(Iris4GPage.video_quality[0],Iris4GPage.video_Angle[0]);
    }
    @Test
    public void testV72060fpsSuperWide() throws Exception {
        Video(Iris4GPage.video_quality[1],Iris4GPage.video_Angle[0]);
    }
    @Test
    public void testV108030fpsSuperWide() throws Exception {
        Video(Iris4GPage.video_quality[2],Iris4GPage.video_Angle[0]);
    }
    @Test
    public void testV72030fpsWide() throws Exception {
        Video(Iris4GPage.video_quality[0],Iris4GPage.video_Angle[1]);
    }
    @Test
    public void testV72060fpsWide() throws Exception {
        Video(Iris4GPage.video_quality[1],Iris4GPage.video_Angle[1]);
    }
    @Test
    public void testV108030fpsWide() throws Exception {
        Video(Iris4GPage.video_quality[2],Iris4GPage.video_Angle[1]);
    }
    @Test
    public void testV72030fpsMedium() throws Exception {
        Video(Iris4GPage.video_quality[0],Iris4GPage.video_Angle[2]);
    }
    @Test
    public void testV72060fpsMedium() throws Exception {
        Video(Iris4GPage.video_quality[1],Iris4GPage.video_Angle[2]);
    }
    @Test
    public void testV108030fpsMedium() throws Exception {
        Video(Iris4GPage.video_quality[2],Iris4GPage.video_Angle[2]);
    }
}