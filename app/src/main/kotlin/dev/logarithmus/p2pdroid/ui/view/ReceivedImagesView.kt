package dev.logarithmus.p2pdroid.ui.view

import dev.logarithmus.p2pdroid.data.entity.MessageFile

interface ReceivedImagesView {
    fun displayImages(imageMessages: List<MessageFile>)
    fun showNoImages()
}
