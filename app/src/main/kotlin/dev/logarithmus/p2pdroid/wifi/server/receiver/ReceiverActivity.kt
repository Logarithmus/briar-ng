package dev.logarithmus.p2pdroid.wifi.server.receiver

import android.net.wifi.p2p.WifiP2pDevice
import android.net.wifi.p2p.WifiP2pDeviceList
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.view.View
import com.wifiscanner.listener.WifiP2PConnectionCallback
import com.wifiscanner.service.WifiP2PService
import com.wifiscanner.service.WifiP2PServiceImpl
import dev.logarithmus.p2pdroid.R
import dev.logarithmus.p2pdroid.wifi.server.base.BaseActivity
import dev.logarithmus.p2pdroid.wifi.server.dialogs.DialogEventListener
import dev.logarithmus.p2pdroid.wifi.server.dialogs.DialogHelper
import java.lang.Boolean

class ReceiverActivity : BaseActivity(), WifiP2PConnectionCallback, DialogEventListener {
    private var isSuccess = Boolean.FALSE
    var wifiP2PService: WifiP2PService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        initToolBar()
        updateTitle(getString(R.string.title_sender))
        wifiP2PService = WifiP2PServiceImpl.Builder()
            .setReceiver(this)
            .setWifiP2PConnectionCallback(this)
            .build()
        wifiP2PService?.onCreate()
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            ReceiverFragment.newInstance()
        ).commitAllowingStateLoss()
    }

    override fun onResume() {
        super.onResume()
        wifiP2PService?.onResume()
    }

    override fun onStop() {
        super.onStop()
        wifiP2PService?.onStop()
    }

    protected override fun onDestroy() {
        super.onDestroy()
        wifiP2PService?.onDestroy()
    }

    private val currentFragment: Fragment?
        get() {
            val fragmentManager: FragmentManager = supportFragmentManager
            return fragmentManager.findFragmentById(R.id.fragment_container)
        }

    override fun onPositiveButtonClicked(view: View) {
        finish()
    }

    override fun onInitiateDiscovery() {}
    override fun onDiscoverySuccess() {}
    override fun onDiscoveryFailure() {}
    override fun onPeerAvailable(wifiP2pDeviceList: WifiP2pDeviceList) {}
    override fun onPeerStatusChanged(wifiP2pDevice: WifiP2pDevice) {}
    override fun onPeerConnectionSuccess() {
        val fragment: Fragment = currentFragment!!
        if (fragment is ReceiverFragment) {
            wifiP2PService?.startDataTransfer(fragment.message)
            fragment.onServerConnectSuccess()
        }
    }

    override fun onPeerConnectionFailure() {
        val fragment: Fragment = currentFragment!!
        if (fragment is ReceiverFragment) fragment.onServerConnectFailed()
    }

    override fun onPeerDisconnectionSuccess() {}
    override fun onPeerDisconnectionFailure() {}
    override fun onDataTransferring() {
        val fragment: Fragment = currentFragment!!
        if (fragment is ReceiverFragment) fragment.onDataTransferStarted()
    }

    override fun onDataTransferredSuccess() {
        runOnUiThread(Runnable {
            if (!isSuccess) {
                isSuccess = Boolean.TRUE
                val message: String = getString(R.string.receiver_success)
                DialogHelper.instance.displayDialog(
                    this@ReceiverActivity,
                    message,
                    R.drawable.ic_p_success,
                    false,
                    this@ReceiverActivity
                )
            }
        })
    }

    override fun onDataTransferredFailure() {}
    override fun onDataReceiving() {}
    override fun onDataReceivedSuccess(s: String) {}
    override fun onDataReceivedFailure() {}
}