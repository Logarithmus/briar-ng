package dev.logarithmus.p2pdroid.data.model

import androidx.annotation.ColorInt

interface ProfileManager {
    fun saveUserName(name: String)
    fun saveUserColor(@ColorInt color: Int)
    fun getUserName(): String
    @ColorInt
    fun getUserColor(): Int
    fun isInitialized(): Boolean
}