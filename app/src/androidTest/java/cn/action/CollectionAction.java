package cn.action;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import ckt.base.VP2;
import cn.page.MePage;
import cn.page.PlayPage;

/**
 * Created by zyj on 2017/9/29.
 */

public class CollectionAction extends VP2 {
    private static Logger logger = Logger.getLogger(BroadcastAction.class.getName());

    //获取收藏列表size
    public static int  getCollectionSize(){
        waitUntilFind(MePage.BROADCAST_VIEW,5000);
        UiObject2 list=getObject2ById(MePage.BROADCASTS_LIST);
        List<UiObject2> collection=list.findObjects(By.clazz(android.widget.LinearLayout.class));
        int size=collection.size();
        return size;
    }
    //随机获取一个Collection对象的index
    public static int getRandomCollectionIndex(){
        waitHasObject(MePage.BROADCASTS_LIST,1000);
        UiObject2 view = gDevice.findObject(By.res(MePage.BROADCASTS_LIST));
        List<UiObject2> collection = view.findObjects(By.clazz(android.widget.LinearLayout.class).depth(1));
        int size = collection.size();
        logger.info("getRandomCollectionIndex-size:"+size);
        Random random = new Random();
        int rd = random.nextInt(size);
        logger.info("size-"+size+"random:"+rd);
        return  rd==0?rd:rd-1;
    }
    //随机获取一个broadcasts对象
    public static UiObject2 getRandomCollectionElement(int index){
        waitHasObject(MePage.BROADCASTS_LIST,10000);
        UiObject2 view = gDevice.findObject(By.res(MePage.BROADCASTS_LIST));
        List<UiObject2> collection = view.findObjects(By.clazz(android.widget.LinearLayout.class).depth(1));
        logger.info("getRandomCollectionElement:"+collection.size());
        UiObject2 broadcast = collection.get(index);
        return  broadcast;
    }
    //播放界面点击收藏按钮
    public static void getClickCollection(){
        clickById(PlayPage.COLLECTION);
    }

}
