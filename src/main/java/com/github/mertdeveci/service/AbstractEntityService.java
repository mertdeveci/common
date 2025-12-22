package com.github.mertdeveci.service;

import jakarta.persistence.EntityNotFoundException;

import java.util.function.Supplier;

public abstract class AbstractEntityService {

    public abstract Supplier<EntityNotFoundException> createEntityNotFoundException();

}
