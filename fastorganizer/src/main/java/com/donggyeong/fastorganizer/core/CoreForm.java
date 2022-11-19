package com.donggyeong.fastorganizer.core;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoreForm {

	@NotEmpty(message = "핵심 내용은 필수입니다.")
	private String name;
}
