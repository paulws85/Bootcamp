package com.pawel.wojtanka.bootcamp.repository;

import com.pawel.wojtanka.bootcamp.model.Rule;
import com.pawel.wojtanka.bootcamp.model.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByRule(Rule rule);

    List<Student> findByRule_RuleNameContains(String ruleName);

    Student findByEmail(String email);

}
