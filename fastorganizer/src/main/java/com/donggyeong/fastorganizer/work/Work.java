package com.donggyeong.fastorganizer.work;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.donggyeong.fastorganizer.category.Category;
import com.donggyeong.fastorganizer.core.Core;
import com.donggyeong.fastorganizer.user.SiteUser;
import com.donggyeong.fastorganizer.BaseTimeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Work extends BaseTimeEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String title;
	
	@Column(length = 100)
	private String description;
	
	@Column(nullable = false, columnDefinition = "char")
	private String useYn;
	
	@ManyToOne
	private Category category;

	@ManyToOne
	private SiteUser email;
	
	@OneToMany(mappedBy = "work", cascade = CascadeType.REMOVE)
	private List<Core> coreList;
	
	
	@PrePersist
	public void prePersist() {
		this.description = this.description == null || "".equals(this.description) ? "설명 없음." : this.description;
		this.useYn = this.useYn == null ? "y" : this.useYn;
	}
}