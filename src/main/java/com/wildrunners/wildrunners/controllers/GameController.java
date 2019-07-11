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
            {new Cell("l1c1", false), new Cell("l2c1", false), new Cell("l3c1", false), new Cell("l4c1", false)},
            {new Cell("l1c2", false), new Cell("l2c2", false), new Cell("l3c2", true), new Cell("l4c2", false)},
            {new Cell("l1c3", true), new Cell("l2c3", false), new Cell("l3c3", false), new Cell("l4c3", false)}
        };
        model.addAttribute("grid", grid);
        return "game";
    }

    @GetMapping("/win")
    public String win() {
        return "win";
    }
}
