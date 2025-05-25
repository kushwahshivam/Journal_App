package net.engineeringdigest.journalApp.Controller;


import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping( "/{userName}")
    public ResponseEntity<?> getAllEntriesofUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<JournalEntry> getall = user.getJournalEntries();
        if(getall != null && !getall.isEmpty()){
        return new ResponseEntity<>(getall,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/{userName}")
    public ResponseEntity<?> addEntry(@RequestBody JournalEntry journalEntry,@PathVariable String userName){
        try {
            journalEntryService.addEntry(journalEntry,userName);
            return new ResponseEntity<>(journalEntry,HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId id){
        Optional<JournalEntry> journalEntry = journalEntryService.getEntryById(id);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userName}/{id}")
    public ResponseEntity<?> DelEntryById(@PathVariable ObjectId id,@PathVariable String userName){
        journalEntryService.deleteEntryById(id,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping( "/id/{id}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId id ,@RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalEntryService.getEntryById(id).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle():oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():oldEntry.getContent());
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}