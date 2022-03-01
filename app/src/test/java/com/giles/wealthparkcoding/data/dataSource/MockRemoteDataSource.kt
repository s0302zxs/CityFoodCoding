package com.giles.wealthparkcoding.data.dataSource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.giles.wealthparkcoding.api.ApiService
import com.giles.wealthparkcoding.api.converter.defaultGsonConverterFactory
import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food
import com.giles.wealthparkcoding.api.adapter.FlowCallAdapterFactory
import kotlinx.coroutines.flow.Flow
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.Rule
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

class MockRemoteDataSource : RemoteDataSource {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    //init apiService
    private lateinit var apiService: ApiService

    private lateinit var mockWebServer: MockWebServer

    var loadApiResponseFile : String = ""

    fun init(){
        mockWebServer = MockWebServer()
        val gsonConverter = defaultGsonConverterFactory
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(gsonConverter)
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .build()
            .create(ApiService::class.java)

    }

    override fun getCities(token: String): Flow<Response<List<City>>> {
        enqueueResponse(loadApiResponseFile)
        return apiService.getCities("city")
    }

    override fun getFoods(token: String): Flow<Response<List<Food>>> {
        enqueueResponse(loadApiResponseFile)
        return apiService.getFoods("food")
    }


    @Throws(IOException::class)
    private fun enqueueResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse().setBody(source.readUtf8())
        mockWebServer.enqueue(mockResponse)
    }


}