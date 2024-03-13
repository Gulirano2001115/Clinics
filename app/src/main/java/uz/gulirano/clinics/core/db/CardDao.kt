package uz.gulirano.clinics.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CardDao {

    @Query("SELECT * FROM card_table")
    fun getCards(): List<CardEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCards(card: CardEntity)


}