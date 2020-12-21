package dev.logarithmus.p2pdroid

import android.app.Activity
import android.app.Application
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.NightMode
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import dev.logarithmus.p2pdroid.data.model.BluetoothConnector
import dev.logarithmus.p2pdroid.data.model.ProfileManager
import dev.logarithmus.p2pdroid.data.model.UserPreferences
import dev.logarithmus.p2pdroid.di.*
import dev.logarithmus.p2pdroid.ui.activity.ChatActivity
import dev.logarithmus.p2pdroid.ui.activity.ConversationsActivity
import dev.logarithmus.p2pdroid.ui.util.StartStopActivityLifecycleCallbacks
import dev.logarithmus.p2pdroid.ui.util.ThemeHolder
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import org.koin.core.scope.Scope

class ChatApplication: Application(), LifecycleObserver, ThemeHolder {

    var isConversationsOpened = false
    var currentChat: String? = null

    @NightMode
    private var nightMode: Int = AppCompatDelegate.MODE_NIGHT_NO

    private val connector: BluetoothConnector by inject()
    private val profileManager: ProfileManager by inject()
    private val preferences: UserPreferences by inject()

    private lateinit var localeSession: Scope

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(applicationModule,
                bluetoothConnectionModule, databaseModule, localStorageModule, viewModule))
        localeSession = getKoin().createScope(localeScope)

        nightMode = preferences.getNightMode()

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        registerActivityLifecycleCallbacks(object: StartStopActivityLifecycleCallbacks() {
            override fun onActivityStarted(activity: Activity) {
                when (activity) {
                    is ConversationsActivity -> isConversationsOpened = true
                    is ChatActivity -> currentChat =
                            activity.intent.getStringExtra(ChatActivity.EXTRA_ADDRESS)
                }
            }

            override fun onActivityStopped(activity: Activity) {
                when (activity) {
                    is ConversationsActivity -> isConversationsOpened = false
                    is ChatActivity -> currentChat = null
                }
            }
        })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeSession.close()
        localeSession = getKoin().createScope(localeScope)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    internal fun prepareConnection() {
        if (!profileManager.getUserName().isEmpty()) {
            connector.prepare()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    internal fun releaseConnection() {
        connector.release()
    }

    override fun setNightMode(@NightMode nightMode: Int) {
        this.nightMode = nightMode
    }

    override fun getNightMode() = nightMode
}
