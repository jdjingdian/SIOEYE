package testcase.me;

import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.widget.TextView;

import com.squareup.spoon.Spoon;

import org.hamcrest.Asst;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import action.MeAction;
import ckt.base.VP2;
import page.App;
import page.Me;

/**
 * Created by elon on 2016/10/27.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class LiveConfigCase extends VP2{
    @Before
    public  void setup(){
        openAppByPackageName(App.SIOEYE_PACKAGE_NAME_USA);
    }

    @Test
    public void test_default_video_title_3() throws UiObjectNotFoundException, IOException {
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_VIDEO_TITLE);
        getObjectById(Me.SAMPLE_CONTENT).clearTextField();
        String expect = getRandomString(3);
        shellInputText(expect);
        clickById(Me.LIVE_CONFIGURATION_DONE_TITLE);

        openAppByPackageName(App.SIOEYE_PACKAGE_NAME_USA);
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_VIDEO_TITLE);
        String active = getObjectById(Me.SAMPLE_CONTENT).getText();
        Asst.assertEquals("修改video title",expect,active);
        gDevice.pressBack();
        gDevice.pressBack();

    }
    @Test
    public void test_default_video_title_69() throws UiObjectNotFoundException, IOException {
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_VIDEO_TITLE);
        getObjectById(Me.SAMPLE_CONTENT).clearTextField();
        String expect = getRandomString(69);
        shellInputText(expect);
        clickById(Me.LIVE_CONFIGURATION_DONE_TITLE);

        openAppByPackageName(App.SIOEYE_PACKAGE_NAME_USA);
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_VIDEO_TITLE);
        String active = getObjectById(Me.SAMPLE_CONTENT).getText();
        Asst.assertEquals("修改video title",expect,active);
        gDevice.pressBack();
        gDevice.pressBack();

    }
    @Test
    public void test_default_video_title_70() throws UiObjectNotFoundException, IOException {
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_VIDEO_TITLE);
        getObjectById(Me.SAMPLE_CONTENT).clearTextField();
        String expect = getRandomString(70);
        shellInputText(expect);
        clickById(Me.LIVE_CONFIGURATION_DONE_TITLE);

        openAppByPackageName(App.SIOEYE_PACKAGE_NAME_USA);
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_VIDEO_TITLE);
        String active = getObjectById(Me.SAMPLE_CONTENT).getText();
        Asst.assertEquals("修改video title",expect,active);
        gDevice.pressBack();
        gDevice.pressBack();

    }
    @Test
    public void test_default_video_title_100() throws UiObjectNotFoundException, IOException {
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_VIDEO_TITLE);
        getObjectById(Me.SAMPLE_CONTENT).clearTextField();
        String expect = getRandomString(100);
        shellInputText(expect);
        clickById(Me.LIVE_CONFIGURATION_DONE_TITLE);

        openAppByPackageName(App.SIOEYE_PACKAGE_NAME_USA);
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_VIDEO_TITLE);
        String active = getObjectById(Me.SAMPLE_CONTENT).getText();
        expect=expect.substring(0,70);
        Asst.assertEquals("修改video title",expect,active);
        gDevice.pressBack();
        gDevice.pressBack();

    }
    public void setToPublic() throws UiObjectNotFoundException {
        List<UiObject2> cbx = gDevice.findObjects(By.res(Me.LIVE_CONFIGURATION_PRIVACY_SELECT));
        boolean is_checked=getObjectById(Me.LIVE_CONFIGURATION_PRIVACY_SELECT).isChecked();
        if (!is_checked){
            clickById(Me.LIVE_CONFIGURATION_PRIVACY_SELECT);
        }
    }
    public void setToPrivate() throws UiObjectNotFoundException {
        setToPublic();
        List<UiObject2> cbx = gDevice.findObjects(By.res(Me.LIVE_CONFIGURATION_PRIVACY_SELECT));
        for (UiObject2 box:cbx) {
            box.click();
        }
    }
    @Test
    public void test_privacy_settings_public() throws UiObjectNotFoundException {
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_PRIVACY_SETTINGS);
        setToPublic();
        clickById(Me.LIVE_CONFIGURATION_DONE_PRIVACY);

        openAppByPackageName(App.SIOEYE_PACKAGE_NAME_USA);
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_PRIVACY_SETTINGS);
        boolean is_checked=getObjectById(Me.LIVE_CONFIGURATION_PRIVACY_SELECT).isChecked();
        Asst.assertEquals("set Privacy Setting:Public",true,is_checked);
        Spoon.screenshot("Public","Privacy_Settings_Public");
    }
    @Test
    public void test_privacy_settings_private() throws UiObjectNotFoundException {
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_PRIVACY_SETTINGS);
        setToPrivate();
        clickById(Me.LIVE_CONFIGURATION_DONE_PRIVACY);

        openAppByPackageName(App.SIOEYE_PACKAGE_NAME_USA);
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_PRIVACY_SETTINGS);
        boolean is_checked=getObjectById(Me.LIVE_CONFIGURATION_PRIVACY_SELECT).isChecked();
        Asst.assertEquals("set Privacy Setting:Public",false,is_checked);
        Spoon.screenshot("Public","Privacy_Settings_Private");
    }
    @Test
    public void test_simulcast_4char() throws UiObjectNotFoundException, IOException {
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_SLV_VIDEO);
        getObjectById(Me.LIVE_CONFIGURATION_SLV_SHARE_CONTENT).clearTextField();
        String expect = getRandomString(4);
        shellInputText(expect);
        clickById(Me.LIVE_CONFIGURATION_DONE_SLV_VIDEO);

        openAppByPackageName(App.SIOEYE_PACKAGE_NAME_USA);
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_SLV_VIDEO);
        String active = getObjectById(Me.LIVE_CONFIGURATION_SLV_SHARE_CONTENT).getText();
        //expect=expect.substring(0,70);
        Asst.assertEquals("SLV:",expect,active);
        gDevice.pressBack();
        gDevice.pressBack();
    }
    @Test
    public void test_simulcast_70char() throws UiObjectNotFoundException, IOException {
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_SLV_VIDEO);
        getObjectById(Me.LIVE_CONFIGURATION_SLV_SHARE_CONTENT).clearTextField();
        String expect = getRandomString(70);
        shellInputText(expect);
        clickById(Me.LIVE_CONFIGURATION_DONE_SLV_VIDEO);

        openAppByPackageName(App.SIOEYE_PACKAGE_NAME_USA);
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_SLV_VIDEO);
        String active = getObjectById(Me.LIVE_CONFIGURATION_SLV_SHARE_CONTENT).getText();
        //expect=expect.substring(0,70);
        Asst.assertEquals("SLV:",expect,active);
        gDevice.pressBack();
        gDevice.pressBack();
    }
    @Test
    public void test_simulcast_120char() throws UiObjectNotFoundException, IOException {
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_SLV_VIDEO);
        getObjectById(Me.LIVE_CONFIGURATION_SLV_SHARE_CONTENT).clearTextField();
        String expect = getRandomString(100);
        shellInputText(expect);
        clickById(Me.LIVE_CONFIGURATION_DONE_SLV_VIDEO);

        openAppByPackageName(App.SIOEYE_PACKAGE_NAME_USA);
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_SLV_VIDEO);
        String active = getObjectById(Me.LIVE_CONFIGURATION_SLV_SHARE_CONTENT).getText();
        //expect=expect.substring(0,70);
        Asst.assertEquals("SLV:",expect,active);
        gDevice.pressBack();
        gDevice.pressBack();
    }
    @Test
    public void test_simulcast_500char() throws UiObjectNotFoundException, IOException {
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_SLV_VIDEO);
        getObjectById(Me.LIVE_CONFIGURATION_SLV_SHARE_CONTENT).clearTextField();
        String expect = getRandomString(500);
        shellInputText(expect);
        clickById(Me.LIVE_CONFIGURATION_DONE_SLV_VIDEO);

        openAppByPackageName(App.SIOEYE_PACKAGE_NAME_USA);
        MeAction.navToLiveConfiguration();
        clickById(Me.LIVE_CONFIGURATION_SLV_VIDEO);
        String active = getObjectById(Me.LIVE_CONFIGURATION_SLV_SHARE_CONTENT).getText();
        expect=expect.substring(0,120);
        Asst.assertEquals("SLV:",expect.length(),active.length());
        gDevice.pressBack();
        gDevice.pressBack();
    }
    public void test_public_viewing_link(){

    }


}