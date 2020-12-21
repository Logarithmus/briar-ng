package dev.logarithmus.p2pdroid.data.model

import android.bluetooth.BluetoothDevice
import dev.logarithmus.p2pdroid.data.entity.Conversation

interface OnConnectionListener {
    fun onConnecting()
    fun onConnected(device: BluetoothDevice)
    fun onConnectedIn(conversation: Conversation)
    fun onConnectedOut(conversation: Conversation)
    fun onConnectionLost()
    fun onConnectionFailed()
    fun onConnectionDestroyed()
    fun onDisconnected()
    fun onConnectionAccepted()
    fun onConnectionRejected()
    fun onConnectionWithdrawn()
}
