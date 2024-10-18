package com.example.locking_mechanisms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.locking_mechanisms.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/transfer")
    public String transferMoney(@RequestParam Long fromAccountId,
                                @RequestParam Long toAccountId,
                                @RequestParam Double amount) {
        accountService.transferMoneyWithOptimisticLock(fromAccountId, toAccountId, amount);
        return "Money transferred successfully!";
    }
}
