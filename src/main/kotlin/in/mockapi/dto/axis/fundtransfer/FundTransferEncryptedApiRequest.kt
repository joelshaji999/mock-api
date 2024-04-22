package `in`.mockapi.dto.axis.fundtransfer

import com.fasterxml.jackson.annotation.JsonProperty
import `in`.mockapi.dto.axis.beneficiary.SubHeaderDTO

data class FundTransferEncryptedApiRequest(
  @JsonProperty("TransferPaymentRequest") val transferPaymentRequest: FundTransferEncryptedRequest
)

data class FundTransferEncryptedRequest(
    @JsonProperty("SubHeader") val subHeader: SubHeaderDTO,
    @JsonProperty("TransferPaymentRequestBodyEncrypted") val transferPaymentRequestBody: String
)
