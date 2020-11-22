package com.okwy.practiceproject.BroadcastReceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.okwy.practiceproject.R;

public class BroadcastReceiverActivity extends AppCompatActivity {

    private ImageView isChargingImage;
    private TextView isChargingText;

    private IntentFilter chargingIntentFilter;
    private ChargingBroadcastReceiver chargingBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        isChargingImage = findViewById(R.id.isChargingImage);
        isChargingText = findViewById(R.id.isChargingText);


        chargingIntentFilter = new IntentFilter();
        chargingIntentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        chargingIntentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        chargingBroadcastReceiver = new ChargingBroadcastReceiver();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
            showCharging(batteryManager.isCharging());
        } else {
            IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent currentBatteryIntent = registerReceiver(null, intentFilter);

            int batteryStatus = currentBatteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            showCharging(batteryStatus == BatteryManager.BATTERY_STATUS_CHARGING || batteryStatus == BatteryManager.BATTERY_STATUS_FULL);
        }

        registerReceiver(chargingBroadcastReceiver, chargingIntentFilter);
    }

    public void showCharging(boolean isCharging) {
        if (isCharging) {
            isChargingImage.setImageResource(R.drawable.ic_battery_charging_full);
            isChargingText.setText("The Device is Charging");
        } else {
            isChargingImage.setImageResource(R.drawable.ic_battery);
            isChargingText.setText("The Device is Not Charging");
        }
    }

    public class ChargingBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            showCharging(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED));
        }
    }
}