package com.google.seergii_tymofieiev.presentation.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject
import com.google.seergii_tymofieiev.R
import com.google.seergii_tymofieiev.presentation.base.BaseFragment

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
abstract class FlowFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.layout_container

    private val currentFragment
        get() = childFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    @Inject
    lateinit var cicerone: Cicerone<FlowRouter>

    protected val navigator: Navigator by lazy {
        object : SupportAppNavigator(this.activity, childFragmentManager, R.id.container) {
            override fun backTo(command: BackTo?) {
                super.backTo(command)
                command?.screen?.let {
                    if (childFragmentManager.findFragmentByTag(it.screenKey) == null) {
                        if (it is SupportAppScreen) {
                            navigator.applyCommands(arrayOf(Forward(it)))
                        }
                    }
                }
            }

            override fun activityBack() {
                onExit()
            }

            override fun setupFragmentTransaction(
                command: Command?,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                //fix incorrect order lifecycle categoryCategoryCallback of MainTabsFlowFragment
                fragmentTransaction.setReorderingAllowed(true)
            }
        }
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    open fun onExit() {}

    override fun onResume() {
        super.onResume()
        cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        cicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }
}