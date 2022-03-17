package org.acme.user.repository.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.acme.user.repository.entity.Authenticable;

@ApplicationScoped
public class AuthenticableService {
    @Inject
    EntityManager em;

    @Transactional
    public Authenticable createUser(Authenticable user) {
        em.persist(user);
        return user;
    }

    @Transactional
    public Authenticable findById(int id) {
        return em.find(Authenticable.class, id);
    }
}