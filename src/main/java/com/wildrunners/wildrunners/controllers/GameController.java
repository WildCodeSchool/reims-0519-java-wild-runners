package com.wildrunners.wildrunners.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class GameController {
    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/game")
    public String game() {
        return "game";
    }

    @GetMapping("/win")
    public String win() {
        return "win";
    }
}
