package com.google.seergii_tymofieiev.presentation.base

import android.os.Bundle
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
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private val navigator: Navigator =
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
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependency()
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
    }
    abstract fun injectDependency()
    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}