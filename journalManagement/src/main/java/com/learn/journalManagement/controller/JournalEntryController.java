//package com.learn.journalManagement.controller;
//
//import com.learn.journalManagement.entity.JournalEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController {
//    private Map<Long, JournalEntity> journalEntities = new HashMap();
//
//    @GetMapping
//    public List<JournalEntity>getAllJournalEntities(){
//        return new ArrayList<>(journalEntities.values());
//    }
//
//    @PostMapping
//    public Boolean createEntry(@RequestBody JournalEntity myEntry){
//        journalEntities.put(myEntry.getId(), myEntry);
//        return true;
//    }
//
//    @GetMapping("/{myId}")
//    public JournalEntity getJournalEntry(@PathVariable Long myId){
//        return  journalEntities.get(myId);
//    }
//}
