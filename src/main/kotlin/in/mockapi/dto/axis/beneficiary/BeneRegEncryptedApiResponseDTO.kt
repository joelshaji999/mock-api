package `in`.mockapi.dto.axis.beneficiary

import com.fasterxml.jackson.annotation.JsonProperty

data class BeneRegEncryptedApiResponseDTO(
  @JsonProperty("BeneficiaryRegistrationResponse") val beneRegResponse: BeneRegEncryptedResponseDTO
)

data class BeneRegEncryptedResponseDTO(
  @JsonProperty("SubHeader") val subHeader: SubHeaderDTO,
  @JsonProperty("BeneficiaryRegistrationResponseBodyEncrypted") val beneRegResponseBody: String
)
