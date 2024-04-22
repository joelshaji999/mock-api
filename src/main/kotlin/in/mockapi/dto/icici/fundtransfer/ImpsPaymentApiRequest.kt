package `in`.mockapi.dto.icici.fundtransfer

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ImpsPaymentApiRequest(
    val beneAccNo: String,
    val beneIFSC: String,
    val amount: String,
    val tranRefNo: String,
    val crpId: String? = null,
    val crpUsr: String? = null,
    val aggrId: String,

    val localTxnDtTime: String,
    val paymentRef: String,
    val senderName: String,
    val mobile: String,
    val retailerCode: String,
    val passCode: String,
    val bcID: String
)
