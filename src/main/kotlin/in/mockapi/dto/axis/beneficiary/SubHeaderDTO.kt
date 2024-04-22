package `in`.mockapi.dto.axis.beneficiary

import com.fasterxml.jackson.annotation.JsonProperty

data class SubHeaderDTO(

  @JsonProperty("requestUUID") val requestUUID: String,
  @JsonProperty("serviceRequestId") val serviceRequestId: String,
  @JsonProperty("serviceRequestVersion") val serviceRequestVersion: String,
  @JsonProperty("channelId") val channelId: String

)
