package org.kasapbasi.week08004;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Chronometer;

import java.io.FileDescriptor;

/**
 * Created by mcemkasapbasi on 12.04.2018.
 */

public class myBindingService extends Service {

    private Chronometer myChrono;
    IBinder binder = new myBinder();
    public class myBinder extends Binder {

         myBindingService getService(){
             return myBindingService.this;
         }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.v("LOG_TAG", "in onRebind");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {

        Log.v("LOG_TAG", "in onDestroy");
        myChrono.stop();
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("LOG_TAG", "in onCreate");
        myChrono = new Chronometer(this);
        myChrono.setBase(SystemClock.elapsedRealtime());
        myChrono.start();

    }

    public String getTimestamp() {
        long elapsedMillis = SystemClock.elapsedRealtime()
                - myChrono.getBase();
        int hours = (int) (elapsedMillis / 3600000);
        int minutes = (int) (elapsedMillis - hours * 3600000) / 60000;
        int seconds = (int) (elapsedMillis - hours * 3600000 - minutes * 60000) / 1000;
        int millis = (int) (elapsedMillis - hours * 3600000 - minutes * 60000 - seconds * 1000);
        return hours + ":" + minutes + ":" + seconds + ":" + millis;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v("LOG_TAG", "in onBind");
        return binder;
    }
}
