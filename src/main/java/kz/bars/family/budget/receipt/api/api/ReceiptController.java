package kz.bars.family.budget.receipt.api.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bars.family.budget.receipt.api.dto.ReceiptDto;
import kz.bars.family.budget.receipt.api.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/receipts")
@CrossOrigin
@Log4j2
@PreAuthorize("isAuthenticated()")
@SecurityRequirement(name = "family-budget-receipt-api")
@Tag(name = "Receipt", description = "All methods for getting a list of Receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    @GetMapping(value = "{id}")
    @Operation(description = "Getting a Receipt..")
    public ReceiptDto getReceipt(@Parameter(description = "'receipt' id")
                                 @PathVariable(name = "id") String id) {

        log.debug("!Call method getting a Receipt");
        return receiptService.getReceiptDto(id);

    }

    @PostMapping
    @Operation(description = "Receipt added")
    public ReceiptDto addReceipt(@RequestBody ReceiptDto ReceiptDto) {

        log.debug("!Call method Receipt added");
        return receiptService.addReceiptDto(ReceiptDto);

    }

    @PutMapping
    @Operation(description = "Receipt updated")
    public ReceiptDto updateReceipt(@RequestBody ReceiptDto receiptDto) {

        log.debug("!Call method Receipt updated");
        return receiptService.updateReceiptDto(receiptDto);

    }

    @DeleteMapping(value = "{id}")
    @Operation(description = "Receipt.. removed")
    public String deleteReceipt(@Parameter(description = "'receipt' id")
                                @PathVariable(name = "id") String id) {

        log.debug("!Call method Receipt removed");
        return receiptService.deleteReceiptDto(id);

    }

    @GetMapping()
    @Operation(description = "Getting a list of All Receipts")
    public List<ReceiptDto> getAllReceipt() {

        log.debug("!Call method getting a list of All Receipts");
        return receiptService.getAllReceiptDto();

    }

}
