package com.donggyeong.fastorganizer.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.donggyeong.fastorganizer.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	public Category getCategory(String name) {
		Optional<Category> category = this.categoryRepository.findByName(name);
		if(category.isPresent()) {
			return category.get();
		} else {
			throw new DataNotFoundException("category not found");
		}
	}
	
	public List<Category> getCategoryList() {
		List<Category> categoryList = this.categoryRepository.findAll();
		return categoryList;
	}

}
