package com.learn.journalManagement.service;

import com.learn.journalManagement.entity.JournalEntity;
import com.learn.journalManagement.entity.UserEntity;
import com.learn.journalManagement.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
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
    private UserEntryService userEntryService;

    @Transactional
    public void saveEntry(JournalEntity journalEntity, String userName) {
        UserEntity user = userEntryService.findByUserName(userName);
        journalEntity.setDate(LocalDateTime.now());
        JournalEntity saved = journalEntryRepository.save(journalEntity);

        user.getJournalEntities().add(saved);
        userEntryService.saveEntry(user);
    }

    public void saveEntry(JournalEntity journalEntity) {
        journalEntity.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntity);
    }

    public List<JournalEntity> getAll() {
        return  journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        UserEntity user = userEntryService.findByUserName(userName);
        journalEntryRepository.deleteById(id);
        user.getJournalEntities().removeIf(x -> x.getId().equals(id));
        userEntryService.saveEntry(user);
        return;
    }
}
