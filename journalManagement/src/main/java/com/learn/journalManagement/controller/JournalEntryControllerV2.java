package com.learn.journalManagement.controller;

import com.learn.journalManagement.entity.JournalEntity;
import com.learn.journalManagement.entity.UserEntity;
import com.learn.journalManagement.service.JournalEntryService;
import com.learn.journalManagement.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("/{userName}")
    public ResponseEntity<List<JournalEntity>>getAllJournalEntitiesOfUser(@PathVariable String userName){
        UserEntity user = userEntryService.findByUserName(userName);
        List<JournalEntity>  all = user.getJournalEntities();
        if(all!= null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntity> createEntry(@PathVariable String userName,  @RequestBody JournalEntity myEntry){
        try {
            UserEntity user = userEntryService.findByUserName(userName);
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{myId}")
    public ResponseEntity<JournalEntity> getJournalEntry(@PathVariable ObjectId myId){
        Optional<JournalEntity> journalEntity=  journalEntryService.findById(myId);
        if(journalEntity.isPresent()){
            return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{myId}/{userName}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId, @PathVariable String userName) {
        journalEntryService.deleteById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{myId}/{userName}")
    public ResponseEntity<JournalEntity> updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntity myEntry, @PathVariable String userName){
        JournalEntity old = journalEntryService.findById(myId).orElse(null);
        if(old != null) {
            old.setTitle(myEntry.getTitle());
            old.setContent(myEntry.getContent());
            journalEntryService.saveEntry(old);
            return  new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
