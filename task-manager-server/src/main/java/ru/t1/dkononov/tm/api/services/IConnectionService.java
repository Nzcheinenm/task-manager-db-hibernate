package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;

public interface IConnectionService {


    @NotNull EntityManager getEntityManager();
}
