package com.pawel.wojtanka.bootcamp.repository;

import com.pawel.wojtanka.bootcamp.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {

    Rule findByRuleName(String ruleName);

}
