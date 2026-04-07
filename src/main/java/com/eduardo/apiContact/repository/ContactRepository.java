package com.eduardo.apiContact.repository;

import com.eduardo.apiContact.model.entity.Contact;
import com.eduardo.apiContact.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByUserAndPhone(User user, String phone);
    Optional<Contact> findByIdAndUser(Long id, User user);
    boolean existsByUserAndPhone(User user, String phone);
    Page<Contact> findByUser(User user, Pageable pageable);
}
