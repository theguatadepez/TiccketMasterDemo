package com.example.ticketmasterdemochallenge.domain.network

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.lang.StringBuilder
import java.net.UnknownHostException

/**
 * [SafeAPIRequest] is an abstract class that allows to do API Requests safety.
 * */
abstract class SafeAPIRequest {

    /**
     * [apiRequest] is a higher level, suspend function that allows to do Api Calls Safety,
     * is a wrapper that returns either the response or an [ApiException].
     * @param call: is an input function, represents a API call.
     * */
    suspend fun<T:Any?> apiRequest(call: suspend () -> Response<T>) : T {
        try {
            val response = call.invoke()
            if(response.isSuccessful){
                return response.body()!!
            }else{
                val error = response.errorBody()?.toString()

                val message = StringBuilder()

                error?.let {
                    try {
                        message.append(JSONObject(it).getString("message"))
                    }catch (e: JSONException){
                        message.append("\n")
                    }
                }
                message.append("Error Code: ${response.code()}")
                message.append(", Error Description: ${response.errorBody()}")
                throw ApiException(message.toString())
            }
        } catch (unknownHostException: UnknownHostException) {
            throw ApiException("No internet connection")
        }
    }
}

//custom API Exceptions
class ApiException(message:String): IOException(message)