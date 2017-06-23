package net.blakelee.homework.activities

import net.blakelee.homework.base.BaseLifecycleActivity
import net.blakelee.homework.viewmodels.EditExamViewModel

class EditExamActivity: BaseLifecycleActivity<EditExamViewModel>()
{
    override val viewModelClass: Class<EditExamViewModel> = EditExamViewModel::class.java
}