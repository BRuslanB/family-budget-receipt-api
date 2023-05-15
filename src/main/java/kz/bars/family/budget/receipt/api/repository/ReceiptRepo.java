package kz.bars.family.budget.receipt.api.repository;

import kz.bars.family.budget.receipt.api.model.Receipt;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepo extends MongoRepository<Receipt, ObjectId> {

}
