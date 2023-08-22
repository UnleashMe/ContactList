package com.example.contactslist.data.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(indices = [Index(value = ["phoneNumber"], unique = true)])
data class Contact(
    @PrimaryKey(autoGenerate = false)
    val uuid: String = UUID.randomUUID().toString(),
    val name: String,
    val phoneNumber: String,
    val address: String
)