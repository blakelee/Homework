package net.blakelee.homework.views

import android.app.Activity
import android.view.Gravity
import android.view.View
import net.blakelee.homework.R
import org.jetbrains.anko.*

class AboutUI: AnkoComponent<Activity> {
    override fun createView(ui: AnkoContext<Activity>): View {
        return with(ui) {
            relativeLayout {
                textView {
                    gravity = Gravity.CENTER
                    textResource = R.string.about_text
                }.lparams(width = matchParent)
            }
        }
    }
}