package com.pawel.wojtanka.bootcamp.security;

import com.pawel.wojtanka.bootcamp.model.User;
import com.pawel.wojtanka.bootcamp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsPasswordService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(userName);

        return new UserPasswordDetails(user);
    }

}
