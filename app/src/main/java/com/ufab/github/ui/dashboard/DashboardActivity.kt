package com.ufab.github.ui.dashboard

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.ufab.github.BR
import com.ufab.github.R
import com.ufab.github.base.BaseActivity
import com.ufab.github.databinding.ActivityHomeBinding
import com.ufab.github.global.helper.ViewModelFactory
import com.ufab.github.ui.dashboard.homefragment.HomeFragment

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject
import kotlin.reflect.KClass


class DashboardActivity : BaseActivity<ActivityHomeBinding,DashboardViewModel>(),DashboardNavigator,HasAndroidInjector {
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    private lateinit var navController: NavController

    private var currentFragment: KClass<out Any>? = null




    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var mDashboardViewModel: DashboardViewModel? = null
    private var mHomeActivityBinding: ActivityHomeBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_home
    override val viewModel: DashboardViewModel
        get() {
            mDashboardViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)
            return mDashboardViewModel!!
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = Navigation.findNavController(this, R.id.navHostFragment)
        navController.setGraph(R.navigation.nav_graph, intent.extras)

        bottomNavigation.setOnNavigationItemSelectedListener(this::onNavigationItemSelected)
        registerBaseObservers(viewModel)


    }
    /**
     * Required for SupportNavigateUp
     */
    override fun onSupportNavigateUp() = Navigation
        .findNavController(this, R.id.navHostFragment).navigateUp()

    override fun androidInjector(): AndroidInjector<Any> {
return fragmentDispatchingAndroidInjector
    }

    override fun navigate(navigationTo: com.ufab.github.global.helper.Navigation) {
        super.navigate(navigationTo)
        when (navigationTo.navigateTo) {
            HomeFragment::class->{
                if (currentFragment?.equals(HomeFragment::class) == true) return
                currentFragment=HomeFragment::class
                val navigation2 = NavOptions.Builder().setPopUpTo(R.id.task1, true).setLaunchSingleTop(true).build()
                navController.navigate(R.id.task2, null, navigation2)



            }
        }
    }
    private fun onNavigationItemSelected(menu: MenuItem): Boolean {
        when (menu.itemId) {
            R.id.task1 -> viewModel.onActionOneClicked()
            R.id.task2 -> viewModel.onActionTwoClicked()
            R.id.task3 -> viewModel.onActionThreeClicked()
        }
        return true
    }

}
