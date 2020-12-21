package dev.logarithmus.p2pdroid.data.model

import dev.logarithmus.p2pdroid.data.entity.Conversation
import dev.logarithmus.p2pdroid.data.entity.ConversationWithMessages

interface ConversationsStorage {
    suspend fun getContacts(): List<Conversation>
    suspend fun getConversations(): List<ConversationWithMessages>
    suspend fun getConversationByAddress(address: String): Conversation?
    suspend fun insertConversation(conversation: Conversation)
    suspend fun removeConversationByAddress(address: String)
}
