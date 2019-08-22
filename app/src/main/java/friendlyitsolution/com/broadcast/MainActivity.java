package friendlyitsolution.com.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    ImageView img;
    TextView tv;
    RelativeLayout rel;
    BroadcastReceiver receiver;
    IntentFilter iff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rel=findViewById(R.id.rel);
        tv=findViewById(R.id.tv);
        img=findViewById(R.id.img);
        iff=new IntentFilter();
        iff.addAction(Intent.ACTION_BATTERY_CHANGED);

        receiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {


                int btry=intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                int status=intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
                tv.setText(btry+" %");


                if(status==BatteryManager.BATTERY_STATUS_CHARGING)
                {
                    img.setImageDrawable(getResources().getDrawable(R.drawable.icon_yes));
                    rel.setBackgroundColor(getResources().getColor(R.color.md_green_200));

                    Toast.makeText(getApplicationContext(),"YES",Toast.LENGTH_LONG).show();

                }
                else if(status==BatteryManager.BATTERY_STATUS_DISCHARGING)
                {

                    img.setImageDrawable(getResources().getDrawable(R.drawable.icon_no));
                    rel.setBackgroundColor(getResources().getColor(R.color.white));

                    Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_LONG).show();
                }





            }
        };

    }


    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(getApplicationContext(),"Resume",Toast.LENGTH_LONG).show();

        registerReceiver(receiver,iff);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(),"pause",Toast.LENGTH_LONG).show();

        unregisterReceiver(receiver);
    }
}
