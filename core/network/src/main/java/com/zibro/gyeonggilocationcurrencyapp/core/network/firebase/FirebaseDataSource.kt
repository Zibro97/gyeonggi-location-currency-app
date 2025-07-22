package com.zibro.gyeonggilocationcurrencyapp.core.network.firebase

import androidx.tracing.trace
import com.google.firebase.firestore.FirebaseFirestore
import com.zibro.gyeonggilocationcurrencyapp.core.network.NetworkDataSource
import javax.inject.Inject

internal class FirebaseDataStore @Inject constructor(
    private val firestore: FirebaseFirestore
) : NetworkDataSource {
    private val firebase = trace("FirebaseDataStore") {
        firestore
    }
}