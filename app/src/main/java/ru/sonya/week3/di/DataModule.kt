package ru.sonya.week3.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.http.GET
import ru.sonya.week3.data.CatsRepository
import ru.sonya.week3.data.Repository
import ru.sonya.week3.data.retrofit2.CatJson
import ru.sonya.week3.data.retrofit2.RetrofitCommon
import ru.sonya.week3.data.retrofit2.RetrofitServices
import ru.sonya.week3.data.roomDB.AppDatabase
import ru.sonya.week3.data.roomDB.CatsDAO
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun bindRepository(
        catsRepository: CatsRepository,
    ): Repository
}

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "cats-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("data", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideDao(db: AppDatabase): CatsDAO {
        return db.catsDao()
    }

    @Provides
    @Singleton
    fun provideApi(): RetrofitServices {
        return RetrofitCommon().retrofitService
    }

}