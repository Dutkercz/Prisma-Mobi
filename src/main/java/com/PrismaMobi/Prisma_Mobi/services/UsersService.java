package com.PrismaMobi.Prisma_Mobi.services;

import com.PrismaMobi.Prisma_Mobi.entities.Users;
import com.PrismaMobi.Prisma_Mobi.entities.UsersDTO;
import com.PrismaMobi.Prisma_Mobi.respositories.UsersRespository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRespository usersRespository;


    @Transactional
    public UsersDTO register(UsersDTO usersDTO){
        String encryptedPassword = passwordEncoder.encode(usersDTO.password());
        Users users = usersRespository.save(new Users(null, usersDTO.login(), encryptedPassword));
        return new UsersDTO(users);
    }

    public Boolean authorizeLogin(String login, String password) {
        Users users = usersRespository.findByLogin(login);
        if(users == null){
            return false;
        }
        return passwordEncoder.matches(password, users.getPassword());
    }
}
