package com.omise.charity.network.interceptors

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject


class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        try {
            val jsonObject = JSONObject()
            jsonObject.put("status_code", response.code())
            jsonObject.put("response_data", JSONObject(response.body()!!.string()))
            val contentType: MediaType? = response.body()!!.contentType()
            val body = ResponseBody.create(contentType, jsonObject.toString())
            return response.newBuilder().body(body).build()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return response
    }
}