package com.google.seergii_tymofieiev.presentation.di.module

import com.google.seergii_tymofieiev.data.repository.BooksDataRepository
import com.google.seergii_tymofieiev.data.repository.BooksRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by Sergii Tymofieiev on 01.07.2020
 */
@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun booksRepository(booksRepositoryImpl: BooksDataRepository): BooksRepository

}