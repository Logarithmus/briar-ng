package dev.logarithmus.p2pdroid.wifi.server.dialogs

import android.app.Activity

class LoaderHelper {
    private var loaderDialog: LoaderDialog? = null
    fun displayLoader(activity: Activity, drawableId: Int) {
        loaderDialog = LoaderDialog(activity)
        loaderDialog!!.setDrawable(drawableId)
        if (!activity.isFinishing) loaderDialog!!.show()
    }

    fun dismissDialog() {
        if (loaderDialog != null) {
            loaderDialog!!.dismiss()
        }
    }

    companion object {
        var instance: LoaderHelper? = null
            get() {
                if (field == null) field = LoaderHelper()
                return field
            }
            private set
    }
}