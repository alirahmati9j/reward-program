package com.ali.rewardprogram.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Component
public class TransactionalExecutor {

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public <T> T execute(Supplier<T> command) {
		return command.get();
	}

}

