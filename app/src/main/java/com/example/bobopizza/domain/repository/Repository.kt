package com.example.bobopizza.domain.repository

import com.example.bobopizza.domain.api.RetrofitInstance
import com.example.bobopizza.domain.models.MenuModel

class Repository {
    suspend fun getMenuModel(): MenuModel {
        return RetrofitInstance.api.getMenuModel()
    }
}