package com.eduardo.apiContact.business.service;

import com.eduardo.apiContact.business.converter.ContactConverter;
import com.eduardo.apiContact.business.exceptions.ResourceNotFoundException;
import com.eduardo.apiContact.model.dto.ContactDto;
import com.eduardo.apiContact.model.entity.Contact;
import com.eduardo.apiContact.model.entity.User;
import com.eduardo.apiContact.repository.ContactRepository;
import com.eduardo.apiContact.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final ContactConverter contactConverter;
    private final UserRepository userRepository;

    public ContactDto saveContact(ContactDto contactDto) {
        User user = getLoggedUser();
        if(contactRepository.existsByUserAndPhone(user, contactDto.getPhone())) {
            throw new RuntimeException("Contact already exists for this phone");
        }

        Contact contact = contactConverter.converterToContactEntity(contactDto);
        contact.setUser(user);

        return contactConverter.converterToContactDto(
                contactRepository.save(contact)
        );
    }

    public Page<ContactDto> listContacts(Pageable pageable) {
        User user = getLoggedUser();

        Pageable pageableWithSort = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("name").ascending()
        );

        Page<Contact> contacts = contactRepository.findByUser(user, pageableWithSort);

        return contacts.map(contactConverter::converterToContactDto);
    }

    public ContactDto findByPhone(String phone) {
        User user = getLoggedUser();
        Contact contact = contactRepository.findByUserAndPhone(user, phone)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));

        return contactConverter.converterToContactDto(contact);
    }

    public void deleteById(Long id) {
        User user = getLoggedUser();
        Contact contact = contactRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));

        contactRepository.delete(contact);
    }

    private User getLoggedUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
