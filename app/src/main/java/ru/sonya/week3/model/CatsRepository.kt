package ru.sonya.week3.model

import android.content.Context
import android.widget.Toast

class CatsRepository {
    suspend fun getCats(context: Context): List<CatJson>? {

        val _context = context
        val mService = RetrofitCommon.retrofitService
        val response = mService.getCats()

        try {
            if (response.isSuccessful && response.body() != null) {
               return response.body()
            } else {
                Toast.makeText(
                    _context,
                    "Error Occurred: ${response.message()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception) {
            Toast.makeText(
                _context,
                "Error Occurred: ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }
        return response.body()
    }
}


