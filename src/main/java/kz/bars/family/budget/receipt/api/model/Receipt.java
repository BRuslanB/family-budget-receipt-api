package kz.bars.family.budget.receipt.api.model;

import kz.bars.family.budget.receipt.api.dto.ReceiptDto;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "receipts")
public class Receipt {

    @Id
    private ObjectId objectId;
    private String fileType;
    private byte[] fileData;

    public Receipt(ObjectId objectId, String fileType, byte[] fileData) {
        this.objectId = objectId;
        this.fileType = fileType;
        this.fileData = fileData;
    }

    public Receipt(String fileType, byte[] fileData) {
        this.fileType = fileType;
        this.fileData = fileData;
    }

    public Receipt() {

    }

    public ReceiptDto toDto() {
        return new ReceiptDto(objectId.toString(), fileType, new String(fileData));
    }

}
