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
public class World {

    String[][] board;
    
    public World() {
        this.board = new String[4][4];
        this.board[0][0] = "start";
        this.board[3][3] = "end";
    }

    public boolean isActive(int width, int height) {
        if (null == this.board[width][height]) {
            return false;
        } else {
            switch (this.board[width][height]) {
                case "start":
                    return true;
                case "end":
                    return true;
                case "pit":
                    return true;
                case "wumpus":
                    return true;
                default:
                    return false;
            }
        }
    }

    public void setEnvironment() {
        int counter = 0;
        int randomWidth;
        int randomHeight;
        while (counter < 2) {
            randomWidth = (int) (Math.random() * 3);
            randomHeight = (int) (Math.random() * 3);
            if (!isActive(randomWidth, randomHeight)) {
                this.board[randomWidth][randomHeight] = "pit";
                counter++;
            }
        }

        counter = 0;
        while (counter < 1) {
            randomWidth = (int) (Math.random() * 3);
            randomHeight = (int) (Math.random() * 3);
            if (!isActive(randomWidth, randomHeight)) {
                this.board[randomWidth][randomHeight] = "wumpus";
                counter++;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ("pit".equals(this.board[i][j])) {
                    if (i - 1 >= 0) {
                        if (!isActive(i - 1, j)) {
                            this.board[i - 1][j] = "breeze";
                        }
                    }
                    if (j - 1 >= 0) {
                        if (!isActive(i, j - 1)) {
                            this.board[i][j - 1] = "breeze";
                        }
                    }
                    if (i + 1 <= 3) {
                        if (!isActive(i + 1, j)) {
                            this.board[i + 1][j] = "breeze";
                        }
                    }
                    if (j + 1 <= 3) {
                        if (!isActive(i, j + 1)) {
                            this.board[i][j + 1] = "breeze";
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ("wumpus".equals(this.board[i][j])) {

                    if (i - 1 >= 0 && !isActive(i - 1, j)) {
                        if ("breeze".equals(this.board[i - 1][j])) {
                            this.board[i - 1][j] = "breeze and stench";
                        } else {
                            this.board[i - 1][j] = "stench";
                        }
                    }

                    if (j - 1 >= 0 && !isActive(i, j - 1)) {
                        if ("breeze".equals(this.board[i][j - 1])) {
                            this.board[i][j - 1] = "breeze and stench";
                        } else {
                            this.board[i][j - 1] = "stench";
                        }
                    }

                    if (i + 1 <= 3 && !isActive(i + 1, j)) {
                        if ("breeze".equals(this.board[i + 1][j])) {
                            this.board[i + 1][j] = "breeze and stench";
                        } else {
                            this.board[i + 1][j] = "stench";
                        }
                    }

                    if (j + 1 <= 3 && !isActive(i, j + 1)) {
                        if ("breeze".equals(this.board[i][j + 1])) {
                            this.board[i][j + 1] = "breeze and stench";
                        } else {
                            this.board[i][j + 1] = "stench";
                        }
                    }
                }
            }
        }
        counter = 0;
        while (counter < 1) {
            randomWidth = (int) (Math.random() * 3);
            randomHeight = (int) (Math.random() * 3);
            if (!isActive(randomWidth, randomHeight)) {
                if (this.board[randomWidth][randomHeight] == null) {
                    this.board[randomWidth][randomHeight] = "gold";
                    counter++;
                } else {
                    StringBuilder st = new StringBuilder(this.board[randomWidth][randomHeight]);
                    st.append(" and gold");
                    this.board[randomWidth][randomHeight] = st.toString();
                    counter++;
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.board[i][j] == null) {
                    this.board[i][j] = "empty";
                }
            }
        }
    }

    public void printWorld() {
        System.out.println("Printing the Board by Tiles:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("Tile %d * %d is %s.%n", i, j, this.board[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public void printWorld_UI() {
        System.out.println("Printing the board in a Table:");
        for (int i = 0; i < 4; i++) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.printf("|| %s || %s || %s || %s%n", this.board[i][0], this.board[i][1], this.board[i][2], this.board[i][3]);
        }
        System.out.println("");
    }
    

}// end of class World
