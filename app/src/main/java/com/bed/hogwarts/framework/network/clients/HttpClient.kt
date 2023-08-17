package com.bed.hogwarts.framework.network.clients

import javax.inject.Inject

import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.FirebaseFirestore

interface HttpClient {
    val firestore: FirebaseFirestore
}

class HttpClientImpl @Inject constructor() : HttpClient {
    override val firestore: FirebaseFirestore get() = Firebase.firestore
}
