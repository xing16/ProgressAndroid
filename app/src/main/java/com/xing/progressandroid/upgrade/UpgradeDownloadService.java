package com.xing.progressandroid.upgrade;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.Toast;

import com.xing.progressandroid.R;
import com.xing.progressandroid.utils.AppUtil;

/**
 * 后台下载服务
 */
public class UpgradeDownloadService extends Service {

    private static final String CHANNEL_ID = "upgrade_channel_id";
    private static final String CHANNEL_NAME = "upgrade_channel_name";
    private static final int NOTIFY_ID = 0x10001;

    public static boolean isRunning;
    private NotificationManager notificationManager;
    private DownloadBinder downloadBinder = new DownloadBinder();

    /**
     * 绑定下载服务
     *
     * @param context
     * @param conn
     */
    public static void bindService(Context context, ServiceConnection conn) {
        Intent intent = new Intent(context, UpgradeDownloadService.class);
        context.startService(intent);
        context.bindService(intent, conn, Context.BIND_AUTO_CREATE);
        isRunning = true;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return downloadBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    /**
     * 显示通知
     */
    private void showNotification(int progress) {
        // 兼容 8.0 以上，通知渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            // 设置绕过免打扰模式
            notificationChannel.setBypassDnd(false);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(AppUtil.getAppName(this))
                .setContentText("正在下载")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOngoing(true)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setProgress(100, progress, false)
                .build();
        notificationManager.notify(NOTIFY_ID, notification);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        isRunning = false;
        return super.onUnbind(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        notificationManager = null;
    }

    class DownloadBinder extends Binder {

        OnDownloadListener listener;
        UpgradeAppBean upgradeAppBean;

        public void start(UpgradeAppBean upgradeAppBean, OnDownloadListener listener) {
            startDownload(upgradeAppBean, listener);
        }

        public void stop() {

        }
    }

    /**
     * 真正开始下载模块
     *
     * @param upgradeAppBean
     * @param listener
     */
    private void startDownload(UpgradeAppBean upgradeAppBean, OnDownloadListener listener) {
        if (upgradeAppBean == null) {
            return;
        }
        String apkUrl = upgradeAppBean.getApkUrl();
        if (TextUtils.isEmpty(apkUrl)) {
            Toast.makeText(this, "下载 url 错误", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean forceUpgrade = upgradeAppBean.isForceUpgrade();
//        upgradeAppBean.getHttpManager().download(apkUrl, Environment.getExternalStorageDirectory().getAbsolutePath() + "/apk", "upgrade_demo.apk", new up);
        showNotification(11);
    }
}
