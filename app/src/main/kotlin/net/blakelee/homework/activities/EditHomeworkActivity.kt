package net.blakelee.homework.activities

import android.os.Bundle
import net.blakelee.homework.adapters.ReminderAdapter
import net.blakelee.homework.base.BaseLifecycleActivity
import net.blakelee.homework.viewmodels.HomeworkViewModel


class EditHomeworkActivity: BaseLifecycleActivity<HomeworkViewModel>() {

    override val viewModelClass = HomeworkViewModel::class.java
    private val reminderAdapter = ReminderAdapter()
    private var hw_id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle  = intent.extras
        hw_id = bundle?.getLong("hw_id")
    }
}