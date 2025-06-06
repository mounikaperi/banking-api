package com.bankingapi.cards.controller;

import com.bankingapi.cards.cards.CardsConstants;
import com.bankingapi.cards.dto.CardsContactInfoDTO;
import com.bankingapi.cards.dto.CardsDTO;
import com.bankingapi.cards.dto.ErrorResponseDTO;
import com.bankingapi.cards.dto.ResponseDTO;
import com.bankingapi.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CRUD REST APIs for cards in EazyBank", description = "CRUD REST APIs in EazyBank to CREATE, FETCH and DELETE card details")
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardsController {

    private static final Logger logger = LoggerFactory.getLogger(CardsController.class);

    private ICardsService iCardsService;

    public CardsController(ICardsService iCardsService) {
        this.iCardsService = iCardsService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private CardsContactInfoDTO cardsContactInfoDTO;

    @Operation(summary = "Create Card REST API", description = "REST API to create new Card inside EazyBank")
    @ApiResponses({
       @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
       @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
          content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCard(@Valid @RequestBody CardsDTO cardsDTO) {
        iCardsService.createCard(cardsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Card Details REST API", description = "REST API to fetch card details based on mobile number")
    @ApiResponses({
       @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
       @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/fetch")
    public ResponseEntity<List<CardsDTO>> fetchCardDetails(@RequestHeader("eazybank-correlation-id") String correlationId, @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
        logger.debug("fetchCardDetails invoked -start");
        List<CardsDTO> cardsDTO = iCardsService.fetchCardDetails(mobileNumber);
        if (cardsDTO.isEmpty()) {
            CardsDTO cardsDTO1 = new CardsDTO();
            cardsDTO1.setMessage("Customer has neither credit card nor debit card");
            List<CardsDTO> cardsDTOS = List.of(cardsDTO1);
            return ResponseEntity.status(HttpStatus.OK).body(cardsDTOS);
        }
        logger.debug("fetchCardDetails invoked -end");
        return ResponseEntity.status(HttpStatus.OK).body(cardsDTO);
    }

    @Operation(summary = "Update Card Details REST API", description = "REST API to update card details based on a card number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "HTTp Status Internal Server Error",
                content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCardDetails(@Valid @RequestBody CardsDTO cardsDTO) {
        boolean isUpdated = iCardsService.updateCard(cardsDTO);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDTO(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
    }

    @Operation(summary = "Get Build Information", description = "Get Build Information that is deployed into cards microservice")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @Operation(summary = "Get Java Version", description = "Get Java Version Details that is installed into cards microservice")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(summary = "Get Contact Info", description = "Contact Info details that can be reached out in case of any issues")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactInfoDTO> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(cardsContactInfoDTO);
    }
}
