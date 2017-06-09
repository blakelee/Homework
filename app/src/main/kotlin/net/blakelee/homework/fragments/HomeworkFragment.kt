package net.blakelee.homework.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.*
import android.widget.AdapterView
import com.yarolegovich.lovelydialog.LovelyChoiceDialog
import net.blakelee.homework.R
import net.blakelee.homework.activities.EditHomeworkActivity
import net.blakelee.homework.adapters.HomeworkAdapter
import net.blakelee.homework.base.BaseLifecycleFragment
import net.blakelee.homework.viewmodels.HomeworkViewModel
import org.jetbrains.anko.support.v4.startActivity

class HomeworkFragment(val classId: Long, color: Int): BaseLifecycleFragment<HomeworkViewModel>(), AdapterView.OnItemLongClickListener {

    override val viewModelClass = HomeworkViewModel::class.java
    private val adapter = HomeworkAdapter(color)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
        observeLiveData()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.class_details_alt, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
        LovelyChoiceDialog(context)
                .setTitle("Select action")
                .setTopColor(ContextCompat.getColor(context, R.color.primary))
                .setIcon(R.drawable.ic_info)
                .setItems(arrayOf("Edit", "Delete"), { pos, _ ->
                    when (pos) {
                        0 -> startActivity<EditHomeworkActivity>("hw_id" to adapter.getItem(position).hw_id!!)
                        1 -> viewModel.deleteHomework(adapter.getItem(position))
                    }
                })
                .create()
                .show()

        return true
    }

    private fun observeLiveData() {
        viewModel.getHomework(classId).observe(this, Observer {
            it?.let { adapter.dataSource = it }
        })
    }
}