package com.example.myfighter.di

import android.app.Application
import com.example.myfighter.database.AppDatabase
import com.example.myfighter.database.FighterDAO
import com.example.myfighter.database.FighterRepository
import com.example.myfighter.database.FighterRepositoryImpl
import com.example.myfighter.ui.fighter_details.FighterDetailsViewModel
import com.example.myfighter.ui.fighter_list.FighterListViewModel
import com.example.myfighter.ui.new_fighter.NewFighterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(application: Application): AppDatabase {
        return AppDatabase.getDatabase(application)
    }
    fun provideFighterDao(database: AppDatabase): FighterDAO {
        return database.getFighterDAO()
    }
    single<AppDatabase> { provideDatabase(get()) }
    single<FighterDAO> { provideFighterDao(get()) }
}

val repositoryModule = module {
    single<FighterRepository> { FighterRepositoryImpl(get()) }
}

val viewmodelModule = module {
    viewModel { FighterListViewModel(get())  }
    viewModel { FighterDetailsViewModel(get())  }
    viewModel { NewFighterViewModel(get())  }
}