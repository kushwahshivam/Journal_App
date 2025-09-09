package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    public UserService userService;



    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveNewUser(user);
        } catch (Exception e) {
            throw new RuntimeException("Error saving journal entry", e);
        }
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);

    }
    public List<JournalEntry> getall(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteEntryById(ObjectId id, String userName){
        boolean removed = false;
       try {
           User user = userService.findByUserName(userName);
           removed = user.getJournalEntries().removeIf(journalEntry -> journalEntry.getId().equals(id));
           if(removed){
               userService.saveNewUser(user);
               journalEntryRepository.deleteById(id);
           }
       }
       catch (Exception e){
           System.out.println(e);
           throw new RuntimeException("An error occured while deleting journal entry");
       }
        return removed;
    }

    public List<JournalEntry> getJournalEntriesByUserName(String userName){
        User user = userService.findByUserName(userName);
        return user.getJournalEntries();
    }
}
