package dev.logarithmus.p2pdroid.data.model

import android.bluetooth.BluetoothDevice
import dev.logarithmus.p2pdroid.data.entity.Conversation

abstract class SimpleConnectionListener : OnConnectionListener {

    override fun onConnecting() {}

    abstract override fun onConnected(device: BluetoothDevice)

    override fun onConnectedIn(conversation: Conversation) {}

    override fun onConnectedOut(conversation: Conversation) {}

    abstract override fun onConnectionLost()

    abstract override fun onConnectionFailed()

    override fun onConnectionDestroyed() {}

    override fun onDisconnected() {}

    override fun onConnectionAccepted() {}

    override fun onConnectionRejected() {}

    override fun onConnectionWithdrawn() {}
}
