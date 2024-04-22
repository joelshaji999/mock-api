package `in`.mockapi.dto.axis.beneficiary

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class BeneRegApiResponseDTO(
  @JsonProperty("BeneficiaryRegistrationResponse") val beneRegResponse: BeneRegResponseDTO
)

data class BeneRegResponseDTO(
  @JsonProperty("SubHeader") val subHeader: SubHeaderDTO,
  @JsonProperty("BeneficiaryRegistrationResponseBody") val beneRegResponseBody: BeneRegApiResponseBodyDTO
)

data class BeneRegApiResponseBodyDTO(
    val status: String,
  val data: BeneRegDataDTO?,
    val message: String
)

data class BeneRegDataDTO(
    val beneDetails: List<BeneRegDetailsResponseDTO>?,
    val checksum: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class BeneRegDetailsResponseDTO(
  val beneMobileNo: String?,
  val beneBankName: String?,
  val beneName: String?,
  val beneIfscCode: String?,
  val beneCode: String?,
  val beneEmailAddr1: String?,
  val corpCode: String?,
  val beneAccNum: String?,
  val status: String?
)
