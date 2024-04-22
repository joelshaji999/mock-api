package `in`.mockapi.dto.icici.fundtransfer

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class UPIPaymentBankApiResponse(
  @JsonProperty("success")
  val success: Boolean,

  @JsonProperty("response")
  var response: String? = null,

  @JsonProperty("errorCode")
  private var errorCode: Int,

  @JsonProperty("description")
  private val description: String? = null,

  @JsonProperty("message")
  var message: String? = null,

  @JsonProperty("BankRRN")
  var bankRRN: String? = null,

  @JsonProperty("UpiTranlogId")
  var upiTranLogId: String? = null,

  @JsonProperty("UserProfile")
  var userProfile: String? = null,

  @JsonProperty("SeqNo")
  var seqNo: String? = null,

  @JsonProperty("MobileAppData")
  var mobileAppData: String? = null,

  @JsonProperty("PayerRespCode")
  var payerRespCode: String? = null,

  @JsonProperty("PayeeRespCode")
  var payeeRespCode: String? = null
)
