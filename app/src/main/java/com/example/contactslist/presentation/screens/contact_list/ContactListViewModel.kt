package com.example.contactslist.presentation.screens.contact_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactslist.data.database.entities.Contact
import com.example.contactslist.domain.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel() {

    private val _state = mutableStateOf(listOf<Contact>())
    val state: State<List<Contact>> = _state

    init {
        viewModelScope.launch {
            contactRepository.getContactList().collectLatest {
                _state.value = it
            }
        }
    }
    fun onEvent(event: ContactListEvent) {
        when (event) {
            is ContactListEvent.DeleteContact -> viewModelScope.launch {
                contactRepository.deleteContact(event.contact)
            }
        }
    }
}