package dev.logarithmus.p2pdroid.wifi.server.utils

import android.content.Context
import android.widget.Toast

object Utils {
    @JvmStatic
    fun showMessage(context: Context?, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}