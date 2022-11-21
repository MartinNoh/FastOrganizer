package com.donggyeong.fastorganizer.work;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.donggyeong.fastorganizer.category.Category;
import com.donggyeong.fastorganizer.category.CategoryService;
import com.donggyeong.fastorganizer.core.CoreForm;
import com.donggyeong.fastorganizer.user.SiteUser;
import com.donggyeong.fastorganizer.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RequestMapping("/work")
@RequiredArgsConstructor
@Controller
public class WorkController {

	private final WorkService workService;
	private final CategoryService categoryService;
	private final UserService userService;
	
	/*
	@RequestMapping("/list")
	public String list(Model model) {
		List<Work> workList = this.workService.getList();
		model.addAttribute("workList", workList);
		return "work_list";
	}
	*/
	/*
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
		Page<Work> paging = this.workService.getList(page);
		model.addAttribute("paging", paging);
		return "work_list";
	}
	*/
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {
		Page<Work> paging = this.workService.getList(page, kw);
		model.addAttribute("paging", paging);
		model.addAttribute("kw", kw);
		return "work_list";
	}
	
	@RequestMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, CoreForm coreForm) {
		Work work = this.workService.getWork(id);
		model.addAttribute("work", work);
		return "work_detail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String workCreate(Model model, WorkForm workForm) {
		List<Category> categoryList = this.categoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);
		return "work_form";
	}
	
//	@PostMapping("/create")
//	public String workCreate(@RequestParam String categoryName, @RequestParam String title, @RequestParam String description) {
//		Category category = this.categoryService.getCategory(categoryName);
//		this.workService.create(category, title, description);
//		return "redirect:/work/list";
//	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String workCreate(Model model, @Valid WorkForm workForm, BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()) {
			List<Category> categoryList = this.categoryService.getCategoryList();
			model.addAttribute("categoryList", categoryList);
			return "work_form";
		}
		Category category = this.categoryService.getCategory(workForm.getCategory());
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.workService.create(category, workForm.getTitle(), workForm.getDescription(), siteUser);
		return "redirect:/work/list";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String workModify(WorkForm workForm, @PathVariable("id") Integer id, Principal principal, Model model) {
		Work work = this.workService.getWork(id);
		if(!work.getEmail().getEmail().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		workForm.setTitle(work.getTitle());
		workForm.setDescription(work.getDescription());
		workForm.setCategory(work.getCategory().getName());
		
		List<Category> categoryList = this.categoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);
		
		return "work_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String workModify(@Valid WorkForm workForm, BindingResult bindingResult, Principal principal, @PathVariable("id") Integer id) {
		if(bindingResult.hasErrors()) {
			return "work_form";
		}
		Work work = this.workService.getWork(id);
		if(!work.getEmail().getEmail().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}
		this.workService.modify(work, workForm.getTitle(), workForm.getCategory(), workForm.getDescription());
		return String.format("redirect:/work/detail/%s", id);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String workDelete(Principal principal, @PathVariable("id") Integer id) {
		Work work = this.workService.getWork(id);
		if (!work.getEmail().getEmail().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
		}
		this.workService.delete(work);
		return "redirect:/";
	}
}
