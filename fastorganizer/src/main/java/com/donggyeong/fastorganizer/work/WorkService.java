package com.donggyeong.fastorganizer.work;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.donggyeong.fastorganizer.category.Category;
import com.donggyeong.fastorganizer.category.CategoryRepository;
import com.donggyeong.fastorganizer.category.CategoryService;
import com.donggyeong.fastorganizer.user.SiteUser;
import com.donggyeong.fastorganizer.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WorkService {
	
	private final WorkRepository workRepository;
	private final CategoryService categoryService;
	
	/*
	public List<Work> getList() {
		return this.workRepository.findAll();
	}
	*/
	/*
	public Page<Work> getList(int page) {
		List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createdDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.workRepository.findAll(pageable);
	}
	*/
	public Page<Work> getList(int page, String kw) {
		List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createdDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.workRepository.findAllByCore(kw, pageable);
	}

	public Work getWork(Integer id) {
		Optional<Work> work = this.workRepository.findById(id);
		if(work.isPresent()) {
			return work.get();
		} else {
			throw new DataNotFoundException("work not found");
		}
	}
	
	public void create(Category category, String title, String description, SiteUser email) {
		Work w = new Work();
		w.setCategory(category);
		w.setTitle(title);
		w.setDescription(description);
		w.setEmail(email);
		this.workRepository.save(w);
	}
	
	public void modify(Work work, String title, String category, String description) {
		work.setTitle(title);
		work.setCategory(categoryService.getCategory(category));
		work.setDescription(description);
		this.workRepository.save(work);
	}
	
	public void delete(Work work) {
		this.workRepository.delete(work);
	}
}