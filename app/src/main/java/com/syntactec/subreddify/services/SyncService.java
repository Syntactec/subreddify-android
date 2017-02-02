package com.syntactec.subreddify.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

/**
 * This class defines a service which will query the RedditResource object however often the user specifies.
 */
public class SyncService extends Service {
    private SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    private int defaultSyncMinutes = 15;
    private int syncMillis = 60000 * preferences.getInt("sync_frequency", defaultSyncMinutes);

    @Override
    public void onCreate() {
        super.onCreate();

/*        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .subreddifyApplicationModule(new SubreddifyApplicationModule(this))
                .build();*/

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
/*        AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime(),
                syncMillis, );*/
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
