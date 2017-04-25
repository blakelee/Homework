package net.blakelee.homework.item_views

import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.percent.percentRelativeLayout


class WeekItemUI : org.jetbrains.anko.AnkoComponent<ViewGroup> {
    override fun createView(ui: org.jetbrains.anko.AnkoContext<ViewGroup>): android.view.View {
        return with(ui) {
            percentRelativeLayout {
                lparams(width = org.jetbrains.anko.matchParent, height = org.jetbrains.anko.wrapContent)

                textView {
                    id = net.blakelee.homework.R.id.day_picker
                    setAllCaps(false)
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.5f
                }

                textView {
                    id = net.blakelee.homework.R.id.day_start
                    setAllCaps(false)
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.25f
                    rightOf(net.blakelee.homework.R.id.day_picker)
                }

                textView {
                    id = net.blakelee.homework.R.id.day_end
                    setAllCaps(false)
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.25f
                    rightOf(net.blakelee.homework.R.id.day_start)
                }
            }
        }
    }
}