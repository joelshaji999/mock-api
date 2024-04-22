package `in`.mockapi.service.bank

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import `in`.mockapi.dto.axis.beneficiary.*
import `in`.mockapi.service.encryption.AxisCryptoServiceImpl
import org.springframework.stereotype.Service

@Service
class AxisBankServiceImpl(
    private val axisCryptoServiceImpl: AxisCryptoServiceImpl
) : BankService {

    override fun getEncryptedRequest(bankName: String, requestPayload: String): String {

        val parsedRequestPayload =
            jacksonObjectMapper().readValue(requestPayload, object : TypeReference<BeneRegApiRequestDTO>() {})


        val requestData = axisCryptoServiceImpl.getEncryptedData(
            jacksonObjectMapper().writeValueAsString(
                parsedRequestPayload.beneRegRequest.beneRegRequestBody
            ),
            null
        )

        return jacksonObjectMapper().writeValueAsString(
            BeneRegEncryptedApiRequestDTO(
                beneRegRequest = BeneRegEncryptedRequestDTO(
                    subHeader = parsedRequestPayload.beneRegRequest.subHeader,
                    beneRegRequestBody = requestData
                )
            )
        )
    }
}