package com.example.contactslist.domain.repository

import com.example.contactslist.data.database.entities.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    suspend fun addContact(contact: Contact)

    fun getContactList(): Flow<List<Contact>>

    suspend fun deleteContact(contact: Contact)

    suspend fun updateContact(contact: Contact)

    suspend fun getContactByNumber(number: String): Contact
}