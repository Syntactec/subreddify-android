package com.syntactec.subreddify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.syntactec.subreddify.services.SyncService;

/**
 * This class handles the boot event.
 */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            context.startService(new Intent(context, SyncService.class));
        }
    }
}
