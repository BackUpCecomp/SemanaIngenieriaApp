package com.example.aplicacionesmovilesb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplicacionesmovilesb.Model.Expositor
import com.example.aplicacionesmovilesb.network.Callback
import com.example.aplicacionesmovilesb.network.FirestoreService
import java.lang.Exception

class ExpositoresViewModel : ViewModel() {
    val firestoreService = FirestoreService()
    val listExpositores = MutableLiveData<List<Expositor>?>()
    val isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getExpositoresFromFirebase()
    }

    fun getExpositoresFromFirebase(){
        firestoreService.getExpositor(callback = object : Callback<List<Expositor>> {
            override fun onSuccess(result: List<Expositor>?){
                listExpositores.postValue( result )
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }

            fun processFinished(){
                isLoading.value = true
            }
        })
    }


}