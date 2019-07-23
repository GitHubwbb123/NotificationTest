package com.wxb.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                /*其中Android系统在 8.0 以后增加了通知通道*/
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
                    //如果手机的系统大于8.0也就是26，需要建立通知通道
                    String channelId="notification_simple";
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    NotificationChannel channel = new NotificationChannel(channelId, "simple", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel);
                    Notification notification = new NotificationCompat.Builder(this,channelId)
                           //NotificationCompat.Builder(this)已经失效
                            .setContentTitle("这是一个通知")
                            .setContentText("这是通知的内容")
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                            .build();
                    notificationManager.notify(1, notification);
                }
                else{
                    String channelId="notification_simple";
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    Notification notification = new NotificationCompat.Builder(this,channelId)
                            .setContentTitle("这是一个通知")
                            .setContentText("这是通知的内容")
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                            .build();
                    notificationManager.notify(1, notification);

                }
                break;
            default:
                break;
        }
    }
}
