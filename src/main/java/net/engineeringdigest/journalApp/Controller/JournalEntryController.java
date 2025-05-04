package net.engineeringdigest.journalApp.Controller;


import net.engineeringdigest.journalApp.Entity.JournalEntry;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/journal")
class JournalEntryController {
    HashMap<Long, JournalEntry> journalEntries = new HashMap<>();



    @GetMapping
    public List<JournalEntry> getall(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean addEntry(@RequestBody JournalEntry journalEntry){
        journalEntries.put(journalEntry.getId(), journalEntry);
        return true;
    }

    @GetMapping("/id/{id}")
    public JournalEntry getEntryById(@PathVariable long id){
        return journalEntries.get(id);
    }

    @DeleteMapping("/id/{id}")
    public JournalEntry DelEntryById(@PathVariable long id){
        return journalEntries.remove(id);
    }

    @PutMapping( "/id/{id}")
    public JournalEntry updateEntryById(@PathVariable long id ,@RequestBody JournalEntry journalEntry){
        return journalEntries.put(id, journalEntry);
    }




}