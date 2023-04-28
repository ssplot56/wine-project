package com.project.wineshop.service.impl;

import com.project.wineshop.model.Dish;
import com.project.wineshop.repository.DishRepository;
import com.project.wineshop.service.DishService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public Dish getById(Long id) {
        return dishRepository.findById(id).orElseThrow();
    }

    @Override
    public Dish getByName(String name) {
        return dishRepository.findByName(name);
    }

    @Override
    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    @Override
    public Dish update(Long id, Dish dish) {
        dish.setId(id);
        return dishRepository.save(dish);
    }

    @Override
    public void deleteById(Long id) {
        dishRepository.deleteById(id);
    }
}
