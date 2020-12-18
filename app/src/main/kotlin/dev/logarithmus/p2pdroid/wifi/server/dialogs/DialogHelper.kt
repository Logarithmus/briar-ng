package dev.logarithmus.p2pdroid.wifi.server.dialogs

import android.app.Activity

class DialogHelper {
    private var customDialog: CustomDialog? = null
    fun displayDialog(
        activity: Activity,
        message: String?,
        cancellable: Boolean
    ) {
        customDialog = CustomDialog(activity)
        customDialog!!.setText(message, true)
        customDialog!!.setCancelable(cancellable)
        if (!activity.isFinishing) customDialog!!.show()
    }

    fun displayDialog(
        activity: Activity,
        message: String?,
        imageResourceId: Int,
        cancellable: Boolean,
        eventListener: DialogEventListener?
    ) {
        customDialog = CustomDialog(activity, eventListener)
        customDialog!!.setText(message, true)
        customDialog!!.setImg(imageResourceId, true)
        customDialog!!.setCancelable(cancellable)
        if (!activity.isFinishing) customDialog!!.show()
    }

    fun dismissDialog() {
        if (customDialog != null) {
            customDialog!!.dismiss()
        }
    }

    companion object {
        var instance: DialogHelper? = null
            get() {
                if (field == null) field = DialogHelper()
                return field
            }
            private set
    }
}