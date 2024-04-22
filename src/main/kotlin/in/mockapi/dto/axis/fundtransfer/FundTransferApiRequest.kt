package `in`.mockapi.dto.axis.fundtransfer

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import `in`.mockapi.dto.axis.beneficiary.SubHeaderDTO

data class FundTransferApiRequest(
  @JsonProperty("TransferPaymentRequest") val fundTransferRequest: FundTransferRequest
)

data class FundTransferRequest(
    @JsonProperty("SubHeader") val subHeader: SubHeaderDTO,
    @JsonProperty("TransferPaymentRequestBody") val transferPaymentRequestBody: TransferPaymentRequestBody
)

data class TransferPaymentRequestBody(
  val channelId: String,
  val corpCode: String,
  val paymentDetails: List<PaymentDetail>?,
  var checksum: String
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PaymentDetail(
  val txnPaymode: String,
  val custUniqRef: String,
  val corpAccNum: String,
  val valueDate: String,
  val txnAmount: String,
  val beneLEI: String?,
  val beneName: String?,
  val beneCode: String,
  val beneAccNum: String?,
  val beneAcType: String?,
  val beneAddr1: String?,
  val beneAddr2: String?,
  val beneAddr3: String?,
  val beneCity: String?,
  val beneState: String?,
  val benePincode: String?,
  val beneIfscCode: String?,
  val beneBankName: String?,
  val chequeNumber: String?,
  val chequeDate: String?,
  val payableLocation: String?,
  val printLocation: String?,
  val beneEmailAddr1: String?,
  val beneMobileNo: String?,
  val productCode: String?,
  val txnType: String?,
  val invoiceDetails: List<InvoiceDetail>?,
  val enrichment1: String?,
  val enrichment2: String?,
  val enrichment3: String?,
  val enrichment4: String?,
  val enrichment5: String?,
  val senderToReceiverInfo: String?,
  val beneRegFlg: String?
)

data class InvoiceDetail(
  val invoiceAmount: String?,
  val invoiceNumber: String?,
  val invoiceDate: String?,
  val cashDiscount: String?,
  val tax: String?,
  val netAmount: String?
)
