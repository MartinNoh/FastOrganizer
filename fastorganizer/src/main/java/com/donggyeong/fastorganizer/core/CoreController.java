package com.donggyeong.fastorganizer.core;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.donggyeong.fastorganizer.work.Work;
import com.donggyeong.fastorganizer.work.WorkService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/core")
@RequiredArgsConstructor
@Controller
public class CoreController {

	private final WorkService workService;
	private final CoreService coreService;
	
//	@PostMapping("/create/{id}")
//	public String createCore(Model model, @PathVariable("id") Integer id, @RequestParam String name) {
//		Work work = this.workService.getWork(id);
//		this.coreService.create(work, name);
//		return String.format("redirect:/work/detail/%s", id);
//	}
	@PostMapping("/create/{id}")
	public String createCore(Model model, @PathVariable("id") Integer id, @Valid CoreForm coreForm, BindingResult bindingResult) {
		Work work = this.workService.getWork(id);
		if(bindingResult.hasErrors()) {
			model.addAttribute("work", work);
			return "work_detail";
					
		}
		Core core = this.coreService.create(work, coreForm.getName());
		return String.format("redirect:/work/detail/%s#core_%s", core.getWork().getId(), core.getId());
	}
}