package `in`.mockapi.dto.axis.getstatus

import com.fasterxml.jackson.annotation.JsonProperty
import `in`.mockapi.dto.axis.beneficiary.SubHeaderDTO

data class TransactionStatusEncryptedApiRequest(
  @JsonProperty("GetStatusRequest") val getStatusRequest: TxnStatusApiEncryptedRequestData
)

data class TxnStatusApiEncryptedRequestData(
  @JsonProperty("SubHeader") val subHeader: SubHeaderDTO,
  @JsonProperty("GetStatusRequestBodyEncrypted") val statusRequestBody: String
)
