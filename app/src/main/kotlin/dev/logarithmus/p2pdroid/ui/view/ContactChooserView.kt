package dev.logarithmus.p2pdroid.ui.view

import dev.logarithmus.p2pdroid.ui.viewmodel.ContactViewModel

interface ContactChooserView {
    fun showContacts(contacts: List<ContactViewModel>)
    fun showNoContacts()
}
