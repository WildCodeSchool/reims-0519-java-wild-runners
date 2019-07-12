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
    public String game(Model model, HttpSession session, @RequestParam(required = false) String avatar) {
        if(avatar == null) {
            if(session.getAttribute("avatar") == null) {
                session.setAttribute("avatar", "kenny");
            }
        }
        else {
            session.setAttribute("avatar", avatar);
        }
        if(session.getAttribute("player1") == null) {
            session.setAttribute("player1", new Player(1, "Hello my dear", 0, 0));
        }
        if (session.getAttribute("dice") != null) {
            model.addAttribute("dice", session.getAttribute("dice"));
        }

        model.addAttribute("currentPlayer", session.getAttribute("player1"));

        model.addAttribute("grid", this.grid);
        model.addAttribute("avatar", session.getAttribute("avatar"));
        return "game";
    }

    @GetMapping("/win")
    public String win(HttpSession session) {
        for(Cell[] column : this.grid) {
            for(Cell cell : column) {
                cell.setDiscovered(false);
            }
        }
        Player player = (Player)session.getAttribute("player1");
        player.setX(0);
        player.setY(0);
        return "win";
    }

    @PostMapping("/game")
    public String game(HttpSession session, @RequestParam String move) {

        boolean won = false;

        int moveSize = Dice.launch(3);
        session.setAttribute("dice", moveSize);
        Player player = (Player)session.getAttribute("player1");

        boolean wasBlocked = false;
        if(move.equals("up")) {
            for(int i = 0; !wasBlocked && i < moveSize; i++) {
                player.setY(Math.max(player.getY() - 1, 0));
                //est-ce que je suis sur un obstacle ?
                if(this.grid[player.getX()][player.getY()].isObstacle()){
                    wasBlocked = true;
                    this.grid[player.getX()][player.getY()].setDiscovered(true);
                    player.setY(Math.min(player.getY() + 2, this.grid[0].length-1));
                }
            }
        }
        if(move.equals("down")) {
            for(int i = 0; !wasBlocked && i < moveSize; i++) {
                player.setY(Math.min(player.getY() + 1, this.grid[0].length-1));
                if(this.grid[player.getX()][player.getY()].isObstacle()){
                    wasBlocked = true;
                    this.grid[player.getX()][player.getY()].setDiscovered(true);
                    player.setY(Math.max(player.getY() - 2, 0));
                }
            }
        }
        if(move.equals("right")) {
            for(int i = 0; !wasBlocked && i < moveSize; i++) {
                player.setX(Math.min(player.getX() + 1, this.grid.length-1));
                if(this.grid[player.getX()][player.getY()].isObstacle()){
                    wasBlocked = true;
                    this.grid[player.getX()][player.getY()].setDiscovered(true);
                    player.setX(Math.max(player.getX() - 2, 0));
                }
            }
        }

        if(move.equals("left")) {
            for(int i = 0; !wasBlocked && i < moveSize; i++) {
                player.setX(Math.max(player.getX() - 1, 0));
                if(this.grid[player.getX()][player.getY()].isObstacle()){
                    wasBlocked = true;
                    this.grid[player.getX()][player.getY()].setDiscovered(true);
                    player.setX(Math.min(player.getX() + 2, this.grid.length-1));
                }
            }
        }


        if(player.getX() >= 13) {
            return "redirect:/win";
        } 
        else { 
            return "redirect:/game";
        }
    }
}