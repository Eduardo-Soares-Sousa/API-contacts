package com.eduardo.apiContact.controller;

import com.eduardo.apiContact.business.service.ContactService;
import com.eduardo.apiContact.model.dto.ContactDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contacts")
public class ContactController {
    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactDto> saveContact(
            @Valid @RequestBody ContactDto contactDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contactService.saveContact(contactDto));
    }

    @GetMapping
    public ResponseEntity<Page<ContactDto>> listContacts(Pageable pageable) {
        return ResponseEntity.ok(contactService.listContacts(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<ContactDto> findByPhone(@RequestParam String phone) {
        return ResponseEntity.ok(contactService.findByPhone(phone));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
