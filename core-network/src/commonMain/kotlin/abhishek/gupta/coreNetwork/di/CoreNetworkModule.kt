package abhishek.gupta.coreNetwork.di

import abhishek.gupta.coreNetwork.apiService.ApiService
import abhishek.gupta.coreNetwork.clieint.KtorClient

import org.koin.dsl.module




    fun getCoreNetworkModule () = module {
        single { ApiService (httpClient = KtorClient.getInstance())}

    }
