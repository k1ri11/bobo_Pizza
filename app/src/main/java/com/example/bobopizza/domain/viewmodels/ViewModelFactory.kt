package com.example.bobopizza.domain.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bobopizza.domain.repository.Repository

class ViewModelFactory (
    private val app: Application,
    private val repository: Repository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(app, repository) as T
    }
}