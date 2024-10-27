package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "left")

public class left extends LinearOpMode {

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
        headServo.setPosition(0.5);
        verticalMotor = hardwareMap.get(DcMotor.class, "Extension");
        extentionMotor = hardwareMap.get(DcMotor.class, "Pivot");

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        automethods = new Automethods(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor, verticalMotor, extentionMotor, intakeServo, headServo, telemetry);
    }
    @Override
    public void runOpMode() throws InterruptedException {
        initalize();
        while (!opModeIsActive()){}

        automethods.Strafe(24, 0.5, false);
        sleep(1000);
        automethods.DriveStraight(107, 0.5);
        sleep(1000);
        automethods.Turn(90,0.5, true);
        sleep(1000);
        automethods.DriveStraight(72, 0.5);
        sleep(1000);
        automethods.Strafe(79, 0.5, true);
        sleep(1000);
        automethods.DriveStraight(-96, 0.5);
        sleep(1000);
        automethods.Strafe(12, 0.5, true);
    }

}
