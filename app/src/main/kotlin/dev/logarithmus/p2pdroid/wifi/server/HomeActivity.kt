package dev.logarithmus.p2pdroid.wifi.server

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import dev.logarithmus.p2pdroid.R
import dev.logarithmus.p2pdroid.wifi.server.utils.NavigatorUtils

class HomeActivity: Activity(), View.OnClickListener {
    private var btnPay: Button? = null
    private var btnReceive: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btnPay = findViewById(R.id.btn_pay)
        btnPay?.setOnClickListener(this)
        btnReceive = findViewById(R.id.btn_receive)
        btnReceive?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v === btnPay) {
            NavigatorUtils.redirectToSenderScreen(this)
        } else if (v === btnReceive) {
            NavigatorUtils.redirectToReceiverScreen(this)
        }
    }
}