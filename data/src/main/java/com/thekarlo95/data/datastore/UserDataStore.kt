package com.thekarlo95.data.datastore

import com.thekarlo95.data.model.UserDataModel
import io.reactivex.Flowable

interface UserDataStore {
    fun fetchUserDetails(username: String): Flowable<UserDataModel>
}