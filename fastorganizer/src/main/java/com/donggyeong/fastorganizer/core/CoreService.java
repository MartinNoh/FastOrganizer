package com.donggyeong.fastorganizer.core;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.donggyeong.fastorganizer.work.Work;
import com.donggyeong.fastorganizer.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CoreService {
	private final CoreRepository coreRepository;
	
	public Core create(Work work, String name) {
		Core core = new Core();
		core.setName(name);
		core.setWork(work);
		// TODO: work 하위 core 중 가장 큰 sequence 값을 찾고 그 것보다 1 큰 값을 set
		this.coreRepository.save(core);
		return core;
	}

}
