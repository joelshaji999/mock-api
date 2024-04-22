package `in`.mockapi.dto.icici.fundtransfer

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class RTGSPaymentBankAPIResponse(

  @JsonProperty("CORP_ID")
  val corpId: String?,

  @JsonProperty("USER_ID")
  val userId: String?,

  @JsonProperty("AGGR_ID")
  val aggrId: String?,

  @JsonProperty("AGGR_NAME")
  val aggrName: String?,

  @JsonProperty("RESPONSE")
  val response: String? = null,

  @JsonProperty("URN")
  val urn: String? = null,

  @JsonProperty("STATUS")
  val status: String? = null,

  @JsonProperty("UNIQUEID")
  val uniqueId: String? = null,

  @JsonProperty("REQID")
  val reqId: String? = null,

  @JsonProperty("UTRNUMBER")
  val utr: String? = null,

  @JsonProperty("RESPONSECODE")
  val responseCode: String? = null,

  @JsonProperty("MESSAGE")
  val message: String? = null,

  @JsonProperty("ERRORCODE")
  val errorCode: String? = null,

  @JsonProperty("DESCRIPTION")
  val description: String? = null
)
