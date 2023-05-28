package ru.mrmarvel.hellofigma.data.sources

import android.util.Log
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mrmarvel.hellofigma.data.models.Project
import ru.mrmarvel.hellofigma.data.repository.ProjectRepository

class ProjectSource {
    fun getAll(): List<Project> {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://u1988986.isp.regruhosting.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: SQLControl = retrofit.create(SQLControl::class.java)
        // BLOCKING
        val call: Call<JSONObject> = service.sql("SELECT * FROM projects;")
        val userResponse: Response<JSONObject> = call.execute()
        val result = userResponse.body()
        Log.d("MYDUBUG", "Send data to server was: $result")
        return listOf()
    }
}