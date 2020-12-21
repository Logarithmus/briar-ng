package dev.logarithmus.p2pdroid.ui.presenter

import dev.logarithmus.p2pdroid.data.model.MessagesStorage
import dev.logarithmus.p2pdroid.ui.view.ImagePreviewView
import dev.logarithmus.p2pdroid.utils.toReadableFileSize
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class ImagePreviewPresenter(private val messageId: Long,
                            private val image: File,
                            private val view: ImagePreviewView,
                            private val storage: MessagesStorage,
                            private val uiContext: CoroutineDispatcher = Dispatchers.Main,
                            private val bgContext: CoroutineDispatcher = Dispatchers.IO): BasePresenter(uiContext) {

    fun loadImage() {
        view.showFileInfo(image.name, image.length().toReadableFileSize())
        view.displayImage("file://${image.absolutePath}")
    }

    fun removeFile() {

        launch(bgContext) {
            image.delete()
            storage.removeFileInfo(messageId)
        }

        view.close()
    }
}
