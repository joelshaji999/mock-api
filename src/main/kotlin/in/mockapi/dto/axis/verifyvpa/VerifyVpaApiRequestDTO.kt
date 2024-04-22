package `in`.mockapi.dto.axis.verifyvpa

import com.fasterxml.jackson.annotation.JsonProperty
import `in`.mockapi.dto.axis.beneficiary.SubHeaderDTO

data class VerifyVPAApiRequestDTO(
  @JsonProperty("VerifyVPARequest") val verifyVPARequest: VerifyVPARequest
)
data class VerifyVPARequest(
  @JsonProperty("SubHeader") val subHeader: SubHeaderDTO,
  @JsonProperty("VerifyVPARequestBody") val verifyVPARequestBody: VerifyVPARequestBody
)

data class VerifyVPARequestBody(
  val merchId: String,
  val merchChanId: String,
  val customerVpa: String,
  val corpCode: String,
  val channelId: String,
  var checksum: String
)
