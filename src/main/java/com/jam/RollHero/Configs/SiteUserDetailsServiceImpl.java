package com.jam.RollHero.Configs;

import com.jam.RollHero.Repository.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SiteUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    SiteUserRepository siteUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) siteUserRepository.findByUsername(username);
    }

//    @Override
//    public UserDetails loadUserId(Long id) throws UsernameNotFoundException {
//        // TODO: return a SiteUser -> optional csat to UserDetails
//        return (UserDetails) siteUserRepository.findById(id);
//    }
}

