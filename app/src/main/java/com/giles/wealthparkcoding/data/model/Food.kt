package com.giles.wealthparkcoding.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(
    @PrimaryKey val name: String,
    val image: String,
)
