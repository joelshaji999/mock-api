package `in`.mockapi.factory

import `in`.mockapi.service.encryption.CryptoService

interface CryptoFactory {

    fun getCryptoService(cryptoServiceName: String): CryptoService
}