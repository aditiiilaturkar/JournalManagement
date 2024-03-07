package com.learn.journalManagement.service;

import com.learn.journalManagement.entity.JournalEntity;
import com.learn.journalManagement.entity.UserEntity;
import com.learn.journalManagement.repository.JournalEntryRepository;
import com.learn.journalManagement.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {
    @Autowired
    private UserEntryRepository userEntryRepository;

    public void saveEntry(UserEntity userEntity) {
        userEntryRepository.save(userEntity);
    }

    public List<UserEntity> getAll() {
        return  userEntryRepository.findAll();
    }

    public Optional<UserEntity> findById(ObjectId id){
        return userEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userEntryRepository.deleteById(id);
        return;
    }
    public UserEntity findByUserName(String userName) {
       return userEntryRepository.findByUserName(userName);
    }
}
