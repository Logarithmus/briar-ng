package dev.logarithmus.p2pdroid.wifi.server.sender

import android.net.wifi.p2p.WifiP2pDeviceList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dev.logarithmus.p2pdroid.R
import dev.logarithmus.p2pdroid.wifi.server.RecyclerItemClickListener
import dev.logarithmus.p2pdroid.wifi.server.WifiListAdapter
import dev.logarithmus.p2pdroid.wifi.server.base.BaseFragment
import dev.logarithmus.p2pdroid.wifi.server.dialogs.AnimationView
import dev.logarithmus.p2pdroid.wifi.server.sender.DeviceListFragment
import java.util.*

class DeviceListFragment : BaseFragment(), RecyclerItemClickListener.OnItemClickListener {
    private var progressView: View? = null
    private var emptyView: TextView? = null
    private var animationView: AnimationView? = null
    private var recyclerView: RecyclerView? = null
    private var wifiListAdapter: WifiListAdapter? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return if (enter) {
            AnimationUtils.loadAnimation(activity, R.anim.slide_in_right)
        } else {
            super.onCreateAnimation(transit, enter, nextAnim)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_device_list, container, false)
        progressView = rootView.findViewById(R.id.scan_progress)
        animationView = rootView.findViewById(R.id.gif1)
        recyclerView = rootView.findViewById(R.id.scanned_list)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        wifiListAdapter = WifiListAdapter(activity, ArrayList())
        recyclerView?.adapter = wifiListAdapter
        emptyView = rootView.findViewById(R.id.empty_view)
        swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout)
        (activity as SenderActivity).wifiP2PService!!.onCreate()
        (activity as SenderActivity).wifiP2PService!!.onResume()
        return rootView
    }

    fun onScanStarted() {
        activity.runOnUiThread {
            progressView!!.visibility = View.VISIBLE
            animationView!!.setMovieResource(R.drawable.loader_2)
        }
    }

    fun onScanCompleted() {
        activity.runOnUiThread { progressView!!.visibility = View.GONE }
    }

    fun onDeviceAvailable(wifiP2pDeviceList: WifiP2pDeviceList) {
        activity.runOnUiThread {
            if (recyclerView!!.isEnabled) {
                progressView!!.visibility = View.GONE
                emptyView!!.visibility = View.GONE
                recyclerView!!.visibility = View.VISIBLE
                swipeRefreshLayout!!.visibility = View.VISIBLE
                wifiListAdapter!!.addDevices(ArrayList(wifiP2pDeviceList.deviceList))
                recyclerView!!.addOnItemTouchListener(RecyclerItemClickListener(activity, this@DeviceListFragment))
            }
        }
    }

    fun onNoDeviceAvailable() {
        activity.runOnUiThread {
            if (wifiListAdapter!!.itemCount == 0) {
                emptyView?.visibility = View.VISIBLE
                recyclerView?.visibility = View.GONE
                swipeRefreshLayout?.visibility = View.GONE
            } else {
                swipeRefreshLayout?.visibility = View.VISIBLE
                recyclerView?.visibility = View.VISIBLE
                emptyView?.visibility = View.GONE
            }
        }
    }

    override fun onItemClick(view: View?, position: Int) {
        (activity as SenderActivity).redirectToProcessScreen(wifiListAdapter?.getItem(position)!!)
        recyclerView?.isEnabled = false
    }

    companion object {
        fun newInstance(): DeviceListFragment {
            return DeviceListFragment()
        }
    }
}