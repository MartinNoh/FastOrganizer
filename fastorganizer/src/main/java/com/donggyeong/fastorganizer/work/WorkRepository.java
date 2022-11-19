package com.donggyeong.fastorganizer.work;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkRepository extends JpaRepository<Work, Integer>{
	Work findByTitle(String title);
	Page<Work> findAll(Pageable pageable);
	
	@Query("select "
			+ "distinct w "
			+ "from Work w "
			+ "left outer join SiteUser u on w.email=u "
			+ "left outer join Core c on c.work=w "
			+ "where 1=1 "
			+ "    and w.title like %:kw% "
			+ "    or w.description like %:kw% "
			+ "    or u.email like %:kw% "
			+ "    or c.name like %:kw% "
			)
	Page<Work> findAllByCore(@Param("kw") String kw, Pageable pageable);
}
