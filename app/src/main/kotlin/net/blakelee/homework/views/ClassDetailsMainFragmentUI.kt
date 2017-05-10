package net.blakelee.homework.views

import android.content.Intent
import android.net.Uri
import android.support.v4.app.Fragment
import android.view.View
import net.blakelee.homework.R
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.models.Week
import net.blakelee.homework.views.items.WeekItemUI
import org.jetbrains.anko.*
import java.io.File

class ClassDetailsMainFragmentUI(val classDetails : ClassDetails) : AnkoComponent<Fragment> {
    override fun createView(ui: AnkoContext<Fragment>): View = with(ui) {
        scrollView {
            lparams(width = matchParent)
            verticalLayout {

                if (classDetails.image != "") {
                    val file = File(classDetails.image)
                    if (file.exists()) {
                        imageView {
                            setImageURI(Uri.fromFile(file))
                        }.lparams(width = matchParent, height = dip(200))
                    }
                }

                verticalLayout {

                    textView("Class Name")
                    textView (classDetails.name)

                    textView("Class Days")
                    for(week : Week in classDetails.week) {
                        this.addView(WeekItemUI().createView(AnkoContext.create(ctx, this)))
                    }

                    if (classDetails.professor != "") {
                        textView("Professor Name")
                        textView(classDetails.professor)
                    }

                    if (classDetails.location != "") {
                    textView("Location")
                    textView(classDetails.location)
                    }

                    if (classDetails.email != "") {
                        textView("Email Address")
                        textView(classDetails.email)
                    }

                    if (classDetails.phone != "") {
                        textView("Phone Number")
                        textView(classDetails.phone) {
                            setOnClickListener {
                                val intent = Intent(Intent.ACTION_DIAL)
                                intent.data = Uri.parse("tel:" + classDetails.phone)
                                ctx.startActivity(intent)
                            }
                        }
                    }

                    if (classDetails.hours != null) {
                        textView("Credit Hours")
                        editText {
                            id = R.id.credit_hours
                            setText(classDetails.hours?.toString())
                        }
                    }

                    if (classDetails.finals.getDay() != "None") {
                        textView("Final")
                        linearLayout {
                            textView(classDetails.finals.getDay()).lparams(weight = 0.5f)
                            textView(classDetails.finals.getStartTime())}.lparams(weight = 0.25f)
                            textView(classDetails.finals.getEndTime()).lparams(weight = 0.25f)
                        }
                    }

                    padding = dip(14)
                }.lparams(height = matchParent, width = matchParent)
        }
    }
}