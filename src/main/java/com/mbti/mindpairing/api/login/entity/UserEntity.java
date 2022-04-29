package com.mbti.mindpairing.api.login.entity;

import com.mbti.mindpairing.api.login.dto.User;
import com.mbti.mindpairing.api.login.dto.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "USERS")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String nickname;

    @Column
    private String password;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false)
    private String phone;

    @Column
    private String mbti;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private User.UserStatus status;

    @CreatedDate
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "delete_time")
    private LocalDateTime deleteTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
