package com.donggyeong.fastorganizer.category;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.donggyeong.fastorganizer.work.Work;
import com.donggyeong.fastorganizer.BaseTimeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category extends BaseTimeEntity{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 20)
	private String name;
	
	@Column(length = 100)
	private String description;	
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
	private List<Work> workList;
}
