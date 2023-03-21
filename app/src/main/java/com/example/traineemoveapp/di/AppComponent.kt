package com.example.traineemoveapp.di

import android.content.Context
import com.example.traineemoveapp.MainActivity
import dagger.BindsInstance
import dagger.Component

// Definition of a Dagger component
@Component(modules = [DatabaseModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance
                   context: Context
        ): AppComponent
    }

    fun inject(activity: MainActivity)

}