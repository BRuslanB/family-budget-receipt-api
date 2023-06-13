package kz.bars.family.budget.receipt.api.service;

import kz.bars.family.budget.receipt.api.dto.ReceiptDto;

import java.util.List;

public interface ReceiptService {
    ReceiptDto getReceiptDto(String id);

    String addReceiptDto(ReceiptDto receiptDto);

    String updateReceiptDto(ReceiptDto receiptDto);

    String deleteReceiptDto(String id);

    List<ReceiptDto> getAllReceiptDto();

}
