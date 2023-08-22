package com.example.contactslist.data.repositories

import com.example.contactslist.data.database.ContactDao
import com.example.contactslist.data.database.entities.Contact
import com.example.contactslist.di.IoDispatcher
import com.example.contactslist.domain.repository.ContactRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val contactDao: ContactDao,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) :
    ContactRepository {
    override suspend fun addContact(contact: Contact) = withContext(ioDispatcher) {
        contactDao.addContact(contact)
    }

    override fun getContactList(): Flow<List<Contact>> {
        return contactDao.getContactList().flowOn(ioDispatcher)
    }

    override suspend fun deleteContact(contact: Contact) = withContext(ioDispatcher) {
        contactDao.deleteContact(contact)
    }

    override suspend fun updateContact(contact: Contact) = withContext(ioDispatcher) {
        contactDao.updateContact(contact)
    }

    override suspend fun getContactByNumber(number: String): Contact = withContext(ioDispatcher) {
        return@withContext contactDao.getContactByNumber(number)
    }
}