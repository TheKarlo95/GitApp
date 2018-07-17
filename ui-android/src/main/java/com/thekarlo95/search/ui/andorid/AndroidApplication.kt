package com.thekarlo95.search.ui.andorid

import android.app.Activity
import android.app.Application
import com.thekarlo95.search.ui.andorid.di.ApplicationComponent
import com.thekarlo95.search.ui.andorid.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class AndroidApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    private val appComponent by lazy {
        DaggerApplicationComponent.builder().create(this) as ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}