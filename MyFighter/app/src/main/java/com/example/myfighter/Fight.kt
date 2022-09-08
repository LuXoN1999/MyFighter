package com.example.myfighter

import android.app.Application
import com.example.myfighter.di.databaseModule
import com.example.myfighter.di.repositoryModule
import com.example.myfighter.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Fight: Application() {
    override fun onCreate() {
        super.onCreate()
        application = this

        startKoin{
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@Fight)
            modules(
                databaseModule,
                repositoryModule,
                viewmodelModule
            )
        }
    }

    companion object{
        lateinit var application: Application
    }
}