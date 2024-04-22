package `in`.mockapi.dto.icici.fundtransfer

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class NeftPaymentApiRequest(

    @JsonProperty("beneAccNo")
    val beneAccNo: String,
    @JsonProperty("beneIFSC")
    val beneIFSC: String,
    @JsonProperty("amount")
    val amount: String,
    @JsonProperty("tranRefNo")
    val tranRefNo: String,
    @JsonProperty("crpId")
    val crpId: String,
    @JsonProperty("crpUsr")
    val crpUsr: String,
    @JsonProperty("aggrId")
    val aggrId: String,

    @JsonProperty("senderAcctNo")
    val senderAcctNo: String,
    @JsonProperty("beneName")
    val beneName: String,
    @JsonProperty("narration1")
    val narration1: String,
    @JsonProperty("narration2")
    val narration2: String?,
    @JsonProperty("aggrName")
    val aggrName: String,
    @JsonProperty("urn")
    val urn: String,
    @JsonProperty("mobile")
    val mobile: String?,
    @JsonProperty("txnType")
    val txnType: String,
    @JsonProperty("WORKFLOW_REQD")
    val workflowReqd: String?

)
