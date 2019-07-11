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
    public String game(Model model, HttpSession session) {
        if(session.getAttribute("currentPlayer") == null) {
            session.setAttribute("currentPlayer", 1);
        }

        model.addAttribute("currentPlayer", session.getAttribute("currentPlayer").equals(1) ? "Wilder 1" : "Wilder 2");


        int[] player = {3, 1};
        model.addAttribute("player", player);
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

    @PostMapping("/game")
    public String game(HttpSession session, @RequestParam(required = false) String move) {

        boolean gameStatus = true;

        if(move != null) { 

            int currentOpponent = 2;
            if(!session.getAttribute("currentPlayer").equals(1)) {
                currentOpponent = 1;
            }

            if(gameStatus == true) {
                session.setAttribute("currentPlayer", currentOpponent);
            } else {
                gameStatus = false;
            }
        }

        if(gameStatus) {
            return "redirect:/game";
        } 
        else { 
            return "redirect:/";
        }
    }
}