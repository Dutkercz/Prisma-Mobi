package com.PrismaMobi.Prisma_Mobi.infra;

import com.PrismaMobi.Prisma_Mobi.entities.Users;
import com.PrismaMobi.Prisma_Mobi.respositories.UsersRepository;
import com.PrismaMobi.Prisma_Mobi.services.TokenService;
import com.PrismaMobi.Prisma_Mobi.services.UsersService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsersRepository usersRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("FILTRO DE SEGURANÇA");
        String tokenJWT = recuperarToken(request);

        if (tokenJWT != null){
            String subject = tokenService.getSubject(tokenJWT);
            Users users = usersRepository.findByLogin(subject);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(users, null, users.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
        System.out.println("Saindo do filtro de segurança");

    }

    private String recuperarToken(HttpServletRequest request) {
        String cleanToken = request.getHeader("Authorization");
        if(cleanToken != null){
            return cleanToken.replace("Bearer ", "");
        }
        return null;
    }
}
