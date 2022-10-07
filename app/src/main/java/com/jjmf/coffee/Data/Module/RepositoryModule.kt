package com.jjmf.coffee.Data.Module

import com.jjmf.coffee.Data.Repository.UsuarioRepository
import com.jjmf.coffee.Data.Repository.UsuarioRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun usuarioRepo(repo : UsuarioRepositoryImpl) : UsuarioRepository

}