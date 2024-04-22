package `in`.mockapi.dto.icici.fundtransfer

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ImpsPaymentApiResponse(
    @JsonProperty("success")
    val success: String,
    @JsonProperty("Response")
    val response: String,
    @JsonProperty("ActCode")
    val actCode: String,
    @JsonProperty("TransRefNo")
    val transRefNo: String,
    @JsonProperty("BankRRN")
    val bankRrn: String?,
    @JsonProperty("BeneName")
    val beneName: String?
)
