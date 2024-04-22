package `in`.mockapi.dto.axis.beneficiary

import com.fasterxml.jackson.annotation.JsonProperty

data class BeneRegEncryptedApiRequestDTO(
  @JsonProperty("BeneficiaryRegistrationRequest") val beneRegRequest: BeneRegEncryptedRequestDTO
)

data class BeneRegEncryptedRequestDTO(
    @JsonProperty("SubHeader") val subHeader: SubHeaderDTO,
    @JsonProperty("BeneficiaryRegistrationRequestBodyEncrypted") val beneRegRequestBody: String
)
