package com.learn.journalManagement.repository;

import com.learn.journalManagement.entity.JournalEntity;
import com.learn.journalManagement.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepository extends MongoRepository<UserEntity, ObjectId> {
    UserEntity findByUserName(String userName);
}
