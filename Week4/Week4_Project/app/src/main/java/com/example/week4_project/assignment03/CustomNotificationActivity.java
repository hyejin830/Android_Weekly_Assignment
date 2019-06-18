package com.example.week4_project.assignment03;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Switch;

import com.example.week4_project.R;

public class CustomNotificationActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int NOTIFICATION_ID = 888;
    private static final String CHANNEL_ID_DEV = "development_environment";
    private static final String CHANNEL_ID_NOTICE = "notice";
    private static final String TAG = "state";

    private NotificationManagerCompat notificationManager;
    private Notification customNotification;
    private RemoteViews notificationLayout;
    private IntentFilter intentFilter;
    private Switch stateSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_notification);

        initView();

        // 노티피케이션 초기화
        initNotification();

        // 실행되자마자 알림
        notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(TAG, NOTIFICATION_ID, customNotification);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // 3-1) 인텐트 필터를 통해 브로드캐스드 등록
        intentFilter = new IntentFilter();
        intentFilter.addAction(getString(R.string.text_action_end_noti));
        intentFilter.addAction(getString(R.string.text_action_setting_noti));
        registerReceiver(buttonBroadCastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(buttonBroadCastReceiver);
    }

    private void initView() {
        stateSwitch = findViewById(R.id.switch_state);
        stateSwitch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_state:
                if (stateSwitch.isChecked()) { // 체크 - staging
                    notificationLayout.setTextViewText(R.id.notification_title, getString(R.string.custom_notification_staging));
                    notificationManager.notify(TAG, NOTIFICATION_ID, customNotification);
                } else { // default
                    notificationLayout.setTextViewText(R.id.notification_title, getString(R.string.custom_notification_dev));
                    notificationManager.notify(TAG, NOTIFICATION_ID, customNotification);
                }
                break;
        }

    }

    private void initNotification() {

        // 1) 채널 생성 (오레오 버전 이상부터는 무조건 생성해야한다)
        createNotificationChannel();

        // 2) 커스텀 레이아웃   Get the layouts to use in the custom notification
        notificationLayout = new RemoteViews(getPackageName(), R.layout.custom_notification);

        // 3) 버튼 이벤트를 위한 PendingIntent
        Intent endNotificationIntent = new Intent(getString(R.string.text_action_end_noti));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, endNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationLayout.setOnClickPendingIntent(R.id.btn_end_notification, pendingIntent);

        // 3) 해당 알림을 선택했을 경우 셋팅 페이지로 가기 위한 이벤트
        Intent settingNotificationIntent = new Intent(getString(R.string.text_action_setting_noti));
        PendingIntent settingPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, settingNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationLayout.setOnClickPendingIntent(R.id.setting_linear_layout, settingPendingIntent);

        // Apply the layouts to the notification
        customNotification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID_DEV)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setOngoing(true)   // 사라지지 않게 할 것
                .setCustomContentView(notificationLayout) // 적용
                .build();

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel DevChannel = new NotificationChannel(CHANNEL_ID_DEV, name, importance);
            DevChannel.setDescription(description);

            NotificationChannel NoticeChannel = new NotificationChannel(CHANNEL_ID_NOTICE, getString(R.string.text_channel_name_notice), importance);
            NoticeChannel.setDescription(description);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(DevChannel);
            notificationManager.createNotificationChannel(NoticeChannel);
        }
    }

    private BroadcastReceiver buttonBroadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            // 닫힘 버튼을 클릭했을 경우 해당 태그와 아이디를 가진 노티를 취소
            if (intent.getAction().equalsIgnoreCase(getString(R.string.text_action_end_noti))) {
                notificationManager.cancel(TAG, NOTIFICATION_ID);
            } // 액션이 셋팅일 경우
            else if (intent.getAction().equalsIgnoreCase(getString(R.string.text_action_setting_noti))) {
                Intent startSettingIntent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                startSettingIntent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                startSettingIntent.putExtra(Settings.EXTRA_CHANNEL_ID, CHANNEL_ID_DEV);
                startActivity(startSettingIntent);

                // 상태바 close
                Intent doCloseSystemDialogIntent = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
                context.sendBroadcast(doCloseSystemDialogIntent);
            }
        }
    };
}
