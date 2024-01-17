package com.pollub.masterand

import android.app.Application
//import com.pollub.masterand.containers.AppContainer
//import com.pollub.masterand.containers.AppDataContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MasterAndApplication : Application() {
//    lateinit var container: AppContainer
//    override fun onCreate() {
//        super.onCreate()
//        container = AppDataContainer(this)
//    }
}