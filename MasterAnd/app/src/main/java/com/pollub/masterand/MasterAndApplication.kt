package com.pollub.masterand

import android.app.Application
import com.pollub.masterand.db.AppContainer
import com.pollub.masterand.db.AppDataContainer


class MasterAndApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}