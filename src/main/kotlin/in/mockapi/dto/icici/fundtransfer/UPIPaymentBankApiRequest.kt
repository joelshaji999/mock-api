package `in`.mockapi.dto.icici.fundtransfer

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class UPIPaymentBankApiRequest(
  @JsonProperty("mobile")
  val mobile: String,

  @JsonProperty("device-id")
  val deviceId: String,

  @JsonProperty("seq-no")
  val seqNo: String,

  @JsonProperty("payee-va")
  val payeeVa: String,

  @JsonProperty("payer-va")
  val payerVa: String,

  @JsonProperty("profile-id")
  val profileId: String,

  @JsonProperty("amount")
  val amount: String,

  @JsonProperty("pre-approved")
  val preApproved: String,

  @JsonProperty("use-default-acc")
  val useDefaultAcc: String,

  @JsonProperty("default-debit")
  val defaultDebit: String,

  @JsonProperty("default-credit")
  val defaultCredit: String,

  @JsonProperty("payee-name")
  val payeeName: String,

  @JsonProperty("mcc")
  val mcc: String,

  @JsonProperty("merchant-type")
  val merchantType: String,

  @JsonProperty("txn-type")
  val txnType: String,

  @JsonProperty("channel-code")
  val channelCode: String,

  @JsonProperty("remarks")
  val remarks: String,

  @JsonProperty("account-provider")
  val accountProvider: String? = "",

  @JsonProperty("account-number")
  val accountNumber: String? = "",

  @JsonProperty("account-type")
  val accountType: String? = "",

  @JsonProperty("ref-id")
  val refId: String? = "",
)
