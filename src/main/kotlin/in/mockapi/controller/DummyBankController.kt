package `in`.mockapi.controller

import `in`.mockapi.service.DummyBankService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class DummyBankController(
    private val dummyBankService: DummyBankService
) {

    @PostMapping("/dummyBank/{bank_name}/encryptRequest", consumes = ["application/json"])
    fun encryptRequest(
        @RequestHeader headers: Map<String, String>,
        @PathVariable("bank_name") bankName: String,
        @RequestBody requestPayload: String
    ): ResponseEntity<String> {

        val response = dummyBankService.getEncryptedRequest(bankName, requestPayload)

        return ResponseEntity.ok(response)

    }

}