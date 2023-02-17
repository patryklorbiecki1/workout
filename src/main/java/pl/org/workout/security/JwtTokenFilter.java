package pl.org.workout.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.org.workout.repositories.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    @Autowired
    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil,UserRepository userRepository){
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(isEmpty(header) || header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        final String token  = header.split(" ")[1].trim();


        UserDetails userDetails = (UserDetails) userRepository
                .findUserByUsername(jwtTokenUtil.getUserNameFromJwtToken(token))
                .orElse(null);
        UsernamePasswordAuthenticationToken
                authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,null,userDetails==null ? List.of() : userDetails.getAuthorities()
        );
        if(!jwtTokenUtil.validateJwtToken(token)){
            filterChain.doFilter(request,response);
            return;
        }

        authenticationToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
