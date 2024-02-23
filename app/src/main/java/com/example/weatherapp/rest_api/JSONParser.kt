package com.example.weatherapp.rest_api

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import java.net.HttpURLConnection
import java.net.URL


// Permet d'avoir les résultat du webservice
class JSONParser {
    var charset = "UTF-8"
    var conn: HttpURLConnection? = null
    var wr: DataOutputStream? = null
    var result: StringBuilder? = null
    var urlObj: URL? = null
    var jObj: JSONObject? = null
    var sbParams: StringBuilder? = null
    var paramsString: String? = null

    fun JmakeHttpRequest(
        url: String?, method: String,
        params: HashMap<*, *>
    ): JSONObject? {

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var url = url
        sbParams = StringBuilder()
        var i = 0
        for (v in params) {            //(key in params.keys) {
            try {
                if (i != 0) {
                    sbParams!!.append("&")
                }
                sbParams!!.append(v.key).append("=")
                    .append(v.value)        //(URLEncoder.encode(url, charset))
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
            i++
        }
        if (method == "POST") {
            // request method is POST
            try {
                urlObj = URL(url)
                conn = urlObj!!.openConnection() as HttpURLConnection
                conn!!.doOutput = true
                conn!!.requestMethod = "POST"
                conn!!.setRequestProperty("Accept-Charset", charset)
                conn!!.readTimeout = 10000
                conn!!.connectTimeout = 150000
                conn!!.connect()
                paramsString = sbParams.toString()
                wr = DataOutputStream(conn!!.outputStream)
                wr!!.writeBytes(paramsString)
                wr!!.flush()
                wr!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else if (method == "GET") {
            // request method is GET
            if (sbParams!!.length != 0) {
                url += "?" + sbParams.toString()
            }
            try {
                urlObj = URL(url)
                conn = urlObj!!.openConnection() as HttpURLConnection?
                conn!!.doOutput = false
                conn!!.requestMethod = "GET"
                conn!!.setRequestProperty("Accept-Charset", charset)
                conn!!.connectTimeout = 95000
                try {
                    conn!!.connect()
                }catch (e: Exception){
                    e.printStackTrace()
                 }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        try {
            //Receive the response from the server
            val `in`: InputStream = BufferedInputStream(conn!!.inputStream)
            val reader = BufferedReader(InputStreamReader(`in`))
            result = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                result!!.append(line)
            }
            Log.d("JSON Parser", "result: " + result.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        conn!!.disconnect()

        // try parse the string to a JSON object
        try {
            jObj = JSONObject(result!!.substring(result!!.indexOf("{"), result!!.lastIndexOf("}") + 1 ))
           // jObj = JSONObject(result.toString())
        } catch (e: JSONException) {
            Log.e("JSON Parser", "Error parsing data ${e.message}")
        }

        // return JSON Object
        return jObj
    }
}