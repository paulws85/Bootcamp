package com.pawel.wojtanka.bootcamp.service;

import com.pawel.wojtanka.bootcamp.model.Rule;
import com.pawel.wojtanka.bootcamp.repository.RuleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RuleService {

    private final RuleRepository ruleRepository;

    public List<Rule> findAllRules() {
        return ruleRepository.findAll();
    }

    public Rule findRuleByName(String ruleName) {
        return ruleRepository.findByRuleName(ruleName);
    }

}
