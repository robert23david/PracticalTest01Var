package practicaltest01var03.eim.systems.cs.pub.ro.practicaltest01var03;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var03Service extends Service {
    public PracticalTest01Var03Service() {
    }
    ProcessingThread processingThread;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        processingThread.stopThread();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String user = intent.getStringExtra("user");
        String grupa = intent.getStringExtra("grupa");
        processingThread = new ProcessingThread(this, user, grupa);
        processingThread.start();
        return START_REDELIVER_INTENT;
    }
}
