package `in`.mockapi.dto.axis.beneficiary

import com.fasterxml.jackson.annotation.JsonProperty

data class BeneRegApiRequestDTO(
    @JsonProperty("BeneficiaryRegistrationRequest") val beneRegRequest: BeneRegRequestDTO
)

data class BeneRegRequestDTO(
    @JsonProperty("SubHeader") val subHeader: SubHeaderDTO,
    @JsonProperty("BeneficiaryRegistrationRequestBody") val beneRegRequestBody: BeneRegRequestBodyDTO
)

data class BeneRegRequestBodyDTO(
    @JsonProperty("channelId") val channelId: String,
    @JsonProperty("corpCode") val corpCode: String,
    @JsonProperty("userId") val userId: String,
    @JsonProperty("beneinsert") val beneinsert: List<BeneRegDetailsDTO>?,
    @JsonProperty("checksum") var checksum: String
)

data class BeneRegDetailsDTO(
    @JsonProperty("apiVersion") val apiVersion: String,
    @JsonProperty("beneLEI") val beneLEI: String?,
    @JsonProperty("beneCode") val beneCode: String,
    @JsonProperty("beneName") val beneName: String,
    @JsonProperty("beneAccNum") val beneAccNum: String,
    @JsonProperty("beneIfscCode") val beneIfscCode: String?,
    @JsonProperty("beneAcType") val beneAcType: String?,
    @JsonProperty("beneBankName") val beneBankName: String?,
    @JsonProperty("beneAddr1") val beneAddr1: String?,
    @JsonProperty("beneAddr2") val beneAddr2: String?,
    @JsonProperty("beneAddr3") val beneAddr3: String?,
    @JsonProperty("beneCity") val beneCity: String?,
    @JsonProperty("beneState") val beneState: String?,
    @JsonProperty("benePincode") val benePincode: String?,
    @JsonProperty("beneEmailAddr1") val beneEmailAddr1: String?,
    @JsonProperty("beneMobileNo") val beneMobileNo: String?
)
