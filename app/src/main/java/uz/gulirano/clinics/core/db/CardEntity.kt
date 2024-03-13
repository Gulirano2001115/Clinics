package uz.gulirano.clinics.core.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("card_table")
data class CardEntity(
    val cardNumber: String,
    val cardDate: String,
    val cardVCC: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}