package dev.logarithmus.p2pdroid.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import dev.logarithmus.p2pdroid.R
import dev.logarithmus.p2pdroid.ui.adapter.ContactsAdapter
import dev.logarithmus.p2pdroid.ui.presenter.ContactChooserPresenter
import dev.logarithmus.p2pdroid.ui.view.ContactChooserView
import dev.logarithmus.p2pdroid.ui.viewmodel.ContactViewModel
import dev.logarithmus.p2pdroid.utils.argument
import dev.logarithmus.p2pdroid.utils.bind
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ContactChooserActivity : SkeletonActivity(), ContactChooserView {

    private val message: String? by argument(EXTRA_MESSAGE)
    private val filePath: String? by argument(EXTRA_FILE_PATH)

    private val presenter: ContactChooserPresenter by inject { parametersOf(this) }

    private val contactsList: RecyclerView by bind(R.id.rv_contacts)
    private val noContactsLabel: TextView by bind(R.id.tv_no_contacts)

    private val contactsAdapter = ContactsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_chooser, ActivityType.CHILD_ACTIVITY)
        lifecycle.addObserver(presenter)

        contactsList.layoutManager = LinearLayoutManager(this)
        contactsList.adapter = contactsAdapter

        contactsAdapter.clickListener = {
            ChatActivity.start(this, it.address, message, filePath)
            finish()
        }
    }

    override fun showContacts(contacts: List<ContactViewModel>) {
        contactsAdapter.conversations = ArrayList(contacts)
        contactsAdapter.notifyDataSetChanged()
    }

    override fun showNoContacts() {
        noContactsLabel.visibility = View.VISIBLE
        contactsList.visibility = View.GONE
    }

    companion object {

        private const val EXTRA_MESSAGE = "extra.message"
        private const val EXTRA_FILE_PATH = "extra.file_path"

        fun start(context: Context, message: String?, filePath: String?) {
            val intent = Intent(context, ContactChooserActivity::class.java)
                    .putExtra(EXTRA_MESSAGE, message)
                    .putExtra(EXTRA_FILE_PATH, filePath)

            context.startActivity(intent)
        }
    }
}
