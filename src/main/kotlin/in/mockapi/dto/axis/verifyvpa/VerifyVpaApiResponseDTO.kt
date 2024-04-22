package `in`.mockapi.dto.axis.verifyvpa

import com.fasterxml.jackson.annotation.JsonProperty
import `in`.mockapi.dto.axis.beneficiary.SubHeaderDTO

data class VerifyVPAApiResponseDTO(
  @JsonProperty("VerifyVPAResponse") val verifyVPAResponse: VerifyVPAResponse
)
data class VerifyVPAResponse(
  @JsonProperty("SubHeader") val subHeader: SubHeaderDTO,
  @JsonProperty("VerifyVPAResponseBody") val verifyVPAResponseBody: VerifyVPAResponseBody
)

data class VerifyVPAResponseBody(
  val code: String,
  val result: String,
  val customerName: String?,
  val vpa: String?
)
