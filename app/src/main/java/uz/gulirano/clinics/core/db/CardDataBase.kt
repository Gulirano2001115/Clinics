package uz.gulirano.clinics.core.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CardEntity::class], version = 1)
abstract class CardDataBase : RoomDatabase() {
    abstract fun getDao(): CardDao

}