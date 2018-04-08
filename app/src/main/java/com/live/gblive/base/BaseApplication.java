package com.live.gblive.base;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.benjamin.api.ApiConfig;
import com.benjamin.app.Global;
import com.live.gblive.utils.LogUtil;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

/**
 * author: xguobin
 * email:xguobin12@163.com
 * created on: 2018/1/8 14:16
 * description:
 */
public class BaseApplication extends Application {
    private static Context applicationContext;

    public static Context getContext() {
        return applicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        ApiConfig.init(Constants.API);
        Global.init(this);

        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:友盟 app key
         * 参数3:友盟 channel
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret
         */
        UMConfigure.init(this, "5aa378398f4a9d586c000026", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "55701bae3e96c977296884af6162add0");
        initPush();
    }

    private void initPush() {
        PushAgent mPushAgent = PushAgent.getInstance(this);
        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            /**
             * 自定义消息的回调方法
             */
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {
                LogUtil.e("dealWithCustomMessage:" + JSON.toJSONString(msg));
            }

            /**
             * 自定义通知栏样式的回调方法
             */
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                LogUtil.e("getNotification:" +JSON.toJSONString(msg));
                switch (msg.builder_id) {
                    case 1:
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

        /**
         * 自定义行为的回调处理，参考文档：高级功能-通知的展示及提醒-自定义通知打开动作
         * UmengNotificationClickHandler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
            }
        };
        //使用自定义的NotificationHandler，来结合友盟统计处理消息通知，参考http://bbs.umeng.com/thread-11112-1-1.html
        //CustomNotificationHandler notificationClickHandler = new CustomNotificationHandler();
        mPushAgent.setNotificationClickHandler(notificationClickHandler);

        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                LogUtil.e("注册成功会返回deviceToken:" +deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                LogUtil.e("注册失败:" +s+","+s1);
            }
        });
    }
}
