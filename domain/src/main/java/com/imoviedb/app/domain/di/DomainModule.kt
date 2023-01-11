package com.imoviedb.app.domain.di

import com.imoviedb.app.domain.account.repository.AccountRepository
import com.imoviedb.app.domain.account.repository.AccountRepositoryImpl
import com.imoviedb.app.domain.account.usecase.GetAccountUseCase
import com.imoviedb.app.domain.account.usecase.GetAccountUseCaseImpl
import com.imoviedb.app.domain.authentication.guestuser.repository.GuestUserAuthRepository
import com.imoviedb.app.domain.authentication.guestuser.repository.GuestUserAuthRepositoryImpl
import com.imoviedb.app.domain.authentication.guestuser.usecase.AuthenticationUseCase
import com.imoviedb.app.domain.authentication.guestuser.usecase.AuthenticationUseCaseImpl
import com.imoviedb.app.domain.authentication.normaluser.repository.LoginRepository
import com.imoviedb.app.domain.authentication.normaluser.repository.LoginRepositoryImpl
import com.imoviedb.app.domain.authentication.normaluser.usecase.*
import com.imoviedb.app.domain.popularshows.details.repository.PopularShowDetailsRepository
import com.imoviedb.app.domain.popularshows.details.repository.PopularShowDetailsRepositoryImpl
import com.imoviedb.app.domain.popularshows.details.usecase.GetPopularShowDetailsUseCase
import com.imoviedb.app.domain.popularshows.details.usecase.GetPopularShowDetailsUseCaseImpl
import com.imoviedb.app.domain.popularshows.showslist.repository.PopularShowsRepository
import com.imoviedb.app.domain.popularshows.showslist.repository.PopularShowsRepositoryImpl
import com.imoviedb.app.domain.popularshows.showslist.usecase.PopularShowsUseCase
import com.imoviedb.app.domain.popularshows.showslist.usecase.PopularShowsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class DomainModule {

    @Binds
    abstract fun providePopularShowRepo(popularShowsRepositoryImpl: PopularShowsRepositoryImpl) : PopularShowsRepository

    @Binds
    abstract fun providePopularShowUseCase(popularShowsUseCaseImpl: PopularShowsUseCaseImpl) : PopularShowsUseCase

    @Binds
    abstract fun provideGetPopularShowDetailsUseCase(popularShowsDetailsUseCase: GetPopularShowDetailsUseCaseImpl) : GetPopularShowDetailsUseCase


    @Binds
    abstract fun providePopularShowDetailsRepository(popularShowDetailsRepositoryImpl: PopularShowDetailsRepositoryImpl) : PopularShowDetailsRepository

    //Account Repo & Use Case binding starts below

    @Binds
    abstract fun provideAccountRepository(accountRepositoryImpl: AccountRepositoryImpl) : AccountRepository

    @Binds
    abstract fun provideAccountUseCase(accountUseCaseImpl: GetAccountUseCaseImpl) : GetAccountUseCase

    @Binds
    abstract fun provideGuestUserAuthRepo(guestUserAuthRepositoryImpl: GuestUserAuthRepositoryImpl) : GuestUserAuthRepository

    @Binds
    abstract fun provideAuthenticationUseCase(authenticationUseCaseImpl : AuthenticationUseCaseImpl) : AuthenticationUseCase

    //Login Module for User
    @Binds
    abstract fun provideLoginRepository(loginRepositoryImpl : LoginRepositoryImpl) : LoginRepository

    @Binds
    abstract fun provideLoginUseCase(loginUserUseCaseImpl : LoginUserUseCaseImpl) : LoginUserUseCase

    //User session
    @Binds
    abstract fun provideUserSessionUseCase(getUserSessionUseCaseImpl: GetUserSessionUseCaseImpl) : GetUserSessionUseCase

    @Binds
    abstract fun provideCreateNewSessionUseCase(createNewSessionUseCaseImpl : CreateNewSessionUseCaseImpl) : CreateNewSessionUseCase

}