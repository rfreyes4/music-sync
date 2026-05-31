package com.musicsync.backend.accounts;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/accounts")
public class ConnectedAccountController {
    
    private final ConnectedAccountRepository connectedAccountRepository;

    public ConnectedAccountController(ConnectedAccountRepository connectedAccountRepository) {
        this.connectedAccountRepository = connectedAccountRepository;
    }

    @GetMapping
    public List<ConnectedAccount> getAccounts() {
        return connectedAccountRepository.findAll();
    }


    @PostMapping("/test/spotify")
    public ConnectedAccount createSpotifyTestAccount() {
        ConnectedAccount account = new ConnectedAccount(
                Provider.SPOTIFY,
                "spotify_user_test",
                "fake_access_token",
                "fake_refresh_token",
                LocalDateTime.now().plusHours(1)
        );
        return connectedAccountRepository.save(account);
    }

    @PostMapping("/test/youtube")
    public ConnectedAccount createYouTubeTestAccount() {
        ConnectedAccount account = new ConnectedAccount(
                Provider.YOUTUBE,
                "youtube_user_test",
                "fake_access_token",
                "fake_refresh_token",
                LocalDateTime.now().plusHours(1)
        );
        return connectedAccountRepository.save(account);
    }
    
    


}
