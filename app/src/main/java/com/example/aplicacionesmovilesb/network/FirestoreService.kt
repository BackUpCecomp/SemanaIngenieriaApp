package com.example.aplicacionesmovilesb.network

import com.example.aplicacionesmovilesb.Model.Expositor
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

const val EXPOSITORES_COLLECTION_NAME = "Expositores"

class FirestoreService {
    val firebaseFirestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        firebaseFirestore.firestoreSettings = settings
    }

    fun getExpositor(callback: Callback<List<Expositor>>){
        firebaseFirestore.collection(EXPOSITORES_COLLECTION_NAME)
            .orderBy("categoria")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result){
                    val list = result.toObjects(Expositor::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
            .addOnFailureListener { exception ->
                callback.onFailed(exception)
            }

    }

}