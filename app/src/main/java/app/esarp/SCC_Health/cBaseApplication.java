package app.esarp.SCC_Health;

import android.app.Application;

import app.esarp.bluetooth.library.BluetoothSPP;

/**
 * Created by mrahman8 on 2/7/2017.
 */

public class cBaseApplication extends Application {
    public BluetoothSPP bluetoothSPP;
    //public static volatile int fileSeq=1;

    @Override
    public void onCreate()
    {
        super.onCreate();
        bluetoothSPP = new BluetoothSPP(this);


    }
    }



