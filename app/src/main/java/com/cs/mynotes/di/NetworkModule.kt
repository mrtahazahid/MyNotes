package com.cs.mynotes.di

import com.cs.mynotes.api.AuthInterceptor
import com.cs.mynotes.api.NotesAPI
import com.cs.mynotes.api.UserApi
import com.cs.mynotes.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofitBuilder() : Retrofit.Builder{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor) : OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Singleton
    @Provides
    fun providesUserAPI(retrofitBuilder: Retrofit.Builder) : UserApi{
        return retrofitBuilder.build().create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNodeAPI(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient) : NotesAPI{
        return retrofitBuilder
            .client(okHttpClient)
            .build().create(NotesAPI::class.java)
    }

}