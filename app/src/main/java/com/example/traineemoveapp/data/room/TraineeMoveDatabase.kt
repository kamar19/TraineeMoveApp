package com.example.traineemoveapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.traineemoveapp.model.FilmEntity
import com.example.traineemoveapp.model.GenreEntity

@Database(
    entities = [FilmEntity::class, GenreEntity::class], version = 40, exportSchema = false
)
abstract class TraineeMoveDatabase : RoomDatabase() {
    abstract val movieDAO: FilmDAO

    companion object {
        var instance: TraineeMoveDatabase? = null
        fun createInstance(applicationContext: Context): TraineeMoveDatabase {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(
                        applicationContext,
                        TraineeMoveDatabase::class.java,
                        DBContract.DATABASE_NAME
                    )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as TraineeMoveDatabase
        }
    }
}
