package kz.bars.family.budget.receipt.api.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bars.family.budget.receipt.api.dto.ReceiptDto;
import kz.bars.family.budget.receipt.api.response.MessageResponse;
import kz.bars.family.budget.receipt.api.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @PreAuthorize("isAuthenticated()")
    @Operation(description = "Getting a Receipt..")
    public ResponseEntity<Object> getReceipt(@Parameter(description = "'receipt' id")
                                             @PathVariable(name = "id") String id) {

        log.debug("!Call method getting a Receipt");

        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {

            ReceiptDto receiptDto = receiptService.getReceiptDto(id);
            if (receiptDto != null) {
                return ResponseEntity.ok(receiptDto);
            }
        }
        return new ResponseEntity<>(new MessageResponse("Receipt not found"), HttpStatus.BAD_REQUEST);

    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(description = "Receipt added")
    public ResponseEntity<Object> addReceipt(@RequestBody ReceiptDto receiptDto) {

        log.debug("!Call method Receipt added");

        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {

            String ObjId = receiptService.addReceiptDto(receiptDto);
            if (ObjId != null) {
                return ResponseEntity.ok(ObjId);
            }
        }
        return new ResponseEntity<>(new MessageResponse("Receipt not added"), HttpStatus.BAD_REQUEST);

    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(description = "Receipt updated")
    public ResponseEntity<Object> updateReceipt(@RequestBody ReceiptDto receiptDto) {

        log.debug("!Call method Receipt updated");

        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {

            if (receiptService.updateReceiptDto(receiptDto) != null) {
                return ResponseEntity.ok(new MessageResponse("Receipt updated successfully!"));
            }
        }
        return new ResponseEntity<>(new MessageResponse("Receipt not updated"), HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("isAuthenticated()")
    @Operation(description = "Receipt.. removed")
    public ResponseEntity<Object> deleteReceipt(@Parameter(description = "'receipt' id")
                                                @PathVariable(name = "id") String id) {

        log.debug("!Call method Receipt removed");

        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {

            if (receiptService.deleteReceiptDto(id) != null) {
                return ResponseEntity.ok(new MessageResponse("Receipt removed successfully!"));
            }
        }
        return new ResponseEntity<>(new MessageResponse("Receipt not removed"), HttpStatus.BAD_REQUEST);

    }

}
