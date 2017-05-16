package net.blakelee.homework.views.items

import android.view.View
import android.view.ViewGroup
import net.blakelee.homework.R
import org.jetbrains.anko.*
import org.jetbrains.anko.percent.percentRelativeLayout

class EditWeekItemUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            percentRelativeLayout {
                lparams(width = matchParent, height = wrapContent)

                button {
                    id = R.id.day_picker
                    setAllCaps(false)
                    backgroundResource = R.drawable.rounded_left
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.4f
                }

                button {
                    id = R.id.day_start
                    backgroundResource = R.drawable.square
                    setAllCaps(false)
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.25f
                    rightOf(R.id.day_picker)
                }

                button {
                    id = R.id.day_end
                    setAllCaps(false)
                    backgroundResource = R.drawable.square
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.25f
                    rightOf(R.id.day_start)
                }

                button("+") {
                    id = R.id.addremove_day
                    backgroundResource = R.drawable.rounded_right
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.1f
                    rightOf(R.id.day_end)
                }
            }
        }
    }
}