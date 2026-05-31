package com.musicsync.backend.accounts;


import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "connected_accounts")
public class ConnectedAccount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String providerUserId;

    @Column(length = 2000)
    private String accessToken;
    
    @Column(length = 2000)
    private String refreshToken;

    private LocalDateTime expiresAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ConnectedAccount() {
    }

    public ConnectedAccount(Provider provider, String providerUserId, String accessToken, String refreshToken, LocalDateTime expiresAt) {
        this.provider = provider;
        this.providerUserId = providerUserId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
    public Provider getProvider() {
        return provider;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }






}
