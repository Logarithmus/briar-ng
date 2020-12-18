package dev.logarithmus.p2pdroid.wifi.server

import android.net.wifi.p2p.WifiP2pDevice

class WifiDevice : WifiP2pDevice() {
    @Transient
    var isConnected = false
}