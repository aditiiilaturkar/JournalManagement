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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserEntryService userEntryService;

    @GetMapping
    public List<UserEntity>getAllUsers(){
        return userEntryService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody UserEntity usr){
        userEntryService.saveEntry(usr);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<UserEntity> updateMapping(@PathVariable String userName, @RequestBody UserEntity newUser){
        UserEntity userInDb = userEntryService.findByUserName(userName);
        if(userInDb != null) {
            userInDb.setUserName(newUser.getUserName());
            userInDb.setPassword(newUser.getPassword());
            userEntryService.saveEntry(userInDb);
            return  new ResponseEntity<>(userInDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}
