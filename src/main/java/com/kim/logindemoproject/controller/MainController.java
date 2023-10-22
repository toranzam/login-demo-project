package com.kim.logindemoproject.controller;

import com.kim.logindemoproject.domain.Account;
import com.kim.logindemoproject.dto.AccountDto;
import com.kim.logindemoproject.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/signUp")
    public String signUpPage() {
        return "signUpForm";
    }

    @PostMapping("/signUp")
    public String createNewAccount(AccountDto accountDto) {
        if (accountDto == null) {
            return "/signUpForm";
        }

        Account account = Account.builder()
                .username(accountDto.getUsername())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .build();

        accountRepository.save(account);

        return "redirect:/loginForm";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "loginForm";
    }




}
