package `in`.mockapi.dto.axis.getstatus

import com.fasterxml.jackson.annotation.JsonProperty
import `in`.mockapi.dto.axis.beneficiary.SubHeaderDTO

data class TransactionStatusApiResponse(
  @JsonProperty("GetStatusResponse") val getStatusResponse: GetStatusResponseData
)

data class GetStatusResponseData(
  @JsonProperty("SubHeader") val subHeader: SubHeaderDTO,
  @JsonProperty("GetStatusResponseBody") val statusResponseBody: TxnStatusResponseBodyData
)

data class TxnStatusResponseBodyData(
  val data: GetStatusResponseDataData?,
  val message: String,
  val status: String
)

data class GetStatusResponseDataData(
  @JsonProperty("CUR_TXN_ENQ") val curTxnEnq: List<CurTxnEnqData>?,
  val errorMessage: String?,
  val checksum: String
)

data class CurTxnEnqData(
  val corpCode: String?,
  val statusDescription: String?,
  val batchNo: String?,
  val utrNo: String?,
  val processingDate: String?,
  val responseCode: String?,
  val crn: String?,
  val transactionStatus: String?
)
