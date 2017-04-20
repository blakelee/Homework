package net.blakelee.homework.views

import android.view.View
import android.view.ViewGroup
import net.blakelee.homework.R
import org.jetbrains.anko.*
import org.jetbrains.anko.percent.percentRelativeLayout

class DaysItemUI : AnkoComponent<View> {
    override fun createView(ui: AnkoContext<View>): View {
        return with(ui) {
            percentRelativeLayout {
                lparams(width = matchParent, height = wrapContent)
                button {
                    id = R.id.day_picker
                    setAllCaps(false)
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.4f
                }

                button {
                    id = R.id.day_start
                    setAllCaps(false)
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.25f
                    rightOf(R.id.day_picker)
                }

                button {
                    id = R.id.day_end
                    setAllCaps(false)
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.25f
                    rightOf(R.id.day_start)
                }

                button("+") {
                    id = R.id.addremove_day
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.1f
                    rightOf(R.id.day_end)
                }
            }
        }
    }
}