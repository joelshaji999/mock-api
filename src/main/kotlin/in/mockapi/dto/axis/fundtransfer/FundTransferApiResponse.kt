package `in`.mockapi.dto.axis.fundtransfer

import com.fasterxml.jackson.annotation.JsonProperty
import `in`.mockapi.dto.axis.beneficiary.SubHeaderDTO

data class FundTransferApiResponse(
    @JsonProperty("SubHeader") val subHeader: SubHeaderDTO,
    @JsonProperty("TransferPaymentResponse") val transferPaymentResponse: TransferPaymentResponseData
)

data class TransferPaymentResponseData(
  val data: String,
  val message: String,
  val status: String
)
