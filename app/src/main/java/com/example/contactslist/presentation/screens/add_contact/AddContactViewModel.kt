package com.example.contactslist.presentation.screens.add_contact

import android.database.sqlite.SQLiteConstraintException
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactslist.data.database.entities.Contact
import com.example.contactslist.domain.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddContactViewModel @Inject constructor(
    private val repository: ContactRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AddContactState())
    val state: State<AddContactState> = _state

    init {
        viewModelScope.launch {
            val number = savedStateHandle.get<String>("number")
            number?.let {
                val contact = repository.getContactByNumber(it)
                _state.value = state.value.copy(
                    id = contact.uuid,
                    number = contact.phoneNumber,
                    name = contact.name,
                    address = contact.address,
                    isAddMode = false
                )
            }
        }
    }

    fun onEvent(event: AddContactEvent) {
        when (event) {
            is AddContactEvent.PhoneNumberInput -> {
                val text = event.text
                if (text.length < 14) {
                    _state.value = state.value.copy(
                        number = text,
                        numberError = if (text.isEmpty()) "Данное поле должно быть заполнено" else if (text.all { it in '0'..'9' }) "" else "Некорректный ввод"
                    )
                    enableButton()
                }
            }

            is AddContactEvent.NameInput -> {
                val text = event.text
                if (text.length < 21) {
                    _state.value = state.value.copy(
                        name = text,
                        nameError = if (text.isEmpty()) "Данное поле должно быть заполнено" else ""
                    )
                    enableButton()
                }
            }

            is AddContactEvent.AddressInput -> {
                val text = event.text
                if (text.length < 51) {
                    _state.value = state.value.copy(
                        address = text,
                        addressError = if (text.isEmpty()) "Данное поле должно быть заполнено" else ""
                    )
                    enableButton()
                }
            }

            is AddContactEvent.ButtonClick -> viewModelScope.launch {
                if (state.value.isAddMode) addContact() else updateContact()
            }
        }
    }

    private fun enableButton() {
        _state.value = state.value.copy(
            isButtonEnabled = with(state.value) {
                this.nameError.isBlank() &&
                        this.numberError.isBlank() &&
                        this.addressError.isBlank() &&
                        this.name.isNotEmpty() &&
                        this.number.isNotEmpty() &&
                        this.address.isNotEmpty()
            }
        )
    }

    private suspend fun addContact() {
        try {
            repository.addContact(
                Contact(
                    name = state.value.name,
                    phoneNumber = state.value.number,
                    address = state.value.address
                )
            )
            _state.value = state.value.copy(goBackEvent = true)
        } catch (e: SQLiteConstraintException) {
            _state.value = state.value.copy(numberError = "Контакт с этим номером уже существует")
        }
    }

    private suspend fun updateContact() {
        try {
            repository.updateContact(
                Contact(
                    uuid = state.value.id,
                    name = state.value.name,
                    phoneNumber = state.value.number,
                    address = state.value.address,
                )
            )
            _state.value = state.value.copy(goBackEvent = true)
        } catch (e: SQLiteConstraintException) {
            _state.value = state.value.copy(numberError = "Контакт с этим номером уже существует")
        }
    }
}