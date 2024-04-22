package `in`.mockapi.dto.axis.getstatus

import com.fasterxml.jackson.annotation.JsonProperty
import `in`.mockapi.dto.axis.beneficiary.SubHeaderDTO

data class TransactionStatusEncryptedApiResponse(
  @JsonProperty("GetStatusResponse") val getStatusResponse: GetStatusEncryptedResponseData
)

data class GetStatusEncryptedResponseData(
  @JsonProperty("SubHeader") val subHeader: SubHeaderDTO,
  @JsonProperty("GetStatusResponseBodyEncrypted") val statusResponseBody: String
)
