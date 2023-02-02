package com.project.service.store;

import com.project.model.store.Category;
import com.project.repo.store.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAllCategories () {
        return categoryRepository.findAll();
    }

    public Optional<Category> findCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }
}
