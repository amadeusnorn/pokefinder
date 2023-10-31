package com.example.pokefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    var pokeImageURL = ""
    private lateinit var pokeList: MutableList<String>
    private lateinit var rvPoke: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPoke = findViewById(R.id.poke_list)
        pokeList = mutableListOf()

        getPokeImageURL()

        Log.d("pokeimageURL", "poke dataset")


    }


    private fun getPokeImageURL() {
        val client = AsyncHttpClient()
        client["https://pokeapi.co/api/v2/pokemon/", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                pokeImageURL = json.jsonObject.getString("results")
                val pokeImageArray = json.jsonObject.getJSONArray("results")
                for (i in 0 until pokeImageArray.length()) {
                    pokeList.add(pokeImageArray.getString(i))
                }
                val adapter = pokeAdapter(pokeList)
                rvPoke.adapter = adapter
                rvPoke.layoutManager = LinearLayoutManager(this@MainActivity)

                Log.d("Pokemon", "response successful$json")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Pokemon Error", errorResponse)
            }


        }]
    }
}