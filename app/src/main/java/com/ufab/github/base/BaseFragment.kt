package com.ufab.github.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ufab.github.R
import com.ufab.github.global.utils.observeOnlyNotNull
import com.squareup.picasso.Picasso
import dagger.Lazy
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import kotlin.reflect.KClass


abstract class BaseFragment<T : ViewDataBinding?, V : BaseAndroidViewModel<*>?> : Fragment() {
    private var mActivity: BaseActivity<ViewDataBinding, BaseAndroidViewModel<*>>? = null
    private var mRootView: View? = null
    var viewDataBinding: ViewDataBinding? = null
        private set
    private var mViewModel: V? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V

    @Inject
    protected lateinit var picassoLazy: Lazy<Picasso>


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    fun getPicasso(): Picasso {
        return picassoLazy.get()
    }

    protected fun findNavController(): NavController? {
        activity?.let {
            return Navigation.findNavController(it as Activity, R.id.navHostFragment)
        } ?: return null
    }


    /**
     * observe snackBarMessage and show snack bar
     * observe loader and show hide progress bar
     * observe dialog and chose dialog
     *
     * @param viewModel BaseAndroidViewModel
     */
    protected fun registerBaseObserver(viewModel: ViewModel) {
        if (viewModel is BaseAndroidViewModel<*>) {
            registerSnackBar(viewModel)
            registerSimpleDialog(viewModel)
            registerChoseDialog(viewModel)
            registerProgressBar(viewModel)
            registerNavigation(viewModel)
        }
    }

    private fun registerNavigation(viewModel: BaseAndroidViewModel<*>) {
        viewModel.navigation.observe(viewLifecycleOwner, Observer { navigate(it) })
    }

    private fun registerSnackBar(viewModel: BaseAndroidViewModel<*>) {
        viewModel.snackBarMessage.observeOnlyNotNull(viewLifecycleOwner) { showSnackBar(it) }
    }

    private fun registerSimpleDialog(viewModel: BaseAndroidViewModel<*>) {
        viewModel.simpleDialog.observeOnlyNotNull(
                viewLifecycleOwner
        ) { simpleDialog ->
            showSimpleDialog(
                    simpleDialog.title,
                    simpleDialog.message,
                    simpleDialog.okActionBlock,
                    simpleDialog.dismissActionBlock
            )
        }
    }

    private fun registerChoseDialog(viewModel: BaseAndroidViewModel<*>) {
        viewModel.choseDialog.observeOnlyNotNull(
                viewLifecycleOwner
        ) { choseDialog ->
            Log.e("TAG", "registerChoseDialog")
            showChoseDialog(
                    choseDialog.title,
                    choseDialog.message,
                    choseDialog.ok,
                    choseDialog.cancel,
                    choseDialog.okActionBlock,
                    choseDialog.cancelActionBlock,
                    choseDialog.dismissActionBlock
            )
        }
    }

    private fun registerProgressBar(viewModel: BaseAndroidViewModel<*>) {
        viewModel.progressBar.observeOnlyNotNull(viewLifecycleOwner) {
            when {
                it -> showProgressBar()
                else -> hideProgressBar()
            }
        }
    }

    /**
     * show simple ok dialog
     *
     * @param titleId  resources id optional
     * @param messageId  resources id
     * @param actionBlock action to do on click
     * @param dismissActionBlock action to do on dismiss optional
     *
     */
    fun showSimpleDialog(
            @StringRes titleId: Int? = null, @StringRes messageId: Int, actionBlock: (() -> Unit)? = null,
            dismissActionBlock: (() -> Unit)? = null
    ) {
        activity?.let { (it as BaseActivity<*,*>).showSimpleDialog(titleId, messageId, actionBlock, dismissActionBlock) }
    }


    /**
     * show simple ok dialog
     * @param title  message string optional
     * @param message  message string
     * @param okActionBlock action to do on click
     * @param dismissActionBlock action to do on dismiss optional
     *
     */
    fun showSimpleDialog(
            title: String? = null,
            message: String,
            okActionBlock: (() -> Unit)? = null,
            dismissActionBlock: (() -> Unit)? = null
    ) {
        activity?.let { (it as BaseActivity<*,*>).showSimpleDialog(title, message, okActionBlock, dismissActionBlock) }
    }


    /**
     * show simple ok dialog
     *
     * @param titleId      dialog title resources Id optional
     * @param messageId       message resources Id
     * @param okId        action button resources Id
     * @param cancelId     cancel button resources Id
     * @param okActionBlock    action to do on click ok optional
     * @param cancelActionBlock action to do on click cancel optional
     * @param dismissActionBlock action to do on dismiss optional
     */
    fun showChoseDialog(
            @StringRes titleId: Int? = null, @StringRes messageId: Int, @StringRes okId: Int,
            @StringRes cancelId: Int, okActionBlock: (() -> Unit)? = null,
            cancelActionBlock: (() -> Unit)? = null,
            dismissActionBlock: (() -> Unit)? = null
    ) {

        activity?.let {
            (it as BaseActivity<*,*>).showChoseDialog(
                    titleId,
                    messageId,
                    okId,
                    cancelId,
                    okActionBlock,
                    cancelActionBlock,
                    dismissActionBlock
            )
        }
    }

    /**
     * show simple ok dialog
     *
     * @param title      dialog title optional
     * @param message       message
     * @param ok        action button
     * @param cancel     cancel button
     * @param okActionBlock     action to do on click ok optional
     * @param cancelActionBlock action to do on click cancel optional
     * @param dismissActionBlock action to do on dismiss optional
     *
     */
    fun showChoseDialog(
            title: String? = null,
            message: String,
            ok: String,
            cancel: String,
            okActionBlock: (() -> Unit)? = null,
            cancelActionBlock: (() -> Unit)? = null,
            dismissActionBlock: (() -> Unit)? = null
    ) {
        activity?.let {
            (it as BaseActivity<*,*>).showChoseDialog(
                    title,
                    message,
                    ok,
                    cancel,
                    okActionBlock,
                    cancelActionBlock,
                    dismissActionBlock
            )
        }
    }


    /**
     * try to hide Keyboard from the focused screen
     */
    fun hideKeyboard() {
        activity?.let { (it as BaseActivity<*,*>).hideKeyboard() }
    }


    /**
     * This method show simple Snackbar
     *
     * @param message message dialog text
     */
    fun showSnackBar(@SuppressLint("SupportAnnotationUsage") @StringRes message: String) {
        activity?.let { (it as BaseActivity<*,*>).showSnackBar(message) }
    }

    /**
     * This method show simple Snackbar
     *
     * @param messageId message dialog text id
     */
    fun showSnackBar(@StringRes messageId: Int) {

        activity?.let { (it as BaseActivity<*,*>).showSnackBar(messageId) }
    }

    /**
     * hide snackBar if it's on screen
     */
    fun hideSnackBar() {
        activity?.let { (it as BaseActivity<*,*>).hideSnackBar() }
    }


    /**
     * show unavailable Network Error
     */
    fun showErrorNetworkDialog() {
        activity?.let { (it as BaseActivity<*,*>).showErrorNetworkDialog() }
    }

    /**
     * show server Error
     */
    fun showErrorServerDialog() {
        activity?.let { (it as BaseActivity<*,*>).showErrorServerDialog() }
    }


    /**
     * show unavailable Network Error
     */
    fun showErrorNetworkSnackBar() {
        activity?.let { (it as BaseActivity<*,*>).showErrorNetworkSnackBar() }
    }

    /**
     * show blocking progressBar on the root of the activity
     */
    fun showProgressBar() {
        activity?.let { (it as BaseActivity<*,*>).showProgressBar() }
    }


    /**
     * hide blocking progressBar
     */
    fun hideProgressBar() {
        activity?.let { (it as BaseActivity<*,*>).hideProgressBar() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mRootView = viewDataBinding!!.root
        return mRootView
    }


        override fun onViewCreated(
            view: View,
            savedInstanceState: Bundle?
        ) {
            super.onViewCreated(view, savedInstanceState)
            viewDataBinding!!.setVariable(bindingVariable, mViewModel)
            viewDataBinding!!.lifecycleOwner = this
            viewDataBinding!!.executePendingBindings()
        }


    /**
     * startActivity to class
     */
    fun navigateToClass(kClass: KClass<out Activity>, shouldFinish: Boolean = false) {
        activity?.let { navigateToClass(kClass, shouldFinish) }
    }


    /**
     * handling navigation actions
     * @param navigationTo activity to navigate to
     */
    open fun navigate(navigationTo: com.ufab.github.global.helper.Navigation) {

    }


}