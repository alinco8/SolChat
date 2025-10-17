package dev.alinco8.solchat.japanize

import com.google.gson.Gson
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

/**
 * @author ucchy
 */
class GoogleCGI {
    companion object {
        const val GOOGLE_IME_URL: String =
            "https://www.google.com/transliterate?langpair=ja-Hira%7Cja&text="

        fun convert(input: String): String {
            val client = HttpClient.newHttpClient()
            val req = HttpRequest.newBuilder()
                .uri(URI.create(GOOGLE_IME_URL + URLEncoder.encode(input, "UTF-8")))
                .GET()
                .build()
            try {
                val res = client.send(req, HttpResponse.BodyHandlers.ofString())
                val body = res.body()

                return parseResponse(body)
            } catch (e: Exception) {
                e.printStackTrace()
                return input
            }
        }

        fun parseResponse(response: String): String {
            val out = StringBuilder()
            val json = Gson().fromJson(response, Array<Array<Any>>::class.java)

            for (entry in json) {
                val candidates = entry[1] as List<*>
                if (candidates.isNotEmpty()) {
                    out.append(candidates[0].toString())
                } else {
                    out.append(entry[0].toString())
                }
            }

            return out.toString()
        }
    }
}