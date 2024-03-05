package com.learn.journalManagement.controller;

import com.learn.journalManagement.entity.JournalEntity;
import com.learn.journalManagement.service.JournalEntryService;
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
    @GetMapping
    public ResponseEntity<List<JournalEntity>>getAllJournalEntities(){
        List<JournalEntity>  all = journalEntryService.getAll();
        if(all!= null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity myEntry){
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
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

    @DeleteMapping("/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{myId}")
    public ResponseEntity<JournalEntity> updateMapping(@PathVariable ObjectId myId, @RequestBody JournalEntity myEntry){
        JournalEntity old = journalEntryService.findById(myId).orElse(null);
        if(old != null) {
            old.setTitle(myEntry.getTitle());
            old.setContent(myEntry.getContent());
            return  new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
