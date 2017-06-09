package net.blakelee.homework.base

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment

//This code written by Ihor Kucherenko as part of Android-Architecture-Components
//The only thing I changed was making it a fragment instead of AppCompatActivity

@Suppress("LeakingThis")
abstract class BaseLifecycleFragment<T : AndroidViewModel> : Fragment(), LifecycleRegistryOwner {

    abstract val viewModelClass: Class<T>
    protected lateinit var viewModel: T
    private val registry = LifecycleRegistry(this)
    override fun getLifecycle(): LifecycleRegistry = registry
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(viewModelClass)
    }
}