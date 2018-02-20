package br.com.studiotrek.faculdadeimpacta.domain.entity

/**
 * Created by Wilder on 19/02/18.
 */

data class Student (
        private var username: String?,
        private var couse: String?,
        private val ra: String,
        private var email: String?,
        private val password: String
)
