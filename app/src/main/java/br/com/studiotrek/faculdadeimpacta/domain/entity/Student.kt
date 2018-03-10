package br.com.studiotrek.faculdadeimpacta.domain.entity

/**
 * Created by Wilder on 19/02/18.
 */

data class Student (
        var username: String?,
        var couse: String?,
        val ra: String?,
        var email: String?,
        val password: String
)
