package net.blakelee.homework.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.*
import com.yarolegovich.lovelydialog.LovelyChoiceDialog
import net.blakelee.homework.R
import net.blakelee.homework.activities.EditHomeworkActivity
import net.blakelee.homework.adapters.ExamAdapter
import net.blakelee.homework.base.BaseLifecycleFragment
import net.blakelee.homework.interfaces.OnLongClickListener
import net.blakelee.homework.models.Exam
import net.blakelee.homework.viewmodels.ExamViewModel
import net.blakelee.homework.views.AssignmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class ExamFragment(val classId: Long, val color: Int): BaseLifecycleFragment<ExamViewModel>(), OnLongClickListener {

    override val viewModelClass = ExamViewModel::class.java
    private val adapter = ExamAdapter(color, this)

    //DUMMY DATA
    private val homework: List<Exam> = listOf(Exam(), Exam())

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return AssignmentUI(adapter).createView(AnkoContext.create(ctx, this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
        //observeLiveData()

        //DUMMY DATA
        adapter.dataSource = homework

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
                        0 -> startActivity<EditHomeworkActivity>("hw_id" to adapter.getItem(position).exam_id!!)
                        1 -> viewModel.deleteExam(adapter.getItem(position))
                    }
                })
                .create()
                .show()
    }

    private fun observeLiveData() {
        viewModel.getExams(classId).observe(this, Observer {
            it?.let { adapter.dataSource = it }
        })
    }
}