package com.whiteclarke.cars.test.model;

import javax.validation.constraints.Pattern;


public class Position {

    private int xCoordinate;

    private int yCoordinate;

    @Pattern(regexp = "([1-9]|1[0-5]),([1-9]|1[0-5]):([FBRL]+)",
            message = "Please enter a valid input (Format - xCo-ordinate,yCo-ordinate:commands )")
    private String userInput;

    /**
     *
     * @param xCoordinate
     */
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     *
     * @return
     */
    public int getxCoordinate() {
        return xCoordinate;
    }

    /**
     *
     * @param yCoordinate
     */
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     *
     * @return
     */
    public int getyCoordinate() {
        return yCoordinate;
    }

    /**
     *
     * @return
     */
    public String getUserInput() {
        return userInput;
    }

    /**
     *
     * @param userInput
     */
    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    /**
     *
     */
    public Position() {
        this.xCoordinate=-1;
        this.yCoordinate=-1;
        this.userInput=null;
    }

    /**
     *
     * @param xCoordinate
     * @param yCoordinate
     */
    public Position(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
}
