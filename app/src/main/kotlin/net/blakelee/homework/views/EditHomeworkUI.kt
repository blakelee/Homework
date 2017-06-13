package net.blakelee.homework.views

import android.app.Activity
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import net.blakelee.homework.R
import net.blakelee.homework.adapters.ReminderAdapter
import net.blakelee.homework.models.Homework
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.percent.percentRelativeLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView


class EditHomeworkUI(var hw: Homework, var reminderAdapter: ReminderAdapter): AnkoComponent<Activity> {

    override fun createView(ui: AnkoContext<Activity>): View = with(ui) {
        verticalLayout {
            verticalLayout {
                appBarLayout {
                    toolbar {
                        setTitleTextColor(ContextCompat.getColor(ctx, R.color.icons))
                        backgroundColor = ContextCompat.getColor(ctx, R.color.primary)
                        id = R.id.toolbar_edit
                    }
                }
            }

            verticalLayout {
                textView("Title")
                editText {
                    setText(hw.title)
                }.addTextChangedListener(object : TextWatcher {
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    override fun afterTextChanged(s: Editable?) { hw.title = s.toString() }
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} })

                textView("Problems")
                editText {
                    setText(hw.problems)
                }.addTextChangedListener(object : TextWatcher {
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    override fun afterTextChanged(s: Editable?) { hw.problems = s.toString() }
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} })

                textView("Description")
                editText {
                    setText(hw.description)
                }.addTextChangedListener(object : TextWatcher {
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    override fun afterTextChanged(s: Editable?) { hw.description = s.toString() }
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} })

                textView("Due")
                percentRelativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    val left = button("Today") {
                        id = View.generateViewId()
                        backgroundResource = R.drawable.rounded_left
                        setAllCaps(false)
                    }.lparams {
                        percentLayoutInfo.widthPercent = 0.5f
                    }

                    button("10:00am") {
                        backgroundResource = R.drawable.rounded_right
                        setAllCaps(false)
                    }.lparams {
                        rightOf(left)
                        percentLayoutInfo.widthPercent = 0.5f
                    }
                }

                textView("Reminder")
                percentRelativeLayout {
                    id = R.id.reminder_text
                    val days = textView("Days before") {
                        id = View.generateViewId()
                    }.lparams {
                        percentLayoutInfo.widthPercent = 0.25f
                    }

                    val time = textView("Time") {
                        id = View.generateViewId()
                    }.lparams {
                        percentLayoutInfo.widthPercent = 0.40f
                        rightOf(days)
                    }

                    textView("Type").lparams {
                        percentLayoutInfo.widthPercent = 0.35f
                        rightOf(time)
                    }
                }

                recyclerView {
                    id = R.id.homework_recycler
                    adapter = reminderAdapter
                    layoutManager = LinearLayoutManager(ctx)
                }

                imageButton {
                    id = R.id.add
                    setImageResource(R.drawable.ic_add_black_24dp)
                    setColorFilter(android.R.color.black, PorterDuff.Mode.SRC_ATOP)
                }

                padding = dip(14)
            }
        }
    }
}