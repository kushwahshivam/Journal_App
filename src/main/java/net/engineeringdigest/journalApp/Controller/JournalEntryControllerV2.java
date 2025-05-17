package net.engineeringdigest.journalApp.Controller;


import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getall(){
        return journalEntryService.getall();
    }

    @PostMapping
    public boolean addEntry(@RequestBody JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.addEntry(journalEntry);
        return true;
    }

    @GetMapping("/id/{id}")
    public JournalEntry getEntryById(@PathVariable ObjectId id){
        return journalEntryService.getEntryById(id).orElse(null);
    }

    @DeleteMapping("/id/{id}")
    public boolean DelEntryById(@PathVariable ObjectId id){
        journalEntryService.deleteEntryById(id);
        return true;
    }

    @PutMapping( "/id/{id}")
    public JournalEntry updateEntryById(@PathVariable ObjectId id ,@RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalEntryService.getEntryById(id).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle():oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():oldEntry.getContent());
        }
        journalEntryService.addEntry(oldEntry);
        return oldEntry;
    }




}