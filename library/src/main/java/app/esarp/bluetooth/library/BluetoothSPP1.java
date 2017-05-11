package app.esarp.bluetooth.library;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BluetoothSPP1 extends Service {
    public BluetoothSPP1() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
