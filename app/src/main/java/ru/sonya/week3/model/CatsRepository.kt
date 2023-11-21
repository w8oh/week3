package ru.sonya.week3.model

class CatsRepository {
    suspend fun getCats(): Result<List<CatJson>?> {

        val mService = RetrofitCommon.retrofitService
        val response = mService.getCats()

        //так тут надо  как-то кэшировать получается

        return try {
            val body = response.body()
            Result.success(body)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}


