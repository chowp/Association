package com.example.administrator.association;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import com.example.administrator.association.MainActivity;
import java.util.List;

/**
 * Created by Administrator on 3/15/2016.
 */

// Broadcast receiver class called its receive method
// when number of wifi connections changed
public class WifiReceiver extends BroadcastReceiver {
    List<ScanResult> wifiList;
    StringBuilder sb = new StringBuilder();


    // This method call when number of wifi connections changed
    public void onReceive(Context c, Intent intent) {
        sb = new StringBuilder();
        wifiList = MainActivity.mainWifi.getScanResults();
        sb.append("\n        Number Of Wifi connections :"+wifiList.size()+"\n\n");

        for(int i = 0; i < wifiList.size(); i++){

            sb.append(new Integer(i+1).toString() + ". ");
            sb.append((wifiList.get(i)).toString());
            sb.append("\n\n");
        }

        MainActivity.mainText.setText(sb);
    }
}
