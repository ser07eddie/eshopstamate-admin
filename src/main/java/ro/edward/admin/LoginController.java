package ro.edward.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SecuritySession securitySession;

    @PostMapping("login")
    public ModelAndView loginUser(@RequestParam("username") String username,
                                  @RequestParam("password") String password
    ) {
        Object[]params = new Object[2];
        params[0] = username;
        params[1] = password;
        List<User> users = jdbcTemplate.query("select * from admin where username=? and password=?;",params, new UserMapper());

        if(users.size() > 0) {
            securitySession.markUserLogged();
            return new ModelAndView("redirect:products");
        }else{
            return new ModelAndView("redirect:/login");
        }
    }


}
