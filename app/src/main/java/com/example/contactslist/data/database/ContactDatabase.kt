package com.example.contactslist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactslist.data.database.entities.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun getDao(): ContactDao
}