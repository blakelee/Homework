package net.blakelee.homework.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.*
import com.yarolegovich.lovelydialog.LovelyChoiceDialog
import net.blakelee.homework.R
import net.blakelee.homework.activities.EditHomeworkActivity
import net.blakelee.homework.adapters.HomeworkAdapter
import net.blakelee.homework.base.BaseLifecycleFragment
import net.blakelee.homework.interfaces.OnLongClickListener
import net.blakelee.homework.models.Homework
import net.blakelee.homework.viewmodels.HomeworkViewModel
import net.blakelee.homework.views.AssignmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.startActivity

class HomeworkFragment(val classId: Long, val color: Int): BaseLifecycleFragment<HomeworkViewModel>(), OnLongClickListener {

    override val viewModelClass = HomeworkViewModel::class.java
    private val adapter = HomeworkAdapter(color, this)
    private val fab by lazy { find<FloatingActionButton>(R.id.fab_main) }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return AssignmentUI(adapter, classId).createView(AnkoContext.create(ctx, this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
        observeLiveData()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        fab.setOnClickListener {
            startActivity<EditHomeworkActivity>("id" to classId)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.class_details_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onLongClick(position: Int) {
        LovelyChoiceDialog(context)
                .setTitle("Select action")
                .setTopColor(color)
                .setIcon(R.drawable.ic_info)
                .setItems(arrayOf("Edit", "Delete"), { pos, _ ->
                    when (pos) {
                        0 -> startActivity<EditHomeworkActivity>(
                                "hw_id" to adapter.getItem(position).hw_id!!,
                                "id" to classId)
                        1 -> viewModel.deleteHomework(adapter.getItem(position))
                    }
                })
                .create()
                .show()
    }

    private fun observeLiveData() {
        viewModel = ViewModelProviders.of(this).get(viewModelClass)
        viewModel.getHomework(classId).observe(this, Observer<List<Homework>> {
            it?.let { adapter.dataSource = it }
        })
    }
}