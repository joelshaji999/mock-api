package `in`.mockapi.controller

import `in`.mockapi.dto.AddNewRequestDTO
import `in`.mockapi.dto.AddNewResponseConditionRequestDTO
import `in`.mockapi.service.DummyApiService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:3000"], maxAge = 3600)
@RestController
class MockApiController(
    private val dummyApiService: DummyApiService
) {

    @PostMapping(
        "/dummy_bank/add_new",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun addNew(
        @RequestBody requestPayload: AddNewRequestDTO
    ): ResponseEntity<String> {

        val response = dummyApiService.addNewDummyApiName(requestPayload)

        return ResponseEntity.ok(response)

    }

    @PostMapping(
        "/dummy_bank/{name}/add_new_response",
        consumes = ["application/json"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun addNewResponseCondition(
        @PathVariable("name") name: String,
        @RequestBody requestPayload: AddNewResponseConditionRequestDTO
    ): ResponseEntity<String> {

        val response = dummyApiService.addNewResponseCondition(name, requestPayload)

        return ResponseEntity.ok(response)
    }

    @PostMapping("/dummy_bank/{name}", consumes = ["application/json"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getResponseForName(
        @PathVariable("name") name: String,
        @RequestBody(required = false) requestPayload: Map<String, Any>?
    ): ResponseEntity<String> {

        val response =
            if (requestPayload.isNullOrEmpty()) {
                val payload = mapOf<String, Any>()
                dummyApiService.getResponse(name, payload)
            } else {
                dummyApiService.getResponse(name, requestPayload)
            }

        return ResponseEntity.ok(response)
    }

    @GetMapping("/dummy_bank/list", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getListOfAllNames(): ResponseEntity<String> {

        val response = dummyApiService.listOfAllNames()

        return ResponseEntity.ok(response)
    }

    @GetMapping("/dummy_bank/{name}/list", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getListForName(
        @PathVariable("name") name: String,
    ): ResponseEntity<String> {

        val response = dummyApiService.listAllForName(name)

        return ResponseEntity.ok(response)
    }

    @PostMapping("/dummy_bank/{name}/delete", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteApiNameCompleted(
        @PathVariable("name") name: String,
    ): ResponseEntity<String> {

        val response = dummyApiService.deleteApiNameAndAllConditions(name)

        return ResponseEntity.ok(response)
    }

    @PostMapping("/dummy_bank/{name}/{condition_identifier}/delete", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteApiNameCompleted(
        @PathVariable("name") name: String,
        @PathVariable("condition_identifier") conditionIdentifier: String,
    ): ResponseEntity<String> {

        val response = dummyApiService.deleteConditionCombinationForApiName(name, conditionIdentifier)

        return ResponseEntity.ok(response)
    }




}