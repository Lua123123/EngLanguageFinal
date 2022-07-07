package com.example.englanguage.network

import com.example.englanguage.model.signup.SignUp
import com.example.englanguage.model.login.UserLogin
import com.example.englanguage.model.login.Login
import com.example.englanguage.model.topic.Topic
import com.example.englanguage.model.vocabulary.Vocabulary
import com.example.englanguage.model.vocabulary.SuccessInsertVocabulary
import com.example.englanguage.model.vocabulary.DeleteVocabulary
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface API {
    //USER
    @FormUrlEncoded
    @POST("auth/signup")
    fun postUsers(
        @Field("email") email: String?,
        @Field("password") password: String?,
        @Field("name") name: String?,
        @Field("password_confirmation") password_confirmation: String?
    ): Call<SignUp?>?

    @POST("auth/login")
    fun getUsers(@Body userLogin: UserLogin?): Call<Login?>?

    //TOPIC
    @FormUrlEncoded
    @POST("topic/getAllTopic")
    fun getTopics(@Field("user_create") user_create: Int): Call<Topic?>?

    //VOCABULARY
    @FormUrlEncoded
    @POST("vocabulary/getVocabulary")
    fun getVocabulary(
        @Field("user_create") user_create: Int,
        @Field("search") search: String?,
        @Field("page") page: String?
    ): Call<Vocabulary?>?

    @FormUrlEncoded
    @POST("vocabulary/getAllVocabularyByUserId")
    fun getVocabularyOfTopic(
        @Field("user_create") user_create: Int,
        @Field("topic_id") topic_id: Int
    ): Call<Vocabulary?>?

    @FormUrlEncoded
    @POST("vocabulary/insertVocabulary")
    fun insertVocabulary(
        @Header("Authorization") Authorization: String?,
        @Field("word") word: String?,
        @Field("mean") mean: String?,
        @Field("image_path") image_path: String?,
        @Field("example") example: String?,
        @Field("id_wordtype") id_wordtype: Int,
        @Field("user_create") user_create: Int
    ): Call<SuccessInsertVocabulary?>?

    @FormUrlEncoded
    @POST("vocabulary/deleteVocabulary")
    fun deleteVocabulary(
        @Header("Authorization") Authorization: String?,
        @Field("user_create") user_create: Int,
        @Field("word") word: String?
    ): Call<DeleteVocabulary?>?

    companion object {
        private const val DOMAIN = "http://192.168.43.130/quanlytuvung/public/api/"
//        private const val DOMAIN = "https://2eae-58-187-123-14.ap.ngrok.io/quanlytuvung/public/api/"
        val api: API = Retrofit.Builder()
            .baseUrl(DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API::class.java)
    }
}