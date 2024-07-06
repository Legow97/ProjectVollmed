package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.users.UserRepository;
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
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Obtener tokens de los Header
        //ystem.out.println("INicio del filtro");
        var authHeader = request.getHeader("Authorization");//.replace("Bearer ","");
        System.out.println(authHeader);
        if(authHeader != null) {

            var token = authHeader.replace("Bearer ","");

            var subject = tokenService.getSubject(token);
            if (subject != null) {
                var user = userRepository.findByLogin(subject);
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()); // forzamos inicio de sesión
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);

    }


    //Forma Genérica de un Filtro
//    @WebFilter(urlPatterns = "/api/**")
//    public class LogFilter implements Filter {
//
//        @Override
//        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//            System.out.println("Requisição recebida em: " + LocalDateTime.now());
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//
//    }
}
