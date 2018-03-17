package br.com.studiotrek.faculdadeimpacta.dagger.module.network

import br.com.studiotrek.faculdadeimpacta.domain.repository.ImpactaApi

class ApiServiceHolder {

    private var apiService: ImpactaApi? = null

    fun get() : ImpactaApi? {
        return apiService
    }

    fun  set(apiService: ImpactaApi) {
        this.apiService = apiService
    }

}
