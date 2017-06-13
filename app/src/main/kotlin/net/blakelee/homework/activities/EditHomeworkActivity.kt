package net.blakelee.homework.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.percent.PercentRelativeLayout
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import net.blakelee.homework.R
import net.blakelee.homework.adapters.ReminderAdapter
import net.blakelee.homework.base.BaseLifecycleActivity
import net.blakelee.homework.models.Reminder
import net.blakelee.homework.viewmodels.EditHomeworkViewModel
import net.blakelee.homework.views.EditHomeworkUI
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class EditHomeworkActivity: BaseLifecycleActivity<EditHomeworkViewModel>() {

    override val viewModelClass = EditHomeworkViewModel::class.java
    private val reminderAdapter = ReminderAdapter(this::removeReminder)
    private val reminderText by lazy { find<PercentRelativeLayout>(R.id.reminder_text) }
    private val addReminder by lazy { find<ImageButton>(R.id.add) }
    private var hw_id: Long = -1
    private var id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle  = intent.extras
        hw_id = bundle.getLong("hw_id", -1)
        id = bundle.getLong("id")

        viewModel = ViewModelProviders.of(this).get(viewModelClass)
        viewModel.loadHomeworkById(hw_id, id)

        EditHomeworkUI(viewModel.homework.value!!, reminderAdapter).setContentView(this)

        subscribeReminders()
        setupToolbar()
        setupListeners()
    }

    private fun subscribeReminders() {
        viewModel.reminders.observe(this, Observer<MutableList<Reminder>> {
            it?.let {
                reminderAdapter.dataSource = it
                if (it.isEmpty())
                    reminderText.visibility = View.GONE
                else
                    reminderText.visibility = View.VISIBLE
            }
        })
    }

    private fun setupToolbar() {
        val toolbar = find<Toolbar>(R.id.toolbar_edit)
        setSupportActionBar(toolbar)

        val arrow: Drawable = resources.getDrawable(R.drawable.abc_ic_ab_back_material)
        arrow.setColorFilter(resources.getColor(R.color.icons), PorterDuff.Mode.SRC_ATOP)

        supportActionBar?.setHomeAsUpIndicator(arrow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (hw_id == null)
            supportActionBar?.setTitle(R.string.add_homework)
        else
            supportActionBar?.setTitle(R.string.edit_homework)
    }

    private fun setupListeners() {
        addReminder.setOnClickListener {
            viewModel.addReminder()
        }
    }

    fun removeReminder(position: Int) {
        viewModel.removeReminder(position)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_class_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
            R.id.action_save -> {
                viewModel.insertHomework()
                super.onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}