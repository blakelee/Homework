package net.blakelee.homework.views.items

import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.percent.percentRelativeLayout

class EditWeekItemUI : org.jetbrains.anko.AnkoComponent<ViewGroup> {
    override fun createView(ui: org.jetbrains.anko.AnkoContext<ViewGroup>): android.view.View {
        return with(ui) {
            percentRelativeLayout {
                lparams(width = org.jetbrains.anko.matchParent, height = org.jetbrains.anko.wrapContent)
                button {
                    id = net.blakelee.homework.R.id.day_picker
                    setAllCaps(false)
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.4f
                }

                button {
                    id = net.blakelee.homework.R.id.day_start
                    setAllCaps(false)
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.25f
                    rightOf(net.blakelee.homework.R.id.day_picker)
                }

                button {
                    id = net.blakelee.homework.R.id.day_end
                    setAllCaps(false)
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.25f
                    rightOf(net.blakelee.homework.R.id.day_start)
                }

                button("+") {
                    id = net.blakelee.homework.R.id.addremove_day
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.1f
                    rightOf(net.blakelee.homework.R.id.day_end)
                }
            }
        }
    }
}