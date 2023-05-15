package kz.bars.family.budget.receipt.api.dto;

import kz.bars.family.budget.receipt.api.model.Receipt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDto {

    private String id;
    private String fileType;
    private String fileContent;

    public Receipt toModel(ObjectId objId) {
        if (objId != null) {
            return new Receipt(objId, fileType, fileContent.getBytes());
        } else {
            return new Receipt(fileType, fileContent.getBytes());
        }
    }
}
