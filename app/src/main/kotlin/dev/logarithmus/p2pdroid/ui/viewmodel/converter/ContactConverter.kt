package dev.logarithmus.p2pdroid.ui.viewmodel.converter

import com.amulyakhare.textdrawable.TextDrawable
import dev.logarithmus.p2pdroid.data.entity.Conversation
import dev.logarithmus.p2pdroid.ui.viewmodel.ContactViewModel
import dev.logarithmus.p2pdroid.utils.getFirstLetter

class ContactConverter {

    fun transform(conversation: Conversation): ContactViewModel {
        return ContactViewModel(
                conversation.deviceAddress,
                "${conversation.displayName} (${conversation.deviceName})",
                TextDrawable.builder()
                        .buildRound(conversation.displayName.getFirstLetter(), conversation.color)
        )
    }

    fun transform(conversationCollection: Collection<Conversation>): List<ContactViewModel> {
        return conversationCollection.map {
            transform(it)
        }
    }
}
