package com.dev.app.mslogmanagement.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dev.app.mslogmanagement.entity.Log;

@Repository("logRepository")
public interface LogRepository extends CrudRepository<Log, UUID> {

}
