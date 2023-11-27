package shop.app.config.filters;

import org.springframework.stereotype.Component;
import shop.app.Services.users.UserService;
import shop.app.config.JwtTokenUtil;
import shop.app.helper.exceptions.JwtTokenException;
import shop.app.helper.ui_models.UserVM;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtRequestFilter implements Filter {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private List<String> excludeUrls; //for show url by customerUsers

    public JwtRequestFilter(UserService userService,JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //for show url by people: (notFilter)
        excludeUrls = new ArrayList<>();
        excludeUrls.add("/api/user/login");
        excludeUrls.add("/api/user/add");
        excludeUrls.add("/api/color/");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            //first not filter urls:
            String url = ((HttpServletRequest)servletRequest).getRequestURI().toLowerCase();
            if (excludeUrls.stream().anyMatch(x->url.equals(x))){
                filterChain.doFilter(servletRequest,servletResponse);//build
                return;
            }

            //read token header and if==ok set token:
            String requestTokenHeader = ((HttpServletRequest)servletRequest).getHeader("Authorization");
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer "))
                throw new JwtTokenException("request token header dose not set");
            //get token and username:
            String token = requestTokenHeader.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(token);

            if (username == null)
                throw new JwtTokenException("username can not resolve");
            //username == username:
            UserVM userVM = new UserVM(userService.getByUsername(username));
            if (!jwtTokenUtil.validateToken(token,userVM))
                throw new JwtTokenException("invalid token");

            filterChain.doFilter(servletRequest,servletResponse);//build

        }catch (JwtTokenException ex){
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");//for acl
        }catch (Exception ex){
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());//for exception
        }
    }
}
