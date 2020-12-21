package dev.logarithmus.p2pdroid.data.model

import android.net.Uri

interface FileManager {
    suspend fun extractApkFile(): Uri?
}
