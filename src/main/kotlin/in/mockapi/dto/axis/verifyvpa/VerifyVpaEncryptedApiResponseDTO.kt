package `in`.mockapi.dto.axis.verifyvpa

import com.fasterxml.jackson.annotation.JsonProperty
import `in`.mockapi.dto.axis.beneficiary.SubHeaderDTO

data class VerifyVpaEncryptedApiResponseDTO(
  @JsonProperty("VerifyVPAResponse") val verifyVPAResponse: VerifyVPAResponseDTO
)

data class VerifyVPAResponseDTO(
  @JsonProperty("SubHeader") val subHeader: SubHeaderDTO,
  @JsonProperty("VerifyVPAResponseBodyEncrypted") val verifyVPAResponseBody: String
)
