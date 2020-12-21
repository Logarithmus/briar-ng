package dev.logarithmus.p2pdroid.data.model

import android.bluetooth.BluetoothDevice
import dev.logarithmus.p2pdroid.data.entity.Conversation
import dev.logarithmus.p2pdroid.data.service.message.PayloadType
import dev.logarithmus.p2pdroid.data.service.message.TransferringFile
import dev.logarithmus.p2pdroid.data.service.message.Contract
import java.io.File

interface BluetoothConnector {

    fun prepare()
    fun release()
    fun stop()
    fun disconnect()
    fun addOnConnectListener(listener: OnConnectionListener)
    fun addOnPrepareListener(listener: OnPrepareListener)
    fun addOnMessageListener(listener: OnMessageListener)
    fun addOnFileListener(listener: OnFileListener)
    fun removeOnConnectListener(listener: OnConnectionListener)
    fun removeOnPrepareListener(listener: OnPrepareListener)
    fun removeOnMessageListener(listener: OnMessageListener)
    fun removeOnFileListener(listener: OnFileListener)
    fun connect(device: BluetoothDevice)
    fun sendMessage(messageText: String)
    fun sendFile(file: File, type: PayloadType)
    fun cancelFileTransfer()
    fun isConnected(): Boolean
    fun isConnectedOrPending(): Boolean
    fun isPending(): Boolean
    fun isConnectionPrepared(): Boolean
    fun getCurrentConversation(): Conversation?
    fun getTransferringFile(): TransferringFile?
    fun acceptConnection()
    fun rejectConnection()
    fun sendDisconnectRequest()
    fun isFeatureAvailable(feature: Contract.Feature): Boolean
}
