package com.pollub.masterand.providers

//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewmodel.CreationExtras
//import androidx.lifecycle.viewmodel.initializer
//import androidx.lifecycle.viewmodel.viewModelFactory
//import com.pollub.masterand.MasterAndApplication
//import com.pollub.masterand.view_models.GameViewModel
//import com.pollub.masterand.view_models.ProfileViewModel
//import com.pollub.masterand.view_models.ResultsViewModel
//
//object AppViewModelProvider {
//    val Factory = viewModelFactory {
//        initializer {
//            ProfileViewModel(masterAndApplication().container.playersRepository)
//        }
//        initializer {
//            GameViewModel(
//                masterAndApplication().container.playersRepository,
//                masterAndApplication().container.scoresRepository
//            )
//        }
//        initializer {
//            ResultsViewModel(masterAndApplication().container.playerScoresRepository)
//        }
//    }
//}
//
//fun CreationExtras.masterAndApplication(): MasterAndApplication =
//    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MasterAndApplication)