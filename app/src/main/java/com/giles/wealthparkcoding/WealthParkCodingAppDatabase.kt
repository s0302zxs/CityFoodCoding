package com.giles.wealthparkcoding

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food
import com.giles.wealthparkcoding.model.CityFoodDao


@Database(entities = [City::class, Food::class] , version = 1)
//@TypeConverters(EnumConverters::class)
abstract class WealthParkCodingAppDatabase : RoomDatabase() {
    abstract fun cityFoodDao(): CityFoodDao

    companion object {
        @Volatile
        private var INSTANCE: WealthParkCodingAppDatabase? = null

        fun getDatabase(context: Context): WealthParkCodingAppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    WealthParkCodingAppDatabase::class.java,
                    "wealthpark_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}