package com.pawel.wojtanka.bootcamp.security;

import com.pawel.wojtanka.bootcamp.model.Student;
import com.pawel.wojtanka.bootcamp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Student user = studentRepository.findByEmail(userEmail);

        return new AppUserDetails(user);
    }

}
