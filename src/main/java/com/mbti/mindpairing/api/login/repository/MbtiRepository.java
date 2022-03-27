package com.mbti.mindpairing.api.login.repository;

import com.mbti.mindpairing.api.login.entity.MbtiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MbtiRepository extends JpaRepository<MbtiEntity, Long> , MbtiRepositoryCustom {
    List<MbtiEntity> findAll();
}
