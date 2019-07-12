package com.wildrunners.wildrunners.controllers;

import com.wildrunners.wildrunners.entities.Cell;
import com.wildrunners.wildrunners.entities.Dice;
import com.wildrunners.wildrunners.entities.Player;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    private Cell[][] grid;

    public GameController() {
        this.grid = new Cell[][]{
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
    }

    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/game")
    public String game(Model model, HttpSession session) {
        if(session.getAttribute("player1") == null) {
            session.setAttribute("player1", new Player(1, "Bryan-Stéphane-Vincent", 0, 0));
        }

        model.addAttribute("currentPlayer", session.getAttribute("player1"));

        model.addAttribute("grid", this.grid);
        return "game";
    }

    @GetMapping("/win")
    public String win() {
        return "win";
    }

    @PostMapping("/game")
    public String game(HttpSession session, @RequestParam String move) {

        boolean won = false;

        int moveSize = Dice.launch(3);

        Player player = (Player)session.getAttribute("player1");

        if(move.equals("up")) {
            for(int i = 0; i < moveSize; i++) {
                player.setY(player.getY() - 1);
                // est-ce que je suis sur un obstacle ?
                if(this.grid[player.getY()][player.getX()].isObstacle()){
                    player.setY(Math.min(player.getY() + 2, 3));
                }
            }
        }
        if(move.equals("down")) {
            for(int i = 0; i < moveSize; i++) {
                player.setY(player.getY() + 1);
                if(this.grid[player.getY()][player.getX()].isObstacle()){
                    player.setY(Math.max(player.getY() - 2, 0));
                }
            }
        }
        if(move.equals("right")) {
            for(int i = 0; i < moveSize; i++) {
                player.setX(player.getX() + 1);
                if(this.grid[player.getY()][player.getX()].isObstacle()){
                    player.setX(Math.max(player.getX() - 2, 0));
                }
            }
        }

        if(move.equals("left")) {
            for(int i = 0; i < moveSize; i++) {
                player.setX(player.getX() - 1);
                if(this.grid[player.getY()][player.getX()].isObstacle()){
                    player.setX(Math.min(player.getX() + 2, 13));
                }
            }
        }


        if(won) {
            return "redirect:/win";
        } 
        else { 
            return "redirect:/game";
        }
    }
}