package com.eduardo.apiContact.business.converter;

import com.eduardo.apiContact.model.dto.ContactDto;
import com.eduardo.apiContact.model.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactConverter {
    public Contact converterToContactEntity(ContactDto contactDto) {
        return Contact.builder()
                .name(contactDto.getName())
                .email(contactDto.getEmail())
                .phone(contactDto.getPhone())
                .description(contactDto.getDescription())
                .build();
    }

    public ContactDto converterToContactDto(Contact contact) {
        return ContactDto.builder()
                .name(contact.getName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .description(contact.getDescription())
                .build();
    }
}
