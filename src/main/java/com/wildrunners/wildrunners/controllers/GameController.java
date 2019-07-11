package com.wildrunners.wildrunners.controllers;

import com.wildrunners.wildrunners.entities.Cell;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {
    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/game")
    public String game(Model model) {
        Cell[][] grid = {
            {new Cell(" ", false), new Cell(" ", false), new Cell(" ", false), new Cell(" ", false)},
            {new Cell(" ", false), new Cell(" ", true), new Cell(" ", false), new Cell(" ", false)},
            {new Cell(" ", false), new Cell(" ", false), new Cell(" ", false), new Cell(" ", true)},
            {new Cell(" ", true), new Cell(" ", false), new Cell(" ", false), new Cell(" ", false)},
            {new Cell(" ", false), new Cell(" ", false), new Cell(" ", true), new Cell(" ", false)},
            {new Cell(" ", false), new Cell(" ", true), new Cell(" ", false), new Cell(" ", false)},
            {new Cell(" ", false), new Cell(" ", false), new Cell(" ", false), new Cell(" ", true)},
            {new Cell(" ", true), new Cell(" ", false), new Cell(" ", false), new Cell(" ", false)},
            {new Cell(" ", false), new Cell(" ", false), new Cell(" ", true), new Cell(" ", false)},
            {new Cell(" ", false), new Cell(" ", true), new Cell(" ", false), new Cell(" ", false)},
            {new Cell(" ", false), new Cell(" ", false), new Cell(" ", false), new Cell(" ", true)},
            {new Cell(" ", true), new Cell(" ", false), new Cell(" ", false), new Cell(" ", false)},
            {new Cell(" ", false), new Cell(" ", false), new Cell(" ", true), new Cell(" ", false)},
            {new Cell(" ", false), new Cell(" ", false), new Cell(" ", false), new Cell(" ", false)}
        };
        model.addAttribute("grid", grid);
        return "game";
    }

    @GetMapping("/win")
    public String win() {
        return "win";
    }
}
