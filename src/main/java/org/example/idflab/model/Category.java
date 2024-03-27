package org.example.idflab.model;

/**
 * Категории, написал в ЕНАМ, а не стринг, чтобы можно было кинуть exception, если в сервис придет неправильная категория
 */
public enum Category {
    GOODS, SERVICES;
}
