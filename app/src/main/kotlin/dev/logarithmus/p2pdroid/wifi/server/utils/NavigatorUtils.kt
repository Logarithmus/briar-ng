package dev.logarithmus.p2pdroid.wifi.server.utils

import android.app.Activity
import android.content.Intent
import dev.logarithmus.p2pdroid.R
import dev.logarithmus.p2pdroid.wifi.server.receiver.ReceiverActivity
import dev.logarithmus.p2pdroid.wifi.server.sender.SenderActivity

object NavigatorUtils {
    fun redirectToSenderScreen(activity: Activity) {
        val intent = Intent(activity, SenderActivity::class.java)
        activity.finish()
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun redirectToReceiverScreen(activity: Activity) {
        val intent = Intent(activity, ReceiverActivity::class.java)
        activity.finish()
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}