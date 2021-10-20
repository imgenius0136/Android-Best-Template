package com.luvia.to2youn.network

import android.content.Context
import android.util.Log
import com.luvia.to2youn.common.PreferenceManager
import com.luvia.to2youn.network.model.base.BaseResponse
import com.luvia.to2youn.network.util.HostManager
import com.google.gson.Gson
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


//Http 클라이언트이다.
class HttpClient private constructor(){
    companion object {

        private var host = ""
        private val apiInterface: ApiInterface? = null
        private val gitHubApiInterface: GitHubApiInterface? = null

        fun getClient(context: Context): ApiInterface {

            host = HostManager.getHost(PreferenceManager(context))

            if(apiInterface == null){

                val logging = HttpLoggingInterceptor {
                    Log.d("OkHttp:::", it)
                }

                logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

                val client: OkHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(host)
                    .client(getUnsafeOkHttpClient()!!)
//                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()

                return retrofit.create(ApiInterface::class.java)
            }

            return apiInterface
        }

        fun getGitHupClient(context: Context) : GitHubApiInterface {
            host = HostManager.getGitHubHost(PreferenceManager(context))

            if(gitHubApiInterface == null){
                val logging = HttpLoggingInterceptor {
                    Log.d("OkHttp:::", it)
                }

                logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

                val client: OkHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(host)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()

                return retrofit.create(GitHubApiInterface::class.java)
            }

            return gitHubApiInterface
        }

        fun <T> request(call: Call<T>, callback: HttpCallback<T>){
            call.enqueue(object : Callback<T> {

                override fun onResponse(call: Call<T>, response: Response<T>) {
                   try{
                       if(response.isSuccessful){
                           response.body()?.let {
                               callback.success(it)
                           }
                       }else{
                           callback.fail(Gson().fromJson(response.errorBody()!!.string(), BaseResponse::class.java).message.toString())
                       }
                   }catch (e: Exception) {
                        callback.fail(e.message.toString())
                   }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    callback.fail(t.message.toString())
                }

            })
        }

        // 로컬 http 기반에서 테스트를 하기위해 만들어둔 클라이언트 구성체이다.
        private fun getUnsafeOkHttpClient(): OkHttpClient? {
            return try {

                val logging = HttpLoggingInterceptor {
                    Log.d("OkHttp:::", it)
                }

                logging.setLevel(HttpLoggingInterceptor.Level.BODY)

                val trustManager = object : TrustManager,
                    X509TrustManager {
                    override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {

                    }

                    override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {

                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }

                }

                val trustAllCerts: Array<TrustManager> = arrayOf(trustManager)
                val sslContext: SSLContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())
                val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
                val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(sslSocketFactory, trustManager)
                builder.hostnameVerifier { _, _ -> true }
                builder.addInterceptor(logging)
                builder.build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

    }

}