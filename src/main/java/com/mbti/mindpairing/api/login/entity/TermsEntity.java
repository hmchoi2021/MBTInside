package com.mbti.mindpairing.api.login.entity;

import com.mbti.mindpairing.api.login.dto.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TERMS")
@Entity
public class TermsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String termsInfo;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String mandatoryYn;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private User.TermsStatus status;

}
