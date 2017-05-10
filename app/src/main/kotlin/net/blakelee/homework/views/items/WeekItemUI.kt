package net.blakelee.homework.views.items

import android.view.View
import android.view.ViewGroup
import net.blakelee.homework.R
import org.jetbrains.anko.*
import org.jetbrains.anko.percent.percentRelativeLayout

class WeekItemUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            percentRelativeLayout {
                lparams(width = matchParent, height = wrapContent)

                textView {
                    id = R.id.day_picker
                    setAllCaps(false)
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.5f
                }

                textView {
                    id = R.id.day_start
                    setAllCaps(false)
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.25f
                    rightOf(R.id.day_picker)
                }

                textView {
                    id = R.id.day_end
                    setAllCaps(false)
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.25f
                    rightOf(R.id.day_start)
                }
            }
        }
    }
}