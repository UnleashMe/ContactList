package com.example.contactslist.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.contactslist.data.database.entities.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addContact(contact: Contact)

    @Query("SELECT * FROM contact ORDER BY name COLLATE NOCASE")
    fun getContactList(): Flow<List<Contact>>

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Query("SELECT * FROM contact WHERE phoneNumber =:number")
    suspend fun getContactByNumber(number: String): Contact
}