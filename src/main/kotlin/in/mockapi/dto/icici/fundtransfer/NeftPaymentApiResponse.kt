package `in`.mockapi.dto.icici.fundtransfer

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class NeftPaymentApiResponse(

  @JsonProperty("CORP_ID")
  val corpId: String?,
  @JsonProperty("USER_ID")
  val userId: String?,
  @JsonProperty("AGGR_ID")
  val aggrId: String?,
  @JsonProperty("AGGR_NAME")
  val aggrName: String?,
  @JsonProperty("REQID")
  val reqId: String?,
  @JsonProperty("STATUS")
  val status: String?,
  @JsonProperty("UNIQUEID")
  val uniqueId: String?,
  @JsonProperty("URN")
  val urn: String?,
  @JsonProperty("UTRNUMBER")
  val utrNumber: String?,
  @JsonProperty("RESPONSE")
  val response: String,
  @JsonProperty("MESSAGE")
  val message: String?,
  @JsonProperty("ERRORCODE")
  val errorCode: String?,
  @JsonProperty("RESPONSECODE")
  val responseCode: String?,
  @JsonProperty("DESCRIPTION")
  val description: String?

)
