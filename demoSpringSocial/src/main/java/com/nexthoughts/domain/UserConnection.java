package com.nexthoughts.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UserConnection")
public class UserConnection implements Serializable {
    @Id
    @Column(name = "userId", nullable = false)
    private String userId;

    @Id
    @Column(name = "providerId", nullable = false)
    private String providerId;

    @Id
    @Column(name = "providerUserId", nullable = false)
    private String providerUserId;

    @Column(name = "rank", nullable = false)
    private int rank;

    private String displayName;

    @Column(name = "profileUrl", columnDefinition = "VARCHAR(512)")
    private String profileUrl;

    @Column(name = "imageUrl", columnDefinition = "VARCHAR(512)")
    private String imageUrl;

    @Column(name = "accessToken", nullable = false, columnDefinition = "VARCHAR(512)")
    private String accessToken;

    @Column(name = "secret", columnDefinition = "VARCHAR(512)")
    private String secret;

    @Column(name = "refreshToken", columnDefinition = "VARCHAR(512)")
    private String refreshToken;

    private Long expireTime;
}