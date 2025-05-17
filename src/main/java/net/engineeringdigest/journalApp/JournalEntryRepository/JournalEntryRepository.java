package net.engineeringdigest.journalApp.JournalEntryRepository;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}
