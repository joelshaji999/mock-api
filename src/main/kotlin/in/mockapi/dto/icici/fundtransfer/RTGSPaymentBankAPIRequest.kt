package `in`.mockapi.dto.icici.fundtransfer

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

data class RTGSPaymentBankAPIRequest(
  @JsonProperty("AGGRID")
  private var aggrId: String,

  @JsonProperty("CORPID")
  private val corpId: String,

  @JsonProperty("USERID")
  private val userId: String,

  @JsonProperty("URN")
  private val urn: String,

  @JsonProperty("AGGRNAME")
  private val aggrName: String,

  @JsonProperty("UNIQUEID")
  private val uniqueId: String,

  @JsonProperty("DEBITACC")
  private val debitAcc: String,

  @JsonProperty("CREDITACC")
  private val creditAcc: String,

  @JsonProperty("IFSC")
  private val ifsc: String,

  @JsonProperty("AMOUNT")
  private val amount: String,

  @JsonProperty("CURRENCY")
  private val currency: String,

  @JsonProperty("TXNTYPE")
  private val txnType: String,

  @JsonProperty("PAYEENAME")
  private val payeeName: String,

  @JsonProperty("REMARKS")
  private val remarks: String,

  @JsonProperty("WORKFLOW_REQD")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  var workflowReqd: String = ""
)
