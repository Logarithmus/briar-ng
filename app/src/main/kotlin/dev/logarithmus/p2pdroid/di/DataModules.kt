package dev.logarithmus.p2pdroid.di

import dev.logarithmus.p2pdroid.data.database.Database
import dev.logarithmus.p2pdroid.data.model.*
import dev.logarithmus.p2pdroid.ui.view.NotificationView
import dev.logarithmus.p2pdroid.ui.view.NotificationViewImpl
import dev.logarithmus.p2pdroid.ui.viewmodel.converter.ChatMessageConverter
import dev.logarithmus.p2pdroid.ui.viewmodel.converter.ContactConverter
import dev.logarithmus.p2pdroid.ui.viewmodel.converter.ConversationConverter
import dev.logarithmus.p2pdroid.ui.widget.ShortcutManager
import dev.logarithmus.p2pdroid.ui.widget.ShortcutManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val bluetoothConnectionModule = module {
    single { BluetoothConnectorImpl(androidContext()) as BluetoothConnector }
    factory { BluetoothScannerImpl(androidContext()) as BluetoothScanner }
}

val databaseModule = module {
    single { Database.getInstance(androidContext()) }
    single { MessagesStorageImpl(get()) as MessagesStorage }
    single { ConversationsStorageImpl(get()) as ConversationsStorage }
}

val localStorageModule = module {
    single { FileManagerImpl(androidContext()) as FileManager }
    single { UserPreferencesImpl(androidContext()) as UserPreferences }
    single { ProfileManagerImpl(androidContext()) as ProfileManager }
}

const val localeScope = "locale_scope"

val viewModule = module {
    single { NotificationViewImpl(androidContext()) as NotificationView }
    single { ShortcutManagerImpl(androidContext()) as ShortcutManager }
    scope(localeScope) { ContactConverter() }
    scope(localeScope) { ConversationConverter(androidContext()) }
    scope(localeScope) { ChatMessageConverter(androidContext()) }
}
