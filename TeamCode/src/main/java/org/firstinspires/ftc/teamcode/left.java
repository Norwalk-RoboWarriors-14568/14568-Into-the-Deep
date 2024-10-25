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
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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
        automethods.DriveStraight(24, 0.5);
    }

}
