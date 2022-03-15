package com.example.authenticationservice.entityDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KeyCloakUserInfo {
    private String sub;
    private String name;
    private String preferred_username;
    private String email;
}
