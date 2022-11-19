package com.donggyeong.fastorganizer.work;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkForm {
	@NotEmpty(message = "제목은 필수 항목입니다.")
	@Size(max=50)
	private String title;
	
	@NotEmpty(message = "카테고리는 필수 항목입니다.")
	private String category;
	
	private String description;
}
