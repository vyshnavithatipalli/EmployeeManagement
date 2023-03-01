package com.example;

import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
@Component
@ConditionalOnMissingBean(value= {MongoAuditable.class})
public class MongoAuditable implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		String name="Missing";
		return Optional.of(name);
	}

}
