package com.lansun.mobile.assistant.notifydownload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.lasun.mobile.assistant.utils.OpenLocaFiles;
import com.lasun.mobile.client.assistant.activity.GoodsDetailsActivity;
import com.lasun.mobile.client.assistant.activity.MainActivity2;
import com.lasun.mobile.client.assistant.activity.R;

@SuppressLint("HandlerLeak")
public class DocDownloadService extends IntentService {
    private final int MSG_WHAT_EXSITS = 0;
    private final int MSG_WHAT_DOWNLOAD_START = 1;
    private final int MSG_WHAT_DOWNLOAD_COMPLETE = 2;
    private final int MSG_WHAT_DOWNLOAD_FAILE = 3;
    private final int notiID = 1;
    private Notification noti;
    private NotificationManager manager;
    private long totalSize;
    private String docName;
    private String docUrl;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
            case MSG_WHAT_EXSITS:
                Intent intent = OpenLocaFiles.getPPTFileIntent("/mnt/sdcard/lasunMobileAssitant/" + docName);
                List<ResolveInfo> queryIntentActivities = DocDownloadService.this.getPackageManager().queryIntentActivities(intent, 0);
                if (queryIntentActivities != null && queryIntentActivities.size() > 0) {
                    startActivity(intent);
                } else {

                    Toast.makeText(DocDownloadService.this, "没有相关软件可以打开该文档！", Toast.LENGTH_SHORT).show();
                }
                break;
            case MSG_WHAT_DOWNLOAD_START:
                Toast.makeText(DocDownloadService.this, "执行下载任务", Toast.LENGTH_SHORT).show();
                noti.contentView.setTextViewText(R.id.download_name, docName);
                noti.contentView.setTextViewText(R.id.currentSize, "0kb");
                noti.contentView.setTextViewText(R.id.totalSize, totalSize + "kb");
                noti.contentView.setProgressBar(R.id.pb_download, (int) totalSize, 0, false);
                manager.notify(notiID, noti);
                break;
            case GlobalUtils.MSG_WHAT_PROGRESS:
                noti.contentView.setTextViewText(R.id.download_name, docName);
                noti.contentView.setTextViewText(R.id.currentSize, msg.arg1 + "kb");
                noti.contentView.setTextViewText(R.id.totalSize, totalSize + "kb");
                noti.contentView.setProgressBar(R.id.pb_download, (int) totalSize, msg.arg1, false);
                manager.notify(notiID, noti);
                break;
            case MSG_WHAT_DOWNLOAD_FAILE:

                Notification n = new Notification(R.drawable.ic_launcher, "提示信息", System.currentTimeMillis());
                n.setLatestEventInfo(DocDownloadService.this, "下载失败", docName + "下载失败！", PendingIntent.getActivity(DocDownloadService.this, 1, new Intent(DocDownloadService.this, MainActivity2.class) {
                }, PendingIntent.FLAG_UPDATE_CURRENT));
                n.defaults = Notification.DEFAULT_LIGHTS;
                n.flags = Notification.FLAG_AUTO_CANCEL;
                manager.notify(2, n);
                break;
            case MSG_WHAT_DOWNLOAD_COMPLETE:
                Notification no = new Notification(R.drawable.icon, "下载完成！", System.currentTimeMillis());
                no.setLatestEventInfo(DocDownloadService.this, "任务完成", docName + "下载完成！", PendingIntent.getActivity(DocDownloadService.this, notiID, OpenLocaFiles.getPPTFileIntent("/mnt/sdcard/lasunMobileAssitant/" + docName), PendingIntent.FLAG_UPDATE_CURRENT));
                no.defaults = Notification.DEFAULT_LIGHTS;
                no.flags = Notification.FLAG_AUTO_CANCEL;
                manager.notify(notiID, no);
                break;
            }
        };
    };

    public DocDownloadService() {
        super("workThread");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        docName = intent.getStringExtra(GlobalUtils.EXTRA_DOC_NAME);
        docUrl = intent.getStringExtra(GlobalUtils.EXTRA_DOC_URL);
        File file = new File("/mnt/sdcard/lasunMobileAssitant/" + docName);
        Log.i("info", "filepath" + file.getPath());
        if (file.exists()) {
            handler.sendEmptyMessage(MSG_WHAT_EXSITS);
            return;
        }
        handler.sendEmptyMessage(MSG_WHAT_DOWNLOAD_START);
        HttpEntity entity = HttpUtil.getEntity(docUrl, null, HttpUtil.METHOD_GET);
        totalSize = HttpUtil.getLength(entity) / 1024;
        try {
            DownloadUtil.save(HttpUtil.getStream(entity), file, handler);
            handler.sendEmptyMessage(MSG_WHAT_DOWNLOAD_COMPLETE);
        } catch (IOException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(MSG_WHAT_DOWNLOAD_FAILE);
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        noti = new Notification(R.drawable.icon, "开始下载", System.currentTimeMillis());
        noti.defaults = Notification.DEFAULT_LIGHTS;
        noti.flags = Notification.FLAG_NO_CLEAR;
        noti.contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity2.class), PendingIntent.FLAG_UPDATE_CURRENT);
        noti.contentView = new RemoteViews(getPackageName(), R.layout.notification);
    }

}