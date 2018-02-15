package com.whiteclarke.cars.test.enums;


public class CarEnums {

    public enum Direction {
        NORTH, EAST, SOUTH, WEST;
    }

    public enum Commands {
        R('R'), L('L'), F('F'), B('B');

        private final char command;

        public char getCommand() {
            return command;
        }

        Commands(char command) {
            this.command = command;
        }
    }
}
