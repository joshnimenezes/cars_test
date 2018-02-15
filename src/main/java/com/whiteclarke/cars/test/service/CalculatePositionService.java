package com.whiteclarke.cars.test.service;

import com.whiteclarke.cars.test.enums.CarEnums.Direction;
import com.whiteclarke.cars.test.model.Position;
import org.springframework.stereotype.Service;

import static com.whiteclarke.cars.test.enums.CarEnums.Commands;

@Service
public class CalculatePositionService {

    private static final int MAX_GRID_SIZE=15;

    /**
     *
     * @param userInput - co-ordinates and commands entered by the user on the UI
     */
    public Position processValues(String userInput) throws Exception {

        String[] values = userInput.split(",");

        int xCoordinate = Integer.parseInt(values[0]);

        values = values[1].split(":");

        int yCoordinate = Integer.parseInt(values[0]);

        String commands = values[1];

        Position initialPosition = new Position(xCoordinate,yCoordinate);

        return calculate(initialPosition,commands, Direction.NORTH);
    }

    /**
     * calculate the new position of the car based on the initial position and the commands entered
     * @param position
     * @param commands
     * @return
     */
    public Position calculate(Position position, String commands, Direction initialDirection) throws Exception {

        Position newPosition= position;
        Direction currentDirection = initialDirection;

        for (int i=0; i<commands.length(); i++){

            char command= commands.charAt(i);

            Commands inputCommand = Commands.valueOf(String.valueOf(command));
                if (inputCommand==Commands.R){
                    currentDirection = right(currentDirection);
                }
                else if (inputCommand==Commands.L){
                    currentDirection = left(currentDirection);
                 }
                else if (inputCommand==Commands.F){
                    newPosition = forward(newPosition, currentDirection);
                }
                else{
                    newPosition = backward(newPosition, currentDirection);
                }
        }

        validateCoordinates(newPosition);
        System.out.print("The new co-ordinates are: "+newPosition.getyCoordinate()+","+newPosition.getxCoordinate());

        return newPosition;
    }

    /**
     * check for invalid co-ordinates of the car
     * @param newPosition
     * @throws Exception
     */
    private void validateCoordinates(Position newPosition) throws Exception {
        if(newPosition.getxCoordinate()>MAX_GRID_SIZE || newPosition.getyCoordinate()>MAX_GRID_SIZE  || newPosition.getxCoordinate()<1 || newPosition.getyCoordinate()<1){
            throw new Exception("Invalid Co-ordinates");
        }
    }


    public Direction right(Direction direction){
        return Direction.values()[direction.ordinal()==3?0:direction.ordinal()+1];
    }


    public Direction left(Direction direction){
        return Direction.values()[direction.ordinal()==0?3:direction.ordinal()-1];
    }

    /**
     *move forward in the direction of the car
     * @param position
     * @return
     */
    public Position forward(Position position, Direction currentDirection) throws Exception {

        int x= position.getxCoordinate();
        int y= position.getyCoordinate();

        if (currentDirection.equals(Direction.NORTH)){
            position.setyCoordinate(y+1);
        }
        else if (currentDirection.equals(Direction.EAST)){
            position.setxCoordinate(x+1);
        }
        else if (currentDirection.equals(Direction.SOUTH)){
            position.setyCoordinate(y-1);
        }
        else if (currentDirection.equals(Direction.WEST)){
            position.setxCoordinate(x-1);
        }
        validateCoordinates(position);
        return position;
    }

    /**
     * move backward/reverse
     * @param position
     * @return
     */
    public Position backward(Position position, Direction currentDirection) throws Exception {
        int x= position.getxCoordinate();
        int y= position.getyCoordinate();

        if (currentDirection.equals(Direction.NORTH)){
            position.setyCoordinate(y-1);
        }
        else if (currentDirection.equals(Direction.EAST)){
            position.setxCoordinate(x-1);
        }
        else if (currentDirection.equals(Direction.SOUTH)){
            position.setyCoordinate(y+1);
        }
        else if (currentDirection.equals(Direction.WEST)){
            position.setxCoordinate(x+1);
        }
        validateCoordinates(position);
        return position;
    }
}
