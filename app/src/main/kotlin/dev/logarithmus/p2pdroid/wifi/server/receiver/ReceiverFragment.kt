package dev.logarithmus.p2pdroid.wifi.server.receiver

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import dev.logarithmus.p2pdroid.R
import dev.logarithmus.p2pdroid.wifi.server.base.BaseFragment
import dev.logarithmus.p2pdroid.wifi.server.dialogs.AnimationView
import dev.logarithmus.p2pdroid.wifi.server.utils.Utils.showMessage

class ReceiverFragment : BaseFragment(), View.OnClickListener, TextWatcher {
    private var btnSend: Button? = null
    private var senderTxt: EditText? = null
    private var connectView: View? = null
    private var hotspotView: View? = null
    private var connectGif: AnimationView? = null
    private var hotspotGif: AnimationView? = null
    private var connectTxtView: TextView? = null
    private var hotspotTxtView: TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_receiver, container, false)
        connectView = rootView.findViewById(R.id.scan_progress)
        connectGif = rootView.findViewById(R.id.gif1)
        connectTxtView = rootView.findViewById(R.id.progress_txt)
        hotspotView = rootView.findViewById(R.id.scan_hotspot)
        hotspotGif = rootView.findViewById(R.id.gif2)
        hotspotTxtView = rootView.findViewById(R.id.progress_hotspot_txt)
        btnSend = rootView.findViewById(R.id.btn_send)
        btnSend?.isEnabled = false
        btnSend?.setOnClickListener(this)
        senderTxt = rootView.findViewById(R.id.sender_txt)
        senderTxt?.addTextChangedListener(this)
        return rootView
    }

    fun onServerConnectStarted() {
        activity.runOnUiThread {
            connectView!!.visibility = View.VISIBLE
            connectGif!!.setMovieResource(R.drawable.loader_2)
        }
    }

    fun onServerConnectSuccess() {
        activity.runOnUiThread {
            connectGif!!.visibility = View.INVISIBLE
            connectTxtView!!.text = activity.getString(R.string.sender_connected)
            onDataTransferStarted()
        }
    }

    fun onServerConnectFailed() {
        activity.runOnUiThread {
            showMessage(activity, activity.getString(R.string.receiver_error_in_connecting))
            connectView!!.visibility = View.VISIBLE
            connectTxtView!!.text = getString(R.string.receiver_init)
        }
    }

    fun onDataTransferStarted() {
        activity.runOnUiThread {
            hotspotView!!.visibility = View.VISIBLE
            hotspotGif!!.setMovieResource(R.drawable.loader_2)
            hotspotTxtView!!.text = activity.getString(R.string.receiver_confirmation)
        }
    }

    val message: String
        get() = senderTxt!!.text.toString()

    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
        if (senderTxt!!.text.toString().isNotEmpty()) {
            btnSend?.isEnabled = true
            btnSend?.setBackgroundColor(ContextCompat.getColor(activity, R.color.btn_green_bg))
        } else btnSend!!.isEnabled = false
    }

    override fun afterTextChanged(editable: Editable) {}
    override fun onClick(view: View) {
        if (view === btnSend) {
            senderTxt!!.isEnabled = false
            btnSend!!.isEnabled = false
            onServerConnectStarted()
        }
    }

    companion object {
        fun newInstance(): ReceiverFragment {
            return ReceiverFragment()
        }
    }
}