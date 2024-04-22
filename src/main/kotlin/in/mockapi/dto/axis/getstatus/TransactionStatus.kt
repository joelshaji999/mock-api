package `in`.mockapi.dto.axis.getstatus

enum class TransactionStatus(val value: String) {
  PENDING("pending"),
  PROCESSED("processed"),
  REJECTED("rejected"),
  RETURN("return")
}
