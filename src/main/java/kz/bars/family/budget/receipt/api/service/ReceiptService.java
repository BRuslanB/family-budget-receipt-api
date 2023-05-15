package kz.bars.family.budget.receipt.api.service;

import kz.bars.family.budget.receipt.api.dto.ReceiptDto;

import java.util.List;

public interface ReceiptService {
    ReceiptDto getReceiptDto(String id);

    ReceiptDto addReceiptDto(ReceiptDto receiptDto);

    ReceiptDto updateReceiptDto(ReceiptDto receiptDto);

    String deleteReceiptDto(String id);

    List<ReceiptDto> getAllReceiptDto();

}
