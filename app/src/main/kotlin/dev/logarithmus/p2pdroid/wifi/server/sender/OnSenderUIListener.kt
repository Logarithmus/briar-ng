package dev.logarithmus.p2pdroid.wifi.server.sender

import android.net.wifi.p2p.WifiP2pDevice
import android.net.wifi.p2p.WifiP2pDeviceList

interface OnSenderUIListener {
    fun redirectToDeviceListScreen()

    /*
     * Device List Details
     * */
    fun onScanStarted()
    fun onDeviceAvailable(wifiP2pDeviceList: WifiP2pDeviceList)
    fun redirectToProcessScreen(wifiP2pDevice: WifiP2pDevice)
    fun onConnectionCompleted()
    fun onDataTransferStarted()
    fun onDataTransferCompleted(s: String)
    fun redirectToSuccessScreen(imageResourceId: Int, message: String)
}