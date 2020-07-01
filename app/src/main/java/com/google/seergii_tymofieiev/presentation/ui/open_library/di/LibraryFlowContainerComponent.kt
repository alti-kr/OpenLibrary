package com.google.seergii_tymofieiev.presentation.ui.open_library.di

import com.google.seergii_tymofieiev.presentation.di.module.FlowNavigationModule
import com.google.seergii_tymofieiev.presentation.di.scope.PerFlow
import com.google.seergii_tymofieiev.presentation.ui.open_library.LibraryFlowFragment
import dagger.Subcomponent

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
@PerFlow
@Subcomponent(modules = [FlowNavigationModule::class])
interface LibraryFlowContainerComponent {
    fun plusFlowFragments(): LibraryFlowFragmentsComponent
    fun inject(libraryFlowFragment: LibraryFlowFragment)
}