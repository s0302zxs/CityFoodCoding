package com.giles.wealthparkcoding.api.converter

import com.giles.wealthparkcoding.api.defaultGson
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

val defaultGsonConverterFactory: Converter.Factory by lazy {
    return@lazy Factory()

}

class Factory : Converter.Factory() {
    private val delegate = GsonConverterFactory.create(defaultGson)

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        return delegate.requestBodyConverter(
            type,
            parameterAnnotations,
            methodAnnotations,
            retrofit
        )
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val gsonResponseBodyConverter =
            delegate.responseBodyConverter(type, annotations, retrofit) ?: return null
        return ResponseBodyConverter(gsonResponseBodyConverter)
    }
}

class ResponseBodyConverter(private val gsonResponseBodyConverter: Converter<ResponseBody, *>) :
    Converter<ResponseBody, Any> {

    override fun convert(value: ResponseBody): Any? {
        return gsonResponseBodyConverter.convert(value)
    }
}
