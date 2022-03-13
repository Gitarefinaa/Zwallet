package com.githarefina.zwallet.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun<reified T: ViewModel> Fragment.viewModelFactory(crossinline viewModelInitialization:()-> T):Lazy<T>{
    return viewModels {
        object :ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModelInitialization.invoke() as T
            }
        }
    }
}