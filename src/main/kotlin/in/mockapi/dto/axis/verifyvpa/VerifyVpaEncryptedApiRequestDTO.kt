package `in`.mockapi.dto.axis.verifyvpa

import com.fasterxml.jackson.annotation.JsonProperty
import `in`.mockapi.dto.axis.beneficiary.SubHeaderDTO

data class VerifyVpaEncryptedApiRequestDTO(
  @JsonProperty("VerifyVPARequest") val verifyVPARequest: VerifyVPARequestDTO
)

data class VerifyVPARequestDTO(
  @JsonProperty("SubHeader") val subHeader: SubHeaderDTO,
  @JsonProperty("VerifyVPARequestBodyEncrypted") val verifyVPARequestBody: String
)
