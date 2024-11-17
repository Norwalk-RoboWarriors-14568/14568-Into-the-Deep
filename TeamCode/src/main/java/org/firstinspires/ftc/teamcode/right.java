package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "right")
public class right extends LinearOpMode {

    DcMotor frontLeftMotor,backLeftMotor, frontRightMotor, backRightMotor, verticalMotor, extentionMotor;
    CRServo intakeServo;
    Servo headServo;
    Automethods automethods;
    private void initalize() {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeServo = hardwareMap.get(CRServo.class, "Intake");
        headServo = hardwareMap.get(Servo.class, "Head");
        headServo.setPosition(0.1089);
        verticalMotor = hardwareMap.get(DcMotor.class, "Pivot");
        extentionMotor = hardwareMap.get(DcMotor.class, "Extension");
        verticalMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        extentionMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verticalMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extentionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        automethods = new Automethods(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor, extentionMotor, verticalMotor, intakeServo, headServo, telemetry);
    }
    @Override
    public void runOpMode() throws InterruptedException {
        initalize();
        sleep(2000);
        intakeServo.setPower(-.2);
        sleep(1000);
        intakeServo.setPower(0);
        while (!opModeIsActive()){}

        automethods.Arm(0.1089, 10, 0, 0.5, true);
        sleep(1000);
        headServo.setPosition(0.6589);
        sleep(1000);
        automethods.Arm(0.6589, 61.343639, 6, 0.5, true);
        sleep(2000);
        automethods.DriveStraight(20, 0.5, true);
        sleep(2000);
        automethods.Arm(0.6589, 20, 0, 0.5, false);
        sleep(1000);
        automethods.DriveStraight(-2,0.5,true);
        intakeServo.setPower(-.5);
        sleep(1000);
        automethods.Arm(0.6589, 10, -6, 0.5, false);
        sleep(1000);
        automethods.Turn(42,0.5,false);
        automethods.DriveStraight(-24,0.5,true);
        //automethods.Turn(90,0.5, true);
        //sleep(1000);
        //automethods.DriveStraight(72, 0.5);
        //sleep(1000);
        //automethods.Strafe(79, 0.5, true);
        //sleep(1000);
        //automethods.DriveStraight(-96, 0.5);
        //sleep(1000);
        //automethods.Strafe(12, 0.5, true);
    }

}
