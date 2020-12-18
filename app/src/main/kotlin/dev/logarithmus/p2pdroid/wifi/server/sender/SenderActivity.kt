package dev.logarithmus.p2pdroid.wifi.server.sender

import android.net.wifi.p2p.WifiP2pDevice
import android.net.wifi.p2p.WifiP2pDeviceList
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.wifiscanner.listener.WifiP2PConnectionCallback
import com.wifiscanner.service.WifiP2PService
import com.wifiscanner.service.WifiP2PServiceImpl
import dev.logarithmus.p2pdroid.R
import dev.logarithmus.p2pdroid.wifi.server.base.BaseActivity
import dev.logarithmus.p2pdroid.wifi.server.dialogs.DialogEventListener
import dev.logarithmus.p2pdroid.wifi.server.dialogs.DialogHelper

class SenderActivity : BaseActivity(), WifiP2PConnectionCallback, OnSenderUIListener, DialogEventListener {
    private var isSuccess = false
    @JvmField
    var wifiP2PService: WifiP2PService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        initToolBar()
        updateTitle(getString(R.string.title_receiver))
        redirectToDeviceListScreen()
        wifiP2PService = WifiP2PServiceImpl.Builder()
            .setSender(this)
            .setWifiP2PConnectionCallback(this)
            .build()
        (wifiP2PService as WifiP2PServiceImpl?)?.onCreate()
    }

    override fun onResume() {
        super.onResume()
        wifiP2PService?.onResume()
    }

    override fun onStop() {
        super.onStop()
        wifiP2PService?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        wifiP2PService?.onDestroy()
    }

    override fun redirectToDeviceListScreen() {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            DeviceListFragment.newInstance()
        ).commitAllowingStateLoss()
    }

    override fun onScanStarted() {
        val fragment: Fragment = currentFragment!!
        if (fragment is DeviceListFragment) fragment.onScanStarted()
    }

    override fun onDeviceAvailable(wifiP2pDeviceList: WifiP2pDeviceList) {
        val fragment: Fragment = currentFragment!!
        if (fragment is DeviceListFragment) fragment.onDeviceAvailable(wifiP2pDeviceList)
    }

    override fun redirectToProcessScreen(wifiP2pDevice: WifiP2pDevice) {
        getSupportFragmentManager().beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.fragment_container, SenderFragment.newInstance(wifiP2pDevice))
            .commitAllowingStateLoss()
    }

    override fun onConnectionCompleted() {
        val fragment: Fragment = currentFragment!!
        if (fragment is SenderFragment) fragment.onConnectCompleted()
    }

    override fun onDataTransferStarted() {
        val fragment: Fragment = currentFragment!!
        if (fragment is SenderFragment) fragment.onDataStarted()
    }

    override fun onDataTransferCompleted(s: String) {
        redirectToSuccessScreen(R.drawable.ic_p_success, s)
    }

    override fun redirectToSuccessScreen(imageResourceId: Int, message: String) {
        runOnUiThread {
            if (!isSuccess) {
                isSuccess = true
                DialogHelper.instance.displayDialog(
                    this@SenderActivity, message,
                    imageResourceId, false, this@SenderActivity
                )
            }
        }
    }

    private val currentFragment: Fragment?
        get() {
            val fragmentManager: FragmentManager = supportFragmentManager
            return fragmentManager.findFragmentById(R.id.fragment_container)
        }

    override fun onPositiveButtonClicked(view: View) {
        finish()
    }

    override fun onInitiateDiscovery() {
        onScanStarted()
    }

    override fun onDiscoverySuccess() {}
    override fun onDiscoveryFailure() {}
    override fun onPeerAvailable(wifiP2pDeviceList: WifiP2pDeviceList) {
        onDeviceAvailable(wifiP2pDeviceList)
    }

    override fun onPeerStatusChanged(wifiP2pDevice: WifiP2pDevice) {}
    override fun onPeerConnectionSuccess() {
        onConnectionCompleted()
        onDataTransferStarted()
    }

    override fun onPeerConnectionFailure() {}
    override fun onPeerDisconnectionSuccess() {}
    override fun onPeerDisconnectionFailure() {}
    override fun onDataTransferring() {}
    override fun onDataTransferredSuccess() {}
    override fun onDataTransferredFailure() {}
    override fun onDataReceiving() {
        onDataTransferStarted()
    }

    override fun onDataReceivedSuccess(s: String) {
        onDataTransferCompleted(s)
    }

    override fun onDataReceivedFailure() {}
}