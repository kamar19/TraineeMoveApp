package com.example.traineemoveapp.di

import android.content.Context
import com.example.traineemoveapp.MainActivity
import com.example.traineemoveapp.di.module.DatabaseModule
import com.example.traineemoveapp.di.module.DetailsViewModelModule
import com.example.traineemoveapp.di.module.NetworkModule
import com.example.traineemoveapp.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
@Component (modules = [ViewModelModule::class,
    DetailsViewModelModule::class,
    DatabaseModule::class, NetworkModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance
                   context: Context
        ): AppComponent
    }
    fun inject(activity: MainActivity)
}