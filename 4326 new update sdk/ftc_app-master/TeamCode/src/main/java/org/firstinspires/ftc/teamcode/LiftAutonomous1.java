package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.lang.Runtime;
import java.util.Timer;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="LiftAutonomous1", group="Autonomous")

public class LiftAutonomous1 extends LinearOpMode {
    DcMotor lift;
    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;
    Servo marker;

    boolean robotIsDown = false;
    int quarterTurn = 7; //mock turn factor that does a quarter turn at .8/.9 power***

    boolean runOnce = false;
    public void runOpMode() {
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        lift = hardwareMap.dcMotor.get("lift");
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        marker = hardwareMap.servo.get("marker");
        waitForStart();

        while (opModeIsActive()) {  //just in case
            if(runOnce == false) {
                    //        liftAut();

                    boolean isLowBatery = false; //change for batteryvoltage: <12.6 = low, >=12.6 = high
                    int battery = 0;
                    if (isLowBatery){
                        battery = 2;
                    } else{
                        battery = 0;
                    }

                    lift.setPower(-.7);
                    wait(23 + battery);
                    lift.setPower(0);
                    wait(5);
                    robotIsDown = true; //delete later if it runs the first time


                    boolean liftOnly = false; // change to true for only lifting
                    telemetry.addData("text", "robotIsDown: " + robotIsDown);
                    wait(10);

                    //moves to lander
                    drive(.4, .4, .4, .4);
                    wait(2);
                   drive(.9,.9,-.9,-.9); //lander turn
                    wait(quarterTurn);
                   stopRobot();
                  drive(.8, .8, .7, .7);
                  wait(15);
                  stopRobot();

                  markerAut();

                drive(.3,.3,-.3, -.3);
                wait(1);
                stopRobot();
                drive(-.7,-.7,-.7,-.7);   //(was)moves backwards
                wait(29);
                stopRobot(); //safety ;3

                stopRobot();
                runOnce = true;
            }
            else{
                telemetry.addData("text", "runOnce is done");
            }
        }
    }
//    public void loop() {
//        telemetry.addData("text", "Lift Power: " + lift);
//    }
//
//    public void start() {
//
//        stopRobot();
//    }

    public void liftAut(){
      //for now
        lift.setPower(-.5);
        wait(26);
        lift.setPower(0);
        wait(10);
        robotIsDown = true; //delete later if it runs the first time
    }
    public void liftDown(){
        lift.setPower(-.7); //lift down
        wait(20);
        lift.setPower(0);
        wait(10);
    }

    public void markerAut(){

//        drive(.8,.8,-.8,-.8);
//        wait(quarterTurn - 2); //used to be -1
        drive(-.7,-.7,.7,.7);
        wait(quarterTurn - 3);
        stopRobot();

        marker.setPosition(1); //shacky shake - used to be 1
        wait(8);
        marker.setPosition(0); //shake - used to be 0
        wait(3);
        marker.setPosition(1); //shacky shake - used to be 1
        wait(3);
        marker.setPosition(0); //shake - used to be 0
        wait(3);
        marker.setPosition(1); //shacky shake shake - used to be 1
        wait(3);
        marker.setPosition(0); //shake - used to be 0
        wait(9);

        stopRobot();
    }

    public void stopRobot(){
        drive(0,0,0,0);
        wait(3);
    }

    public void wait(int time) {
        try {
            Thread.sleep(time * 100);//milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    public void drive(double leftFrontPower, double leftBackPower, double rightFrontPower, double rightBackPower)
    {
        leftFront.setPower(leftFrontPower);
        leftBack.setPower(leftBackPower);
        rightFront.setPower(rightBackPower);
        rightBack.setPower(rightBackPower);

    }
}