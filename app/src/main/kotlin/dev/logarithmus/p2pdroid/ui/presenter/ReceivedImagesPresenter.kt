package dev.logarithmus.p2pdroid.ui.presenter

import dev.logarithmus.p2pdroid.data.model.MessagesStorage
import dev.logarithmus.p2pdroid.ui.view.ReceivedImagesView
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReceivedImagesPresenter(private val address: String,
                              private val view: ReceivedImagesView,
                              private val model: MessagesStorage,
                              private val uiContext: CoroutineDispatcher = Dispatchers.Main,
                              private val bgContext: CoroutineDispatcher = Dispatchers.IO) : BasePresenter(uiContext) {

    fun loadImages() = launch {
        val messages = withContext(bgContext) { model.getFileMessagesByDevice(address) }
        if (messages.isNotEmpty()) {
            view.displayImages(messages)
        } else {
            view.showNoImages()
        }
    }
}
