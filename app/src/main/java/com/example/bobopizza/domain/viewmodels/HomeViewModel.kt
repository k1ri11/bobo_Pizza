package com.example.bobopizza.domain.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bobopizza.domain.PizzaApp
import com.example.bobopizza.domain.models.MenuModel
import com.example.bobopizza.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    app: Application,
    private val repository: Repository
) : AndroidViewModel(app) {
    private val _menuModel: MutableLiveData<MenuModel> = MutableLiveData()
    val menuModel: LiveData<MenuModel> = _menuModel

    init {
        getMenuModel()
    }

    fun getMenuModel() {
        viewModelScope.launch(Dispatchers.IO) {
            if (hasInternetConnection()) {
                val response = repository.getMenuModel()
                withContext(Dispatchers.Main) {
                    _menuModel.value = response
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        getApplication<PizzaApp>(),
                        "Нет доступа в интренет",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<PizzaApp>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}