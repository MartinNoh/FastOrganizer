package com.donggyeong.fastorganizer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.donggyeong.fastorganizer.category.Category;
import com.donggyeong.fastorganizer.category.CategoryRepository;
import com.donggyeong.fastorganizer.category.CategoryService;
import com.donggyeong.fastorganizer.work.Work;
import com.donggyeong.fastorganizer.work.WorkRepository;
import com.donggyeong.fastorganizer.work.WorkService;

@SpringBootTest
@ContextConfiguration(classes = FastorganizerApplication.class)
class FastOrganizerApplicationTests {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private WorkRepository workRepository;
	
	@Autowired
	private WorkService workService;
	
	@Autowired
	private CategoryService categoryService;
	
	/*
	@Test
	void testCreateCategory() {
		Category c1 = new Category();
		c1.setName("JAVA");
		c1.setDescription("Java 언어 관련 내용");
		this.categoryRepository.save(c1);
		
		Category c2 = new Category();
		c2.setName("PYTHON");
		c2.setDescription("Python 언어 관련 내용");
		this.categoryRepository.save(c2);
		
		Category c3 = new Category();
		c3.setName("Spring boot");
		c3.setDescription("Spring boot 프레임워크 관련 내용");
		this.categoryRepository.save(c3);
		
		Category c4 = new Category();
		c4.setName("Django");
		c4.setDescription("Django 프레임워크 관련 내용");
		this.categoryRepository.save(c4);
	}
 	*/
	
	/*
	@Test
	void testCreateWork() {
		Optional<Category> oc1 = this.categoryRepository.findById(9);
		assertTrue(oc1.isPresent());
		Category c1 = oc1.get();
		
		Work w1 = new Work();
		w1.setEmail("ehdrud1129@naver.com");
		w1.setTitle("Java naming convention rules");
		w1.setCategory(c1);
		this.workRepository.save(w1);

		Optional<Category> oc2 = this.categoryRepository.findById(10);
		assertTrue(oc2.isPresent());
		Category c2 = oc2.get();
		
		Work w2 = new Work();
		w2.setEmail("ehdrud1129@naver.com");
		w2.setTitle("Python venv 가상환경 생성과 사용");
		w2.setCategory(c2);
		this.workRepository.save(w2);
		}
		*/
	
	@Test
	void testCreateWorkList() {
		for(int i=1; i<=300; i++) {
			String title = String.format("테스트 데이터입니다. :[%03d]", i);
			String description = "테스트 데이터";
			Category category = this.categoryService.getCategory("JAVA");
			this.workService.create(category, title, description, null);
		}
		
	}
}
