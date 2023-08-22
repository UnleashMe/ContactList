package com.example.contactslist.di

import android.content.Context
import androidx.room.Room
import com.example.contactslist.data.database.ContactDao
import com.example.contactslist.data.database.ContactDatabase
import com.example.contactslist.data.repositories.ContactRepositoryImpl
import com.example.contactslist.domain.repository.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesContactDao(@ApplicationContext context: Context): ContactDao {
        val database =
            Room.databaseBuilder(context, ContactDatabase::class.java, "contact.db").build()
        return database.getDao()
    }

    @Provides
    @Singleton
    fun providesContactRepository(
        contactDao: ContactDao,
        @IoDispatcher
        coroutineDispatcher: CoroutineDispatcher
    ): ContactRepository {
        return ContactRepositoryImpl(contactDao, coroutineDispatcher)
    }

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @MainImmediateDispatcher
    @Provides
    fun providesMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate

    @Singleton
    @Provides
    fun providesCoroutineScope(
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + ioDispatcher)
}

