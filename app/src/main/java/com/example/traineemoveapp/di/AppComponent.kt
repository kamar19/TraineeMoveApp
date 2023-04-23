package com.example.traineemoveapp.di

import android.content.Context
import com.example.traineemoveapp.MainActivity
import com.example.traineemoveapp.di.module.*
import dagger.BindsInstance
import dagger.Component
@Component (modules = [ViewModelModule::class,
    DetailsViewModelModule::class,
    RepositoryModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance
                   context: Context
        ): AppComponent
    }
    fun inject(activity: MainActivity)
}