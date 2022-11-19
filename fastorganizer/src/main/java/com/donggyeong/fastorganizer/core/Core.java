package com.donggyeong.fastorganizer.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.donggyeong.fastorganizer.work.Work;
import com.donggyeong.fastorganizer.BaseTimeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Core extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false)
	private Integer sequence;
	
	@ManyToOne
	private Work work;
	
	@PrePersist
	public void prePersist() {
		this.sequence = this.sequence == null ? 1 : this.sequence;
	}
}