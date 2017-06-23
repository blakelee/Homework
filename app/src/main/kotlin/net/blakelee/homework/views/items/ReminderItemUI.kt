package net.blakelee.homework.views.items

import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import net.blakelee.homework.R
import org.jetbrains.anko.*
import org.jetbrains.anko.percent.percentRelativeLayout

class ReminderItemUI: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            percentRelativeLayout {

                val buttonHeight = dip(48)

                val text = editText {
                    id = R.id.days
                    backgroundResource = R.drawable.rounded_left
                    gravity = Gravity.CENTER_HORIZONTAL
                    inputType = InputType.TYPE_CLASS_NUMBER
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.25f
                    height = buttonHeight
                }

                val time = button {
                    id = R.id.time
                    backgroundResource = R.drawable.square
                    setAllCaps(false)
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.25f
                    height = buttonHeight
                    rightOf(text)
                }

                val type = spinner {
                    id = R.id.type
                    backgroundResource = R.drawable.square
                    adapter = ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.notification_type))
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.4f
                    rightOf(time)
                    height = buttonHeight
                }

                button("-") {
                    id = R.id.remove
                    backgroundResource = R.drawable.rounded_right
                }.lparams {
                    percentLayoutInfo.widthPercent = 0.1f
                    rightOf(type)
                    height = buttonHeight
                }
            }
        }
    }
}