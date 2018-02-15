package com.whiteclarke.cars.test.service;

import com.whiteclarke.cars.test.enums.CarEnums.Direction;
import com.whiteclarke.cars.test.model.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatePositionServiceTest {

    @Autowired
    private CalculatePositionService calculatePositionService;

    private Position mockPosition;
    private Position expectedPosition;
    private Position actualPosition;
    private String command;
    private Direction direction;
    private Direction actualDirection;
    private Direction expectedDirection;


    @Before
    public void setUp() {
        direction = Direction.NORTH;
    }

    @Test
    public void calculateTest_Success(){

        try {
            mockPosition =new Position(5,5);
            command="FLFLFFRFFF";
            actualPosition= calculatePositionService.calculate(mockPosition,command, direction);
            expectedPosition= new Position(1,4);
            Assert.assertEquals(expectedPosition.getxCoordinate(),actualPosition.getxCoordinate());
            Assert.assertEquals(expectedPosition.getyCoordinate(),actualPosition.getyCoordinate());

            mockPosition=new Position(6,6);
            command="FFLFFLFFLFF";
            actualPosition= calculatePositionService.calculate(mockPosition,command, direction);
            expectedPosition= new Position(6,6);
            Assert.assertEquals(expectedPosition.getxCoordinate(),actualPosition.getxCoordinate());
            Assert.assertEquals(expectedPosition.getyCoordinate(),actualPosition.getyCoordinate());

            mockPosition= new Position(5,5);
            command="RFLFRFLF";
            actualPosition= calculatePositionService.calculate(mockPosition,command, direction);
            expectedPosition= new Position(7,7);
            Assert.assertEquals(expectedPosition.getxCoordinate(),actualPosition.getxCoordinate());
            Assert.assertEquals(expectedPosition.getyCoordinate(),actualPosition.getyCoordinate());

        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test(expected = Exception.class)
    public void calculateTest_Fail() throws Exception {
        mockPosition=new Position(15,15);
        command="LFRFF";
        actualPosition= calculatePositionService.calculate(mockPosition,command, direction);
    }

    @Test
    public void processValuesTest_Fail()  {
        mockPosition = new Position(11,14);
        command="FFLFFLFFLFFFFFFF";
        try {
            actualPosition= calculatePositionService.calculate(mockPosition,command, direction);
        } catch (Exception e) {
            Assert.assertEquals("Invalid Co-ordinates",e.getMessage());
        }
    }

    @Test
    public void processValuesTest_IncorrectPosition()  {
        mockPosition=new Position(11,14);
        command="FFLFFLFFLFFFFFFF";
        try {
            actualPosition= calculatePositionService.calculate(mockPosition,command, direction);
        } catch (Exception e) {
            Assert.assertEquals("Invalid Co-ordinates",e.getMessage());
        }
    }

    @Test
    public void processValuesTest_Success() {

        try {
            actualPosition= calculatePositionService.processValues("5,5:RFLFRFLF");
            expectedPosition= new Position(7,7);
            Assert.assertEquals(expectedPosition.getxCoordinate(),actualPosition.getxCoordinate());
            Assert.assertEquals(expectedPosition.getyCoordinate(),actualPosition.getyCoordinate());

            actualPosition= calculatePositionService.processValues("6,6:FFLFFLFFLFF");
            expectedPosition= new Position(6,6);
            Assert.assertEquals(expectedPosition.getxCoordinate(),actualPosition.getxCoordinate());
            Assert.assertEquals(expectedPosition.getyCoordinate(),actualPosition.getyCoordinate());

            actualPosition= calculatePositionService.processValues("5,5:FLFLFFRFFF");
            expectedPosition= new Position(1,4);
            Assert.assertEquals(expectedPosition.getxCoordinate(),actualPosition.getxCoordinate());
            Assert.assertEquals(expectedPosition.getyCoordinate(),actualPosition.getyCoordinate());

            actualPosition= calculatePositionService.processValues("3,6:LBLBLBLBRFRF");
            expectedPosition= new Position(4,5);
            Assert.assertEquals(expectedPosition.getxCoordinate(),actualPosition.getxCoordinate());
            Assert.assertEquals(expectedPosition.getyCoordinate(),actualPosition.getyCoordinate());
        } catch (Exception e) {
            e.getMessage();
        }
    }



    @Test(expected = Exception.class)
    public void forwardTest_Fail() throws Exception {
        mockPosition = new Position(15,15);

        actualPosition= calculatePositionService.forward(mockPosition, direction);
    }

    @Test
    public void forwardTest_IncorrectPosition() throws Exception {
        mockPosition = new Position(10,2);

        actualPosition= calculatePositionService.forward(mockPosition, direction);
        expectedPosition= new Position(2,10);
        Assert.assertNotEquals(expectedPosition.getxCoordinate(),actualPosition.getxCoordinate());
        Assert.assertNotEquals(expectedPosition.getyCoordinate(),actualPosition.getyCoordinate());
    }


    @Test
    public void backwardTest_Success() throws Exception {
        mockPosition= new Position(3,4);
        actualPosition= calculatePositionService.backward(mockPosition, direction);
        expectedPosition= new Position(3,3);
        Assert.assertEquals(expectedPosition.getxCoordinate(),actualPosition.getxCoordinate());
        Assert.assertEquals(expectedPosition.getyCoordinate(),actualPosition.getyCoordinate());
    }

    @Test(expected = Exception.class)
    public void backwardTest_Fail() throws Exception {
        mockPosition = new Position(1, 1);

        actualPosition= calculatePositionService.backward(mockPosition, direction);
    }

    @Test
    public void forwardTest_Success() throws Exception {

        mockPosition = new Position(9, 2);

        actualPosition = calculatePositionService.forward(mockPosition, direction);
        expectedPosition = new Position(9,3);
        Assert.assertEquals(expectedPosition.getxCoordinate(),actualPosition.getxCoordinate());
        Assert.assertEquals(expectedPosition.getyCoordinate(),actualPosition.getyCoordinate());
    }

    @Test
    public void rightTest_success(){

        expectedDirection = Direction.EAST;
        actualDirection = calculatePositionService.right(direction);

        Assert.assertEquals(expectedDirection, actualDirection);

        expectedDirection = Direction.SOUTH;
        actualDirection = calculatePositionService.right(Direction.EAST);

        Assert.assertEquals(expectedDirection, actualDirection);

        expectedDirection = Direction.WEST;
        actualDirection = calculatePositionService.right(Direction.SOUTH);

        Assert.assertEquals(expectedDirection, actualDirection);

        expectedDirection = Direction.NORTH;
        actualDirection = calculatePositionService.right(Direction.WEST);

        Assert.assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void rightTest_fail(){

        expectedDirection = Direction.WEST;
        actualDirection = calculatePositionService.right(direction);

        Assert.assertNotEquals(expectedDirection, actualDirection);

        expectedDirection = Direction.SOUTH;
        actualDirection = calculatePositionService.right(Direction.WEST);

        Assert.assertNotEquals(expectedDirection, actualDirection);

        expectedDirection = Direction.EAST;
        actualDirection = calculatePositionService.right(Direction.SOUTH);

        Assert.assertNotEquals(expectedDirection, actualDirection);

        expectedDirection = Direction.NORTH;
        actualDirection = calculatePositionService.right(Direction.EAST);

        Assert.assertNotEquals(expectedDirection, actualDirection);
    }

    @Test
    public void leftTest_success(){

        expectedDirection = Direction.WEST;
        actualDirection = calculatePositionService.left(direction);

        Assert.assertEquals(expectedDirection, actualDirection);

        expectedDirection = Direction.SOUTH;
        actualDirection = calculatePositionService.left(Direction.WEST);

        Assert.assertEquals(expectedDirection, actualDirection);

        expectedDirection = Direction.EAST;
        actualDirection = calculatePositionService.left(Direction.SOUTH);

        Assert.assertEquals(expectedDirection, actualDirection);

        expectedDirection = Direction.NORTH;
        actualDirection = calculatePositionService.left(Direction.EAST);

        Assert.assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void leftTest_fail(){

        expectedDirection = Direction.EAST;
        actualDirection = calculatePositionService.left(direction);

        Assert.assertNotEquals(expectedDirection, actualDirection);

        expectedDirection = Direction.SOUTH;
        actualDirection = calculatePositionService.left(Direction.EAST);

        Assert.assertNotEquals(expectedDirection, actualDirection);

        expectedDirection = Direction.WEST;
        actualDirection = calculatePositionService.left(Direction.SOUTH);

        Assert.assertNotEquals(expectedDirection, actualDirection);

        expectedDirection = Direction.NORTH;
        actualDirection = calculatePositionService.left(Direction.WEST);

        Assert.assertNotEquals(expectedDirection, actualDirection);
    }
}
