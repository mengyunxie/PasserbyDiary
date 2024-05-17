package com.passerby.userservice.service;

import com.passerby.userservice.model.Session;
import com.passerby.userservice.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public String addSession(String username) {
        String sid = UUID.randomUUID().toString();
        Session session = new Session();
        session.setSid(sid);
        session.setUsername(username);
        sessionRepository.save(session);
        return sid;
    }

    public String getSessionUser(String sid) {
        Session session = sessionRepository.findById(sid).orElse(null);
        return session != null ? session.getUsername() : null;
    }

    public void deleteSession(String sid) {
        sessionRepository.deleteById(sid);
    }
}
