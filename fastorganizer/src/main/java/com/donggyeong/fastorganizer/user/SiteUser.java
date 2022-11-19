package com.donggyeong.fastorganizer.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.donggyeong.fastorganizer.BaseTimeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String nickname;
	
	@Column(nullable = false, columnDefinition = "char")
	private String useYn;
	
	@PrePersist
	public void prePersist() {
		this.useYn = this.useYn == null ? "y" : this.useYn;
	}
}