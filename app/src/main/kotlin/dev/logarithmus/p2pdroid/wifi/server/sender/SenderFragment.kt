package dev.logarithmus.p2pdroid.wifi.server.sender

import android.net.wifi.p2p.WifiP2pDevice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import dev.logarithmus.p2pdroid.R
import dev.logarithmus.p2pdroid.wifi.server.base.BaseFragment
import dev.logarithmus.p2pdroid.wifi.server.dialogs.AnimationView

class SenderFragment : BaseFragment() {
    private var connectView: View? = null
    private var hotspotView: View? = null
    private var dataView: View? = null
    private var connectGif: AnimationView? = null
    private var hotspotGif: AnimationView? = null
    private var dataGif: AnimationView? = null
    private var connectTxtView: TextView? = null
    private var hotspotTxtView: TextView? = null
    private var dataTxtView: TextView? = null
    private var wifiP2pDevice: WifiP2pDevice? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            wifiP2pDevice = it.getParcelable(INTENT_CONFIG)
        }
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return if (enter) AnimationUtils.loadAnimation(activity, R.anim.slide_in_right) else super.onCreateAnimation(
            transit,
            enter,
            nextAnim
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_sender, container, false)
        connectView = rootView.findViewById(R.id.scan_progress)
        connectGif = rootView.findViewById(R.id.gif1)
        connectTxtView = rootView.findViewById(R.id.progress_txt)
        hotspotView = rootView.findViewById(R.id.scan_hotspot)
        hotspotGif = rootView.findViewById(R.id.gif2)
        hotspotTxtView = rootView.findViewById(R.id.progress_hotspot_txt)
        dataView = rootView.findViewById(R.id.scan_data)
        dataGif = rootView.findViewById(R.id.gif3)
        dataTxtView = rootView.findViewById(R.id.progress_data_txt)
        (activity as SenderActivity).wifiP2PService?.connectDevice(wifiP2pDevice)
        onConnectStarted()
        return rootView
    }

    fun onConnectStarted() {
        activity.runOnUiThread {
            connectView?.visibility = View.VISIBLE
            connectGif?.setMovieResource(R.drawable.loader_2)
        }
    }

    fun onConnectCompleted() {
        activity.runOnUiThread {
            connectGif?.visibility = View.INVISIBLE
            connectTxtView?.text = activity.getString(R.string.sender_connected)
        }
    }

    fun onHotspotStarted() {
        activity.runOnUiThread {
            hotspotView?.visibility = View.VISIBLE
            hotspotGif?.setMovieResource(R.drawable.loader_2)
        }
    }

    fun onHotspotCompleted() {
        activity.runOnUiThread {
            hotspotGif?.visibility = View.INVISIBLE
            hotspotTxtView?.text = activity.getString(R.string.sender_hotspot_success)
        }
    }

    fun onDataStarted() {
        activity.runOnUiThread {
            dataView?.visibility = View.VISIBLE
            dataGif?.setMovieResource(R.drawable.loader_2)
        }
    }

    companion object {
        private const val INTENT_CONFIG = "wifi_config"
        @JvmStatic
        fun newInstance(wifiP2pDevice: WifiP2pDevice?): SenderFragment {
            val fragment = SenderFragment()
            val args = Bundle()
            args.putParcelable(INTENT_CONFIG, wifiP2pDevice)
            fragment.arguments = args
            return fragment
        }
    }
}