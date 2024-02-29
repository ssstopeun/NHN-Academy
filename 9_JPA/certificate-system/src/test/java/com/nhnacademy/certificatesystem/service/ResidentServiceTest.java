package com.nhnacademy.certificatesystem.service;

import com.nhnacademy.certificatesystem.config.RootConfig;
import com.nhnacademy.certificatesystem.config.WebConfig;
import com.nhnacademy.certificatesystem.entity.Certification;
import com.nhnacademy.certificatesystem.entity.Resident;
import com.nhnacademy.certificatesystem.repository.ResidentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class ResidentServiceTest {
    @PersistenceContext
    private EntityManager entityManager;

}