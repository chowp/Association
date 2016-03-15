package com.example.administrator.association;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiManager;

import com.example.administrator.association.MainActivity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
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
        /*wifiList = MainActivity.mainWifi.getScanResults();
        //sb.append("\n        Number Of Wifi connections :"+wifiList.size()+"\n\n");

        for(int i = 0; i < wifiList.size(); i++){

            sb.append(new Integer(i+1).toString() + ". ");
            sb.append((wifiList.get(i)).toString());
            sb.append("\n\n");
        }*/
        if (intent.getAction().equals(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION))
        {
            SupplicantState state = (SupplicantState) intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss     ");
            Date curDate = new Date(System.currentTimeMillis()); //get the current time
            String str = formatter.format(curDate);
            switch (state)
            {
                case ASSOCIATED:
                    sb.append(str + " Association completed!\n");
                case ASSOCIATING:
                    sb.append(str + "ASSOCIATING\n");
                case AUTHENTICATING:
                    sb.append(str + "AUTHENTICATING \n");
                case COMPLETED:
                    sb.append(str + "COMPLETED \n");
                case DISCONNECTED:
                    sb.append(str +  "DISCONNECTED \n");
                case DORMANT:
                    sb.append(str + "DORMANT \n");
                case FOUR_WAY_HANDSHAKE:
                    sb.append(str + "FOUR_WAY_HANDSHAKE \n");
                case GROUP_HANDSHAKE:
                    sb.append(str + "GROUP_HANDSHAKE \n");
                case INACTIVE:
                    sb.append(str + "INACTIVE \n");
                case INTERFACE_DISABLED:
                    sb.append(str + "INTERFACE_DISABLED \n");
                case INVALID:
                    sb.append(str + "INVALID\n");
                case SCANNING:
                    sb.append(str + "SCANNING\n");
                case UNINITIALIZED:
                    sb.append(str + "UNINITIALIZED\n");
                default:
                    sb.append(str+ "UNKOWN STATE\n\n");
            }
        }
        MainActivity.mainText.setText(sb);
    }
}
