package `in`.mockapi.dto.axis.fundtransfer

import com.fasterxml.jackson.annotation.JsonProperty
import `in`.mockapi.dto.axis.beneficiary.SubHeaderDTO

data class FundTransferEncryptedApiResponse(
  @JsonProperty("TransferPaymentResponse") val transferPaymentResponse: TransferPaymentEncryptedResponseData
)

data class TransferPaymentEncryptedResponseData(
  @JsonProperty("SubHeader") val subHeader: SubHeaderDTO,
  @JsonProperty("TransferPaymentResponseBodyEncrypted") val transferPaymentResponseBody: String
)
