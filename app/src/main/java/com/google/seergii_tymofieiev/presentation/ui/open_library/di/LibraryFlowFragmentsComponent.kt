package com.google.seergii_tymofieiev.presentation.ui.open_library.di

import com.google.seergii_tymofieiev.presentation.di.scope.PerFragment
import dagger.Subcomponent

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
@PerFragment
@Subcomponent
interface LibraryFlowFragmentsComponent {
    companion object {
        var libraryFlowFragmentsComponent: LibraryFlowFragmentsComponent? = null
    }
}