package org.kasapbasi.week08004;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Intent myInt;
    myBindingService myService;
    boolean isBound=false;
    TextView tv;

    @Override
    protected void onStop() {
        if(isBound){
            unbindService(mycon);
            isBound=false;
        }

        super.onStop();
    }

    private  ServiceConnection mycon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            isBound=true;
            myService =((myBindingService.myBinder) iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView) findViewById(R.id.tvTimeStamp);

        myInt = new Intent(this,myBindingService.class);

        startService(myInt);
        bindService(myInt, mycon, Context.BIND_AUTO_CREATE);
    }


    public void IBindClick(View V){

        if(isBound){
         tv.setText( myService.getTimestamp());
        }
    }
    public void IBindStop(View v){

        if(isBound){
            unbindService(mycon);
            isBound=false;
        }
      stopService(myInt);

    }
}
