package com.project.wineshop.service;

import com.project.wineshop.model.Dish;
import java.util.List;

public interface DishService {
    Dish save(Dish dish);

    Dish getById(Long id);

    Dish getByName(String name);

    List<Dish> findAll();

    Dish update(Long id, Dish dish);

    void deleteById(Long id);
}
