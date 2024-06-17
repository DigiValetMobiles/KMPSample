package com.jetbrains.kmpapp.utils

import kotlinx.coroutines.CoroutineDispatcher


internal interface AppDispatcher {
    val dispatcher : CoroutineDispatcher
}
//internal expect fun provideDispatcher() : AppDispatcher