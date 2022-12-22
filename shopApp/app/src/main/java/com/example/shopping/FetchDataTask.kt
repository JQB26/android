package com.example.shopping

import android.os.AsyncTask
import java.net.HttpURLConnection
import java.net.URL

class FetchDataTask : AsyncTask<URL, Void, String>() {
    override fun doInBackground(vararg params: URL): String {
        val url = params[0]
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        val inputStream = connection.inputStream
        return inputStream.bufferedReader().readText()
    }

    override fun onPostExecute(result: String) {
        // Update the UI with the fetched data
    }
}