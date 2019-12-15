package com.example.monopoly;

import android.content.Context;
import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;

import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.Strategy;

public class Connection {

    Context context;



//    private void startAdvertising() {
//        Nearby.getConnectionsClient(context)
//                .startAdvertising(
//                        /* endpointName= */ "Device A",
//                        /* serviceId= */ "com.example.package_name",
//                        mConnectionLifecycleCallback,
//                        new AdvertisingOptions(Strategy.P2P_CLUSTER));
//    }

//    private void startDiscovery() {
//        Nearby.getConnectionsClient(context)
//                .startDiscovery(
//                        /* serviceId= */ "com.example.package_name",
//                        mEndpointDiscoveryCallback,
//                        new DiscoveryOptions(Strategy.P2P_CLUSTER));
//    }
}
