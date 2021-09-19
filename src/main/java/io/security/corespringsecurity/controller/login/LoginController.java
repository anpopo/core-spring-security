package io.security.corespringsecurity.controller.login;

import io.security.corespringsecurity.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String exception, Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "login";
    }

    // get 방식 로그아웃을 위한 SecurityContextLogoutHandler 사용
    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse res) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(req, res, authentication);
        }

        return "redirect:/login";
    }

    @GetMapping("/denied")
    public String accessDenied(@RequestParam(required = false) String exception, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("exception", exception);

        return "user/login/denied";
    }
}
