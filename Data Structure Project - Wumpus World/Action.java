/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusworld;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Mohammad Aminazadeh & pooya mirzaei
 */
public class Action {

    String[][] positions;
    boolean hasGold;
    boolean playing;
    Runner runner;
    World world;
    boolean lose;
    String defaultDirection;

    public Action() {
        this.positions = new String[4][4];
        this.positions[0][0] = "empty";
        this.positions[3][3] = "empty";
        this.runner = new Runner();
        this.world = new World();
        this.hasGold = false;
        this.playing = true;
        this.defaultDirection = "down";
        this.world.setEnvironment();
        this.world.printWorld();
        this.world.printWorld_UI();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.positions[i][j] == null) {
                    this.positions[i][j] = "none";
                }
            }
        }
    }

    public boolean hasGold() {
        ArrayList<String> feelings = new ArrayList<>();
        String[] temp = this.world.board[this.runner.currentWidth][this.runner.currentHeight].split(" and ");
        feelings.addAll(Arrays.asList(temp));
        return feelings.contains("gold");
    }

    public boolean hasBreeze() {
        ArrayList<String> feelings = new ArrayList<>();
        String[] temp = this.world.board[this.runner.currentWidth][this.runner.currentHeight].split(" and ");
        feelings.addAll(Arrays.asList(temp));
        return feelings.contains("breeze");
    }

    public void reportFeelings() {
        if (this.world.board[this.runner.currentWidth][this.runner.currentHeight].equals("empty")) {
            System.out.println("Nothing Feeled!");
        } else {
            System.out.printf("%s Feeled!%n", this.world.board[this.runner.currentWidth][this.runner.currentHeight]);
        }
    }

    public void removeGold() {
        System.out.println("Gold Was Picked!");
        this.hasGold = true;
        switch (this.world.board[runner.currentWidth][runner.currentHeight]) {
            case "gold":
                this.world.board[runner.currentWidth][runner.currentHeight] = null;
                break;
            case "breeze and gold":
                this.world.board[runner.currentWidth][runner.currentHeight] = "breeze";
                break;
            case "stench and gold":
                this.world.board[runner.currentWidth][runner.currentHeight] = "stench";
                break;
            default:
                this.world.board[runner.currentWidth][runner.currentHeight] = "breeze and stench";
                break;
        }
    }

    public void setPit() {
        if (this.runner.currentWidth - 1 >= 0) {
            if (!this.world.isActive(this.runner.currentWidth - 1, this.runner.currentHeight)) {
                if ("none".equals(this.positions[this.runner.currentWidth - 1][this.runner.currentHeight])) {
                    this.positions[this.runner.currentWidth - 1][this.runner.currentHeight] = "ppit1";
                } else {
                    switch (this.positions[this.runner.currentWidth - 1][this.runner.currentHeight]) {
                        case "ppit1":
                            this.positions[this.runner.currentWidth - 1][this.runner.currentHeight] = "ppit2";
                            break;
                        case "ppit2":
                            this.positions[this.runner.currentWidth - 1][this.runner.currentHeight] = "dpit";
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        if (runner.currentHeight - 1 >= 0) {
            if (!this.world.isActive(runner.currentWidth, runner.currentHeight - 1)) {
                if ("none".equals(this.positions[runner.currentWidth][runner.currentHeight - 1])) {
                    this.positions[runner.currentWidth][runner.currentHeight - 1] = "ppit1";
                } else {
                    switch (this.positions[runner.currentWidth][runner.currentHeight - 1]) {
                        case "ppit1":
                            this.positions[runner.currentWidth][runner.currentHeight - 1] = "ppit2";
                            break;
                        case "ppit2":
                            this.positions[runner.currentWidth][runner.currentHeight - 1] = "dpit";
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        if (runner.currentWidth + 1 <= 3) {
            if (!this.world.isActive(runner.currentWidth + 1, runner.currentHeight)) {
                if ("none".equals(this.positions[runner.currentWidth + 1][runner.currentHeight])) {
                    this.positions[runner.currentWidth + 1][runner.currentHeight] = "ppit1";
                } else {
                    switch (this.positions[runner.currentWidth + 1][runner.currentHeight]) {
                        case "ppit1":
                            this.positions[runner.currentWidth + 1][runner.currentHeight] = "ppit2";
                            break;
                        case "ppit2":
                            this.positions[runner.currentWidth + 1][runner.currentHeight] = "dpit";
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        if (runner.currentHeight + 1 <= 3) {
            if (!this.world.isActive(runner.currentWidth, runner.currentHeight + 1)) {
                if ("none".equals(this.positions[runner.currentWidth][runner.currentHeight + 1])) {
                    this.positions[runner.currentWidth][runner.currentHeight + 1] = "ppit1";
                } else {
                    switch (this.positions[runner.currentWidth][runner.currentHeight + 1]) {
                        case "ppit1":
                            this.positions[runner.currentWidth][runner.currentHeight + 1] = "ppit2";
                            break;
                        case "ppit2":
                            this.positions[runner.currentWidth][runner.currentHeight + 1] = "dpit";
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public boolean noUpLimit() {
        return this.runner.currentWidth - 1 >= 0 && !"dpit".equals(this.positions[this.runner.currentWidth - 1][this.runner.currentHeight]);
    }

    public boolean noRightLimit() {
        return this.runner.currentHeight + 1 <= 3 && !"dpit".equals(this.positions[this.runner.currentWidth][this.runner.currentHeight + 1]);
    }

    public boolean noLeftLimit() {
        return this.runner.currentHeight - 1 >= 0 && !"dpit".equals(this.positions[this.runner.currentWidth][this.runner.currentHeight - 1]);
    }

    public boolean noDownLimit() {
        return this.runner.currentWidth + 1 <= 3 && !"dpit".equals(this.positions[this.runner.currentWidth + 1][this.runner.currentHeight]);
    }

    public void checkPosition() {
        if (hasGold()) {
            removeGold();
        } else if (this.world.board[this.runner.currentWidth][this.runner.currentHeight].equals("wumpus")) {
            System.out.println("Wumpus has been killed!");
            this.positions[this.runner.currentWidth][this.runner.currentHeight] = "empty";
        }
    }

    public void plusMove() {
        System.out.println("Plus Move Started!");
        if (noUpLimit()) {
            this.runner.moveUp();
            checkPosition();
            this.runner.moveDown();
        }
        if (noRightLimit()) {
            this.runner.moveRight();
            checkPosition();
            this.runner.moveLeft();
        }
        if (noLeftLimit()) {
            this.runner.moveLeft();
            checkPosition();
            this.runner.moveRight();
        }
        if (noDownLimit()) {
            this.runner.moveDown();
            checkPosition();
            this.runner.moveUp();
        }
        System.out.println("Plus Move Ended!");
        System.out.println("");
    }

    public void setEmpty() {
        if (this.runner.currentWidth - 1 >= 0) {
            this.positions[this.runner.currentWidth - 1][this.runner.currentHeight] = "empty";
        }
        if (this.runner.currentHeight + 1 <= 3) {
            this.positions[this.runner.currentWidth][this.runner.currentHeight + 1] = "empty";
        }
        if (this.runner.currentHeight - 1 >= 0) {
            this.positions[this.runner.currentWidth][this.runner.currentHeight - 1] = "empty";
        }
        if (this.runner.currentWidth + 1 <= 3) {
            this.positions[this.runner.currentWidth + 1][this.runner.currentHeight] = "empty";
        }
    }

    public void run() {
        System.out.println("Runner Started from Tile 0 * 0");
        runner.moveRight();
        reportFeelings();
        System.out.println("");
        // main game while loop
        while (this.playing) {
            if (this.world.board[this.runner.currentWidth][this.runner.currentHeight].equals("pit")) {
                this.lose = true;
                this.playing = false;
                break;
            } else if (this.world.board[this.runner.currentWidth][this.runner.currentHeight].equals("wumpus")) {
                System.out.println("Wumpus has been killed!");
                this.positions[this.runner.currentWidth][this.runner.currentHeight] = "empty";
            } else if (hasBreeze()) {
                setPit();
            } else if (this.world.board[this.runner.currentWidth][this.runner.currentHeight].equals("end")) {
                if (this.hasGold) {
                    this.playing = false;
                    this.lose = false;
                    break;
                }
            } else if (hasBreeze()) {
                setPit();
            } else {
                plusMove();
                setEmpty();
            }
            if (hasGold()) {
                removeGold();
            } 
            if (!noDownLimit()) {
                this.defaultDirection = "right";
            } else {
                this.runner.moveDown();
                reportFeelings();
                System.out.println("");
                continue;
            }

            if (!noRightLimit()) {
                this.defaultDirection = "up";
            } else {
                this.runner.moveRight();
                reportFeelings();
                System.out.println("");
                continue;
            }

            if (!noUpLimit()) {
                this.defaultDirection = "left";
            } else {
                this.runner.moveUp();
                reportFeelings();
                System.out.println("");
                continue;
            }

            if (!noLeftLimit()) {
                this.defaultDirection = "down";
                if (!noDownLimit()) {
                    this.defaultDirection = "right";
                    this.runner.moveRight();
                    reportFeelings();
                    System.out.println("");
                }
            } else {
                this.runner.moveLeft();
                reportFeelings();
                System.out.println("");
            }

        }// end of main game while
        if (lose) {
            System.out.println("Player Lost!");
        } else {
            System.out.println("Player won!");
        }
    }

}// end of class Action
