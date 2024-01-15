package com.pollub.masterand.view_models

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pollub.masterand.MasterAndApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ProfileViewModel(masterAndApplication().container.playersRepository)
        }
        initializer {
            GameViewModel(
                masterAndApplication().container.playersRepository,
                masterAndApplication().container.scoresRepository
            )
        }
    }
}

fun CreationExtras.masterAndApplication(): MasterAndApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MasterAndApplication)