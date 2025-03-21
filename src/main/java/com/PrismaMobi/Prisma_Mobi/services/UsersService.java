package com.PrismaMobi.Prisma_Mobi.services;

import com.PrismaMobi.Prisma_Mobi.entities.Users;
import com.PrismaMobi.Prisma_Mobi.entities.UsersDTO;
import com.PrismaMobi.Prisma_Mobi.entities.enums.Roles;
import com.PrismaMobi.Prisma_Mobi.respositories.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;


    @Transactional
    public UsersDTO register(UsersDTO usersDTO){
        String encryptedPassword = passwordEncoder.encode(usersDTO.password());
        Users users = usersRepository.save
                (new Users(null, usersDTO.login(), encryptedPassword, null, Roles.ROLE_PASSENGER));
        return new UsersDTO(users);
    }

    public Boolean authorizeLogin(String login, String password) {
        Users users = usersRepository.findByLogin(login);
        if(users == null){
            return false;
        }
        return passwordEncoder.matches(password, users.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByLogin(username);
    }
}
