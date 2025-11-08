package ru.vsu.portalforembroidery.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.vsu.portalforembroidery.model.Role;

import jakarta.persistence.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "users")
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "role_id")
//    @Convert(converter = RoleConverter.class)
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Column(name = "external_id")
    private UUID externalId;

}
