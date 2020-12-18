package dev.logarithmus.p2pdroid.wifi.server

import android.content.Context
import android.graphics.Color
import android.net.wifi.p2p.WifiP2pDevice
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.logarithmus.p2pdroid.R
import dev.logarithmus.p2pdroid.wifi.server.WifiListAdapter.CustomViewHolder

class WifiListAdapter(private val context: Context, private val wifiP2pDevices: MutableList<WifiP2pDevice>) :
    RecyclerView.Adapter<CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wifi_list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val device = getItem(position)
        holder.itemTitle.text = device.deviceName
        if (device.status == WifiP2pDevice.CONNECTED) {
            holder.itemDesc.text = context.getString(R.string.connected)
            holder.itemDesc.setTextColor(Color.GREEN)
        } else {
            holder.itemDesc.text = device.deviceAddress
            holder.itemDesc.setTextColor(ContextCompat.getColor(context, R.color.fragment_add_bank_desc))
        }
    }

    override fun getItemCount(): Int {
        return wifiP2pDevices.size
    }

    fun getItem(position: Int): WifiP2pDevice {
        return wifiP2pDevices[position]
    }

    fun addDevices(devices: List<WifiP2pDevice>?) {
        wifiP2pDevices.clear()
        wifiP2pDevices.addAll(devices!!)
        notifyDataSetChanged()
    }

    fun addDevice(device: WifiP2pDevice) {
        wifiP2pDevices.add(device)
        notifyDataSetChanged()
    }

    inner class CustomViewHolder(itemView: View) : ViewHolder(itemView) {
        val itemTitle: TextView
        val itemDesc: TextView

        init {
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDesc = itemView.findViewById(R.id.item_desc)
        }
    }
}