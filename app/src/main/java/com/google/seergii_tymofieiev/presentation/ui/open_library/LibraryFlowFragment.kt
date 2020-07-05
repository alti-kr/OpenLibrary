package com.google.seergii_tymofieiev.presentation.ui.open_library

import android.os.Bundle
import com.google.seergii_tymofieiev.R
import com.google.seergii_tymofieiev.presentation.navigation.FlowFragment
import com.google.seergii_tymofieiev.presentation.navigation.FlowRouter
import com.google.seergii_tymofieiev.presentation.navigation.Screens
import com.google.seergii_tymofieiev.presentation.ui.app_activity.di.ActivityComponent
import com.google.seergii_tymofieiev.presentation.ui.open_library.di.LibraryFlowFragmentsComponent.Companion.libraryFlowFragmentsComponent
import com.google.seergii_tymofieiev.utils.setLaunchScreen
import javax.inject.Inject

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
class LibraryFlowFragment : FlowFragment() {
    companion object {
        fun newInstance(): LibraryFlowFragment =
            LibraryFlowFragment()
    }

    @Inject
    lateinit var flowRouter: FlowRouter

    override val layoutRes: Int = R.layout.layout_container
    override fun injectDaggerDependency() {
        ActivityComponent.activityComponent
            ?.plusLibraryFlowComponent()
            ?.apply {
                inject(this@LibraryFlowFragment)
                libraryFlowFragmentsComponent = plusFlowFragments()
            }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.setLaunchScreen(Screens.SearchBookScreen)
    }

    override fun onExit() {
       flowRouter.finishFlow()
    }
}