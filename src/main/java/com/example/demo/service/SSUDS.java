package com.example.demo.service;

import com.example.demo.model.AppRole;
import com.example.demo.model.DaltonUserRepository;
import com.example.demo.model.Daltonuser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUDS implements UserDetailsService {



    private DaltonUserRepository userRepository;

    public SSUDS(DaltonUserRepository userRepository){

        this.userRepository=userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try{
            Daltonuser user = userRepository.findByUsername(username);
            if(user==null)
            {
                throw new UsernameNotFoundException("Invalid username or password");
            }


            else {
                System.out.println("Picked "+user.getUsername()+"from the database");
                return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));

            }
        }
        catch(Exception e)
        {
            throw new UsernameNotFoundException("Invalid username or password");
        }

    }

    private Set<GrantedAuthority> getAuthorities(Daltonuser user)
    {
        Set <GrantedAuthority> authorities = new HashSet<>();
        for(AppRole role : user.getRoles())
        {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
            System.out.println("User authority:"+role.getRole());

        }
        return authorities;
    }


}
