package `in`.mockapi.factory

import `in`.mockapi.service.encryption.CryptoService
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CryptoFactoryImpl : CryptoFactory {

    private var beanFactory: BeanFactory? = null

    @Autowired
    fun beanFactoryDynamicService(beanFactory: BeanFactory?) {
        this.beanFactory = beanFactory
    }

    override fun getCryptoService(cryptoServiceName: String): CryptoService {

        val service: CryptoService = beanFactory?.getBean(cryptoServiceName, CryptoService::class.java)?:throw Exception("Crypto service not found for $cryptoServiceName")

        return service
    }
}