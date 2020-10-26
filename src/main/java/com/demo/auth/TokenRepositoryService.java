package com.demo.auth;

import com.demo.auth.repository.TokenRepository;
import com.demo.dto.UserToken;
import com.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class TokenRepositoryService implements PersistentTokenRepository {

    private final TokenRepository repository;

    @Autowired
    public TokenRepositoryService(TokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        repository.save(new UserToken(
                token.getUsername(),
                token.getSeries(),
                token.getTokenValue(),
                token.getDate())
        );
    }

    @Transactional
    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        repository.update(series, tokenValue, lastUsed);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        UserToken userToken = repository.findUserTokenBySeries(seriesId);
        if (userToken == null) throw new NotFoundException();
        return new PersistentRememberMeToken(
                userToken.getUsername(),
                userToken.getSeries(),
                userToken.getToken(),
                userToken.getDate()
        );
    }

    @Transactional
    @Override
    public void removeUserTokens(String username) {
        repository.deleteByUsername(username);
    }
}
