package com.imoviedb.app.data.di

import com.imoviedb.app.data.repository.account.AccountRepositoryImpl
import com.imoviedb.app.data.repository.authentication.guestuser.GuestUserAuthRepositoryImpl
import com.imoviedb.app.data.repository.authentication.normaluser.LoginRepositoryImpl
import com.imoviedb.app.data.repository.popularshows.details.PopularShowDetailsRepositoryImpl
import com.imoviedb.app.data.repository.popularshows.showslist.PopularShowsRepositoryImpl
import com.imoviedb.app.domain.authentication.guestuser.usecase.DeleteGuestTokenUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class DomainModule {

    @Binds
    abstract fun providePopularShowRepo(popularShowsRepositoryImpl: PopularShowsRepositoryImpl): com.imoviedb.app.domain.popularshows.showslist.repository.PopularShowsRepository

    @Binds
    abstract fun providePopularShowUseCase(popularShowsUseCaseImpl: com.imoviedb.app.domain.popularshows.showslist.usecase.PopularShowsUseCaseImpl): com.imoviedb.app.domain.popularshows.showslist.usecase.PopularShowsUseCase

    @Binds
    abstract fun provideGetPopularShowDetailsUseCase(popularShowsDetailsUseCase: com.imoviedb.app.domain.popularshows.details.usecase.GetPopularShowDetailsUseCaseImpl): com.imoviedb.app.domain.popularshows.details.usecase.GetPopularShowDetailsUseCase


    @Binds
    abstract fun providePopularShowDetailsRepository(popularShowDetailsRepositoryImpl: PopularShowDetailsRepositoryImpl): com.imoviedb.app.domain.popularshows.details.repository.PopularShowDetailsRepository

    //Account Repo & Use Case binding starts below

    @Binds
    abstract fun provideAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): com.imoviedb.app.domain.account.repository.AccountRepository

    @Binds
    abstract fun provideAccountUseCase(accountUseCaseImpl: com.imoviedb.app.domain.account.usecase.GetAccountUseCaseImpl): com.imoviedb.app.domain.account.usecase.GetAccountUseCase

    @Binds
    abstract fun provideGuestUserAuthRepo(guestUserAuthRepositoryImpl: GuestUserAuthRepositoryImpl): com.imoviedb.app.domain.authentication.guestuser.repository.GuestUserAuthRepository

    @Binds
    abstract fun provideAuthenticationUseCase(authenticationUseCaseImpl: com.imoviedb.app.domain.authentication.guestuser.usecase.AuthenticationUseCaseImpl): com.imoviedb.app.domain.authentication.guestuser.usecase.AuthenticationUseCase

    //Login Module for User
    @Binds
    abstract fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): com.imoviedb.app.domain.authentication.normaluser.repository.LoginRepository

    @Binds
    abstract fun provideLoginUseCase(loginUserUseCaseImpl: com.imoviedb.app.domain.authentication.normaluser.usecase.LoginUserUseCaseImpl): com.imoviedb.app.domain.authentication.normaluser.usecase.LoginUserUseCase

    //User session
    @Binds
    abstract fun provideUserSessionUseCase(getUserSessionUseCaseImpl: com.imoviedb.app.domain.authentication.normaluser.usecase.GetUserSessionUseCaseImpl): com.imoviedb.app.domain.authentication.normaluser.usecase.GetUserSessionUseCase

    @Binds
    abstract fun provideCreateNewSessionUseCase(createNewSessionUseCaseImpl: com.imoviedb.app.domain.authentication.normaluser.usecase.CreateNewSessionUseCaseImpl): com.imoviedb.app.domain.authentication.normaluser.usecase.CreateNewSessionUseCase

    @Binds
    abstract fun provideDeleteSessionUseCase(deleteGuestTokenUseCaseImpl: DeleteGuestTokenUseCaseImpl): com.imoviedb.app.domain.authentication.guestuser.usecase.DeleteGuestTokenUseCase

}