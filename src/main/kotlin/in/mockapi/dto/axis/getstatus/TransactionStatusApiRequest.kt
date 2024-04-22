package `in`.mockapi.dto.axis.getstatus

import com.fasterxml.jackson.annotation.JsonProperty
import `in`.mockapi.dto.axis.beneficiary.SubHeaderDTO

data class TransactionStatusApiRequest(
  @JsonProperty("GetStatusRequest") val getStatusRequest: StatusCheckApiRequestData
)

data class StatusCheckApiRequestData(
  @JsonProperty("SubHeader") val subHeader: SubHeaderDTO,
  @JsonProperty("GetStatusRequestBody") val statusRequestBody: StatusCheckRequestBody
)

data class StatusCheckRequestBody(
  val channelId: String,
  val corpCode: String,
  val crn: String,
  var checksum: String
)
