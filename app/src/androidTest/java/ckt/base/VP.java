package ckt.base;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.UiDevice;

import com.sioeye.MainActivity;
import com.squareup.spoon.Spoon;

import org.junit.Rule;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import ckt.tools.Common;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by admin on 2016/9/6.
 */
public class VP {
    private static Logger logger = Logger.getLogger(VP.class.getName());
    public static UiDevice gDevice=null;
    public static Instrumentation instrument=null;
    public static Context context=null;
    public static boolean isStop=false;
    public static String logAbsPath;
    public static String logName;
    private static Thread logThread;
    /**
     * You can get a UiDevice Instance if you call this method.
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    public static void initDevice() {
        if (instrument==null){
            instrument = InstrumentationRegistry.getInstrumentation();
            context = instrument.getContext();
        }
        if (gDevice == null) {
            gDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        }
    }

    public static void logStart() throws InterruptedException {
        Date date = new Date();
        DateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmss");
        logName = "log_"+format2.format(date);
        System.out.println(logName);
        isStop=false;

        String logDir = Environment.getExternalStorageDirectory()+ File.separator+"log";
        logAbsPath=logDir+ File.separator+logName+".txt";
        if (!new File(logDir).exists()){
            new File(logDir).mkdirs();
        }
        if (logThread!=null){
            if (!logThread.isInterrupted()){
                logThread.interrupt();
            }
        }
        logThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Process logcatProcess = null;
                BufferedReader bufferedReader = null;
                try {
                    logcatProcess = Runtime.getRuntime().exec("logcat -v time *.E");
                    bufferedReader = new BufferedReader(new InputStreamReader(logcatProcess.getInputStream()));
                    String line;
                    while(true){
                        if (isStop==true) {
                            logcatProcess.destroy();
                            break;
                        }
                        if ((line = bufferedReader.readLine()) != null){
                            Common.writeToFile(logAbsPath, line, true);
                        }
                    }
                   /* while ((line = bufferedReader.readLine()) != null) {
                        Common.writeToFile(logAbsPath, line, true);
                        if (isStop==true) {
                            logcatProcess.destroy();
                        }
                    }*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        logThread.start();
        Logger.getLogger("start logcat").info(logName);
    }
    public static void logStop(){
        isStop = false;
        Logger.getLogger("stop logcat").info(logName);
    }
    public static void saveLogToReport(){
        Spoon.save(instrument.getContext(), logAbsPath);
        Logger.getLogger("LOGGER").info("spoon save log file "+logAbsPath);
    }
    // boolean Result = ImageLib.sameAs("/mnt/sdcard/other_word.jpg", "/mnt/sdcard/origin.jpg");
}
