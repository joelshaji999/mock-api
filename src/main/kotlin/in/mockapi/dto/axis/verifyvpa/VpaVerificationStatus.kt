package `in`.mockapi.dto.axis.verifyvpa

enum class VpaVerificationStatus(val value: String) {
  SUCCESS("success"),
  FAILURE("failure"),
  RETRY("retry")
}
