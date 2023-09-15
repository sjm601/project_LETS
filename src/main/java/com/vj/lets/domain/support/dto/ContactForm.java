package com.vj.lets.domain.support.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ContactForm {

    String email;
    String name;
    String phoneNumber;
    String cafeName;
    String address;
    int businessNumber;
    String message;

}
