package com.bankingapi.baloans.controller;

import com.bankingapi.baloans.constants.LoansConstants;
import com.bankingapi.baloans.dto.ErrorResponseDTO;
import com.bankingapi.baloans.dto.LoansDTO;
import com.bankingapi.baloans.dto.ResponseDTO;
import com.bankingapi.baloans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD REST APIs for loans in Eazy Bank", description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH and DELETE loan details")
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoansController {

    private ILoansService iLoansService;

    @Operation(summary = "Create Loan REST API", description = "REST API to create new loan inside EazyBank")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "HTTP status CREATED"),
        @ApiResponse(responseCode = "500", description = "HTTP Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createLoan(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "MobileNumber must be 10 digits") String mobileNumber) {
        iLoansService.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Loan Details REST API", description = "REST API to fetch loan details based on a mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/fetch")
    public ResponseEntity<LoansDTO> fetchLoanDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "MobileNumber must be 10 digits") String mobileNumber) {
        LoansDTO loansDTO = iLoansService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDTO);
    }

    @Operation(summary = "Update Loan Details REST API", description = "REST API too update loan details based on a loan number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateLoanDetails(@Valid @RequestBody LoansDTO loansDTO) {
        boolean isUpdated = iLoansService.updateLoan(loansDTO);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDTO(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(summary = "Delete Loan Details REST API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "Expectatonn Failed"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteLoansDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "MobileNumber must be 10 digits") String mobileNumber) {
        boolean isDeleted = iLoansService.deleteLoan(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDTO(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }
    }
}
