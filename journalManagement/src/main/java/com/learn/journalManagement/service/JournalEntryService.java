package com.learn.journalManagement.service;

import com.learn.journalManagement.entity.JournalEntity;
import com.learn.journalManagement.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntity journalEntity) {
        journalEntryRepository.save(journalEntity);
    }

    public List<JournalEntity> getAll() {
        return  journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
        return;
    }
}
