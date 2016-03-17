package com.example.administrator.association;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.example.administrator.association.MainActivity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import android.app.AlertDialog;
import android.widget.Toast;

import static android.widget.Toast.*;

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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss     ");
        Date curDate = new Date(System.currentTimeMillis()); //get the current time
        //String str = formatter.format(curDate);
        long str = System.currentTimeMillis();
        if (intent.getAction().equals(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION))
        {
            SupplicantState state = (SupplicantState) intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE);
            String ssid = MainActivity.mainWifi.getConnectionInfo().getSSID();
            //new AlertDialog.Builder(c).setTitle("state type").setMessage(state.toString()).show();
            makeText(c.getApplicationContext(), ssid + ":" + state.toString(), LENGTH_LONG).show();
            sb.append(" # " + str + "  " + state.toString() + "  -->" + ssid   + "\n");
            /*switch (state)
            {
                case ASSOCIATED:
                    sb.append("  " + str + "ASSOCIATED\n");
                    break;
                case ASSOCIATING:
                    sb.append("  " + str + "ASSOCIATING\n");
                    break;
                case AUTHENTICATING:
                    sb.append("  " + str + "AUTHENTICATING \n");
                    break;
                case COMPLETED:
                    sb.append("  " + str + "COMPLETED \n");
                    break;
                case DISCONNECTED:
                    sb.append("  " + str +  "DISCONNECTED \n");
                    break;
                case DORMANT:
                    sb.append("  " + str + "DORMANT \n");
                    break;
                case FOUR_WAY_HANDSHAKE:
                    sb.append("  " + str + "FOUR_WAY_HANDSHAKE \n");
                    break;
                case GROUP_HANDSHAKE:
                    sb.append("  " + str + "GROUP_HANDSHAKE \n");
                    break;
                case INACTIVE:
                    sb.append("  " + str + "INACTIVE \n");
                    break;
                case INTERFACE_DISABLED:
                    sb.append("  " + str + "INTERFACE_DISABLED \n");
                    break;
                case INVALID:
                    sb.append("  " + str + "INVALID\n");
                    break;
                case SCANNING:
                    sb.append("  " + str + "SCANNING\n");
                    break;
                case UNINITIALIZED:
                    sb.append("  " + str + "UNINITIALIZED\n");
                    break;
                default:
                    sb.append("  " + str+ "UNKOWN STATE\n\n");
            }*/
        }else if(intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION))
        {
            NetworkInfo.DetailedState state = ((NetworkInfo)intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO)).getDetailedState();
            String ssid = MainActivity.mainWifi.getConnectionInfo().getSSID();
            makeText(c.getApplicationContext(), ssid + "*"+state.toString(), LENGTH_LONG).show();
            sb.append(" & " + str + "  " + state.toString() + "  -->"+ ssid + "\n");
        }
        MainActivity.mainText.append(sb);
    }
}
