package ckt.tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Log;
import java.io.File;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import ckt.base.VP3;

import static android.os.Environment.getExternalStorageDirectory;

/**
 * Created by elon on 2016/10/11.
 */
public final  class Spoon2 {
    static final String SPOON_SCREENSHOTS = "spoon-screenshots";
    static final String SPOON_FILES = "spoon-files";
    static final String NAME_SEPARATOR = "_";
    static final String TEST_CASE_CLASS_JUNIT_3 = "android.test.InstrumentationTestCase";
    static final String TEST_CASE_METHOD_JUNIT_3 = "runMethod";
    static final String TEST_CASE_CLASS_JUNIT_4 = "org.junit.runners.model.FrameworkMethod$1";
    static final String TEST_CASE_METHOD_JUNIT_4 = "runReflectiveCall";
    static final String TEST_CASE_CLASS_CUCUMBER_JVM = "cucumber.runtime.model.CucumberFeature";
    static final String TEST_CASE_METHOD_CUCUMBER_JVM = "run";
    private static final String EXTENSION = ".png";
    private static final String TAG = "Spoon";
    private static final Object LOCK = new Object();
    private static final Pattern TAG_VALIDATION = Pattern.compile("[a-zA-Z0-9_-]+");
    private static final int LOLLIPOP_API_LEVEL = 21;
    private static final int MARSHMALLOW_API_LEVEL = 23;
    /** Returns the test class element by looking at the method InstrumentationTestCase invokes. */
    private static StackTraceElement extractStackElement(StackTraceElement[] trace, int i) {
        //Stacktrace length changed in M
        int testClassTraceIndex = Build.VERSION.SDK_INT >= MARSHMALLOW_API_LEVEL ? (i - 2) : (i - 3);
        return trace[testClassTraceIndex];
    }
    private static File filesDirectory(UiDevice uiDevice, String directoryType, String testClassName,
                                       String testMethodName) throws IllegalAccessException {
        File directory;
        directory = new File(getExternalStorageDirectory(), "app_" + directoryType);
        File dirClass = new File(directory, testClassName);
        File dirMethod = new File(dirClass, testMethodName);
        createDir(dirMethod);
        return dirMethod;
    }
    private static void createDir(File dir) throws IllegalAccessException {
        File parent = dir.getParentFile();
        if (!parent.exists()) {
            createDir(parent);
        }
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IllegalAccessException("Unable to create output dir: " + dir.getAbsolutePath());
        }
        //chmodPlusRWX(dir);
    }
    private static File obtainScreenshotDirectory(UiDevice uiDevice, String testClassName,
                                                  String testMethodName) throws IllegalAccessException {
        return filesDirectory( uiDevice, SPOON_SCREENSHOTS, testClassName, testMethodName);
    }
    private static File screenshot(UiDevice uiDevice, String tag, String testClassName,
                                  String testMethodName) {
        if (!TAG_VALIDATION.matcher(tag).matches()) {
            throw new IllegalArgumentException("Tag must match " + TAG_VALIDATION.pattern() + ".");
        }
        try {
            File screenshotDirectory =
                    obtainScreenshotDirectory(uiDevice, testClassName,
                            testMethodName);
            String screenshotName = System.currentTimeMillis() + NAME_SEPARATOR + tag + EXTENSION;
            File screenshotFile = new File(screenshotDirectory, screenshotName);
            Logger.getLogger("").info(screenshotFile.getAbsolutePath());
            uiDevice.takeScreenshot(screenshotFile);
            Log.d(TAG, "Captured screenshot '" + tag + "'.");
            return screenshotFile;
        } catch (Exception e) {
            throw new RuntimeException("Unable to capture screenshot.", e);
        }
    }
    private static File screenshot(UiDevice uiDevice, String tag, String testClassName,
                                  String testMethodName,String drawText) {
        if (!TAG_VALIDATION.matcher(tag).matches()) {
            throw new IllegalArgumentException("Tag must match " + TAG_VALIDATION.pattern() + ".");
        }
        try {
            File screenshotDirectory =
                    obtainScreenshotDirectory(uiDevice, testClassName,
                            testMethodName);
            String screenshotName = System.currentTimeMillis() + NAME_SEPARATOR + tag + EXTENSION;
            File screenshotFile = new File(screenshotDirectory, screenshotName);
            Logger.getLogger("").info(screenshotFile.getAbsolutePath());
            uiDevice.takeScreenshot(screenshotFile);
            Bitmap bitmap= BitmapFactory.decodeFile(screenshotFile.getAbsolutePath());
            Bitmap drawBitmap= VP3.drawTextBitmap(bitmap, drawText);
            VP3.saveBitMapToSdcard(drawBitmap, screenshotFile.getAbsolutePath());
            Log.d(TAG, "Captured screenshot '" + tag + "'.");
            return screenshotFile;
        } catch (Exception e) {
            throw new RuntimeException("Unable to capture screenshot.", e);
        }
    }
    private static File screenshot(UiDevice uiDevice, String tag, String testClassName,
                                    String testMethodName,String drawText,UiObject uiObject) throws UiObjectNotFoundException {
        if (!TAG_VALIDATION.matcher(tag).matches()) {
            throw new IllegalArgumentException("Tag must match " + TAG_VALIDATION.pattern() + ".");
        }
        Rect rect = uiObject.getBounds();
        try {
            File screenshotDirectory =
                    obtainScreenshotDirectory(uiDevice, testClassName,
                            testMethodName);
            String screenshotName = System.currentTimeMillis() + NAME_SEPARATOR + tag + EXTENSION;
            File screenshotFile = new File(screenshotDirectory, screenshotName);
            Logger.getLogger("").info(screenshotFile.getAbsolutePath());
            uiDevice.takeScreenshot(screenshotFile);
            Bitmap bitmap= BitmapFactory.decodeFile(screenshotFile.getAbsolutePath());
            Bitmap drawBitmap= VP3.drawTextRectBitmap(bitmap, drawText,rect);
            VP3.saveBitMapToSdcard(drawBitmap, screenshotFile.getAbsolutePath());
            Log.d(TAG, "Captured screenshot '" + tag + "'.");
            return screenshotFile;
        } catch (Exception e) {
            throw new RuntimeException("Unable to capture screenshot.", e);
        }
    }
    private static File screenshot(UiDevice uiDevice, String tag, String testClassName,
                                   String testMethodName,String drawText,UiObject2 uiObject2) throws UiObjectNotFoundException {
        if (!TAG_VALIDATION.matcher(tag).matches()) {
            throw new IllegalArgumentException("Tag must match " + TAG_VALIDATION.pattern() + ".");
        }
        Rect rect = uiObject2.getVisibleBounds();
        try {
            File screenshotDirectory =
                    obtainScreenshotDirectory(uiDevice, testClassName,
                            testMethodName);
            String screenshotName = System.currentTimeMillis() + NAME_SEPARATOR + tag + EXTENSION;
            File screenshotFile = new File(screenshotDirectory, screenshotName);
            Logger.getLogger("").info(screenshotFile.getAbsolutePath());
            uiDevice.takeScreenshot(screenshotFile);
            Bitmap bitmap= BitmapFactory.decodeFile(screenshotFile.getAbsolutePath());
            Bitmap drawBitmap= VP3.drawTextRectBitmap(bitmap, drawText,rect);
            VP3.saveBitMapToSdcard(drawBitmap, screenshotFile.getAbsolutePath());
            Log.d(TAG, "Captured screenshot '" + tag + "'.");
            return screenshotFile;
        } catch (Exception e) {
            throw new RuntimeException("Unable to capture screenshot.", e);
        }
    }
    static StackTraceElement findTestClassTraceElement(StackTraceElement[] trace) {
        for (int i = trace.length - 1; i >= 0; i--) {
            StackTraceElement element = trace[i];
            if (TEST_CASE_CLASS_JUNIT_3.equals(element.getClassName()) //
                    && TEST_CASE_METHOD_JUNIT_3.equals(element.getMethodName())) {
                return extractStackElement(trace, i);
            }

            if (TEST_CASE_CLASS_JUNIT_4.equals(element.getClassName()) //
                    && TEST_CASE_METHOD_JUNIT_4.equals(element.getMethodName())) {
                return extractStackElement(trace, i);
            }
            if (TEST_CASE_CLASS_CUCUMBER_JVM.equals(element.getClassName()) //
                    && TEST_CASE_METHOD_CUCUMBER_JVM.equals(element.getMethodName())) {
                return extractStackElement(trace, i);
            }
        }

        throw new IllegalArgumentException("Could not find test class!");
    }

    public static void screenshot(UiDevice uiDevice, String tag) {
        StackTraceElement testClass = findTestClassTraceElement(Thread.currentThread().getStackTrace());
        String className = testClass.getClassName().replaceAll("[^A-Za-z0-9._-]", "_");
        String methodName = testClass.getMethodName();
        Logger.getLogger("screenshot").info(testClass.getClassName()+"|"+className+"|"+methodName+"|"+tag);
        screenshot(uiDevice,tag,className,methodName);
    }
    public static void screenshot(UiDevice uiDevice, String tag,String drawText) {
        StackTraceElement testClass = findTestClassTraceElement(Thread.currentThread().getStackTrace());
        String className = testClass.getClassName().replaceAll("[^A-Za-z0-9._-]", "_");
        String methodName = testClass.getMethodName();
        Logger.getLogger("screenshot").info(testClass.getClassName()+"|"+className+"|"+methodName+"|"+tag);
        screenshot(uiDevice,tag,className,methodName,drawText);
    }
    //截取图片,1：图片上写文字，2：图片的给定的UiObject2对象上绘制边框，
    public static void screenshot(UiDevice uiDevice, String tag, String drawText,UiObject2 uiObject2) throws UiObjectNotFoundException {
        StackTraceElement testClass = findTestClassTraceElement(Thread.currentThread().getStackTrace());
        String className = testClass.getClassName().replaceAll("[^A-Za-z0-9._-]", "_");
        String methodName = testClass.getMethodName();
        Logger.getLogger("screenshot").info(testClass.getClassName()+"|"+className+"|"+methodName+"|"+tag);
        screenshot(uiDevice,tag,className,methodName,drawText,uiObject2);
    }
    //截取图片,1：图片上写文字，2：图片的给定的UiObject对象上绘制边框，
    public static void screenshot(UiDevice uiDevice,String tag, String drawText,UiObject uiObject) throws UiObjectNotFoundException {
        StackTraceElement testClass = findTestClassTraceElement(Thread.currentThread().getStackTrace());
        String className = testClass.getClassName().replaceAll("[^A-Za-z0-9._-]", "_");
        String methodName = testClass.getMethodName();
        Logger.getLogger("screenshot").info(testClass.getClassName()+"|"+className+"|"+methodName+"|"+tag);
        screenshot(uiDevice,tag,className,methodName,drawText,uiObject);
    }
}
