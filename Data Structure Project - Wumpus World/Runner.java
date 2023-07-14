/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusworld;

/**
 *
 * @author Mohammad Aminazadeh & pooya mirzaei
 */
public class Runner {

    String direction;
    int currentWidth;
    int currentHeight;

    public Runner() {
        this.direction = "right";
        this.currentWidth = 0;
        this.currentHeight = 0;
    }

    public void moveForward() {
        switch (this.direction) {
            case "right": {
                this.currentHeight++;
                break;
            }
            case "left": {
                this.currentHeight--;
                break;
            }
            case "up": {
                this.currentWidth--;
                break;
            }
            case "down": {
                this.currentWidth++;
                break;
            }
        }
    }

    private void rotateRight() {
        switch (this.direction) {
            case "right": {
                this.direction = "down";
                break;
            }
            case "left": {
                this.direction = "up";
                break;
            }
            case "up": {
                this.direction = "right";
                break;
            }
            case "down": {
                this.direction = "left";
                break;
            }
        }
    }

    private void rotateLeft() {
        switch (this.direction) {
            case "right": {
                this.direction = "up";
                break;
            }
            case "left": {
                this.direction = "down";
                break;
            }
            case "up": {
                this.direction = "left";
                break;
            }
            case "down": {
                this.direction = "right";
                break;
            }
        }
    }

    public void moveRight() {
        switch (this.direction) {
            case "right": {
                moveForward();
                System.out.println("Runner moved forward");
                printLocation();
                break;
            }
            case "left": {
                rotateLeft();
                rotateLeft();
                moveForward();
                System.out.println("Runner rotated left 2 times and moved forward");
                printLocation();
                break;
            }
            case "up": {
                rotateRight();
                moveForward();
                System.out.println("Runner rotated right and moved forward");
                printLocation();
                break;
            }
            case "down": {
                rotateLeft();
                moveForward();
                System.out.println("Runner rotated left and moved forward");
                printLocation();
                break;
            }
        }
    }

    public void moveLeft() {
        switch (this.direction) {
            case "right": {
                rotateLeft();
                rotateLeft();
                moveForward();
                System.out.println("Runner rotated left 2 times and moved forward");
                printLocation();
                break;
            }
            case "left": {
                moveForward();
                System.out.println("Runner moved forward");
                printLocation();
                break;
            }
            case "up": {
                rotateLeft();
                moveForward();
                System.out.println("Runner rotated left and moved forward");
                printLocation();
                break;
            }
            case "down": {
                rotateRight();
                moveForward();
                System.out.println("Runner rotated right and moved forward");
                printLocation();
                break;
            }
        }
    }

    public void moveDown() {
        switch (this.direction) {
            case "right": {
                rotateRight();
                moveForward();
                System.out.println("Runner rotated right and moved forward");
                printLocation();
                break;
            }
            case "left": {
                rotateLeft();
                moveForward();
                System.out.println("Runner rotated left and moved forward");
                printLocation();
                break;
            }
            case "up": {
                rotateLeft();
                rotateLeft();
                moveForward();
                System.out.println("Runner rotated left 2 times and moved forward");
                printLocation();
                break;
            }
            case "down": {
                moveForward();
                System.out.println("Runner moved forward");
                printLocation();
                break;
            }
        }
    }

    public void moveUp() {
        switch (this.direction) {
            case "right": {
                rotateLeft();
                moveForward();
                System.out.println("Runner rotated left and moved forward");
                printLocation();
                break;
            }
            case "left": {
                rotateRight();
                moveForward();
                System.out.println("Runner rotated right and moved forward");
                printLocation();
                break;
            }
            case "up": {
                moveForward();
                System.out.println("Runner moved forward");
                printLocation();
                break;
            }
            case "down": {
                rotateLeft();
                rotateLeft();
                moveForward();
                System.out.println("Runner rotated left 2 times and moved forward");
                printLocation();
                break;
            }
        }
    }

    public void printLocation() {
        System.out.printf("the player is at %d * %d%n", this.currentWidth, this.currentHeight);
    }
    
}// end of class Runner
