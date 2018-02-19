package br.com.studiotrek.faculdadeimpacta.domain.repository

import org.json.JSONObject
import retrofit2.http.*
import rx.Observable

/**
 * Created by Wilder on 18/02/18.
 */

interface ImpactaApi {

    //TODO: create response class
    @GET("login/{ra}/{password}")
    fun login(@Path("ra") ra: String, @Path("password") password: String): Observable<JSONObject>

    //TODO: Change jsonobject to appropriate classes
    @POST("horario")
    fun getClassesSchedule(@Body body: JSONObject): Observable<JSONObject>

    //TODO: Change jsonobject to appropriate classes
    @POST("quadro-horario")
    fun getStudentInformation(@Body body: String): Observable<JSONObject>

    //TODO: Change jsonobject to appropriate classes
    @POST("semestre-nota")
    fun getCurrentSemesterGrades(@Body body: String): Observable<JSONObject>

}