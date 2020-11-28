package com.pawel.wojtanka.bootcamp.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Podaj nie puste imię")
    private String firstName;

    @NotEmpty(message = "{com.pawel.wojtanka.bootcamp.model.Student.lastName.NotEmpty}")
    private String lastName;

    @NotEmpty
    @Column(unique = true)
    private String email;

    private String phoneNumber;

    private Integer ratePerHour;

    @NotEmpty
    private String password;

    @Transient
    private String confirmPassword;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private Rule rule;

    @ManyToMany()
    private List<Course> courses;

    @Override
    public String toString() {
        return firstName + ' ' + lastName + "\n";
    }

}
