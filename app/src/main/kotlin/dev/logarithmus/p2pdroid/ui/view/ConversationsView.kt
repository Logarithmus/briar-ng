package dev.logarithmus.p2pdroid.ui.view

import androidx.annotation.ColorInt
import dev.logarithmus.p2pdroid.ui.viewmodel.ConversationViewModel

interface ConversationsView {
    fun redirectToChat(conversation: ConversationViewModel)
    fun notifyAboutConnectedDevice(conversation: ConversationViewModel)
    fun showServiceDestroyed()
    fun showNoConversations()
    fun showRejectedNotification(conversation: ConversationViewModel)
    fun hideActions()
    fun refreshList(connected: String?)
    fun showConversations(conversations: List<ConversationViewModel>, connected: String?)
    fun showUserProfile(name: String, @ColorInt color: Int)
    fun dismissConversationNotification()
    fun removeFromShortcuts(address: String)
}
