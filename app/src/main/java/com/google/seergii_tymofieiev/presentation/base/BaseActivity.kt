package com.google.seergii_tymofieiev.presentation.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.arellomobile.mvp.MvpAppCompatActivity
import com.google.seergii_tymofieiev.R
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
abstract class BaseActivity : MvpAppCompatActivity(), BaseView {
    protected open val layoutRes: Int
        get() = R.layout.activity_app
    val navigator: Navigator =
        object : SupportAppNavigator(this, supportFragmentManager, R.id.root) {
            override fun setupFragmentTransaction(
                command: Command?,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                fragmentTransaction.setReorderingAllowed(true)
            }
        }
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    abstract fun injectDependency()
}