package kz.bars.family.budget.receipt.api.service.impl;

import kz.bars.family.budget.receipt.api.dto.ReceiptDto;
import kz.bars.family.budget.receipt.api.model.Receipt;
import kz.bars.family.budget.receipt.api.repository.ReceiptRepo;
import kz.bars.family.budget.receipt.api.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepo receiptRepo;

    @Override
    public ReceiptDto getReceiptDto(String id) {

        ReceiptDto receiptDto = null;
        Receipt receipt = receiptRepo.findById(new ObjectId(id)).orElse(null);

        if (receipt != null) {
            receiptDto = receipt.toDto();
            log.debug("!Getting a Receipt: id={}", id);

        } else {

            log.error("!Receipt not found: id={}", id);
        }

        return receiptDto;
    }

    @Override
    public String addReceiptDto(ReceiptDto receiptDto) {

        try {
            if (receiptDto != null) {
                // Use null for ObjectId as it will be generated automatically
                Receipt receipt = receiptDto.toModel(null);

                receiptRepo.save(receipt);

                log.debug("!Receipt added: id={}, fileType={}, fileContent={}",
                        receipt.getObjectId().toString(), receiptDto.getFileType(),
                        receiptDto.getFileContent().substring(0, 10) + "...");

                return receipt.getObjectId().toString();
            }

        } catch (Exception ex) {

            log.error("!Receipt not added: fileType={}, fileContent={}",
                    receiptDto.getFileType(),
                    receiptDto.getFileContent().substring(0, 10) + "...");

            return null;
        }

        log.error("!Receipt not added");
        return null;
    }

    @Override
    public String updateReceiptDto(ReceiptDto receiptDto) {

        try {
            ObjectId objId = new ObjectId(receiptDto.getId()); // Convert string id to ObjectId
            Receipt receipt = receiptRepo.findById(objId).orElse(null);

            if (receipt != null) {
                objId = new ObjectId(receiptDto.getId()); // Convert string id to ObjectId
                receipt = receiptDto.toModel(objId); // Using ObjectId for PUT request

                receiptRepo.save(receipt);

                log.debug("!Receipt updated, id={}, fileType={}, fileContent={}",
                        receiptDto.getId(), receiptDto.getFileType(),
                        receiptDto.getFileContent().substring(0, 10) + "...");

                return receiptDto.getId();

            } else {

                log.error("!Receipt not found: id={}", receiptDto.getId());
                return null;
            }

        } catch (Exception ex) {

            log.error("!Receipt not updated");
            return null;
        }
    }

    @Override
    public String deleteReceiptDto(String id) {

        try {
            if (receiptRepo.findById(new ObjectId(id)).orElse(null) != null) {
                receiptRepo.deleteById(new ObjectId(id));

                log.debug("!Receipt removed: id={}", id);
                return id;

            } else {

                log.error("!Receipt not found: id={}", id);
                return null;
            }

        } catch (Exception ex) {

            log.error("!Receipt not removed: id={}", id);
            return null;
        }
    }

    @Override
    public List<ReceiptDto> getAllReceiptDto() {

        List<Receipt> receipts = receiptRepo.findAll();
        log.debug("!Getting a list of All Receipts");

        return receipts.stream().map(Receipt::toDto).collect(Collectors.toList());
    }

}
