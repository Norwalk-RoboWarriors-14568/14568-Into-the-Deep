package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Automethods {

    double TicsPerRevolution = 537.6;

    double Circumfrence = 12.85516;

    double TPI = TicsPerRevolution / Circumfrence;

    double StrafeTPI = 0;

    int leftTarget1 = 0, leftTarget2 = 0, rightTarget1 = 0, rightTarget2 = 0, buffer = 10;

    private DcMotor frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor, extensionMotor, pivotMotor;
    private CRServo intakeServo;
    private Servo headServo;


    private Telemetry telemetry;


    public Automethods(DcMotor left, DcMotor left2, DcMotor right, DcMotor right2, DcMotor extension, DcMotor pivot, CRServo intake, Servo head, Telemetry telemetryin ) {
        frontLeftMotor = left;
        backLeftMotor = left2;
        frontRightMotor = right;
        backRightMotor = right2;
        extensionMotor = extension;
        pivotMotor = pivot;
        intakeServo = intake;
        headServo = head;
        telemetry = telemetryin;
    }
    public void DriveStraight (double inches, double power){
        int  ticks = (int) (inches * TPI);
        leftTarget1 = frontLeftMotor.getCurrentPosition()+ticks;
        leftTarget2 = backLeftMotor.getCurrentPosition()+ticks;
        rightTarget1 = frontRightMotor.getCurrentPosition()+ticks;
        rightTarget2 = backRightMotor.getCurrentPosition()+ticks;
        frontLeftMotor.setTargetPosition(leftTarget1);
        backLeftMotor.setTargetPosition(leftTarget2);
        frontRightMotor.setTargetPosition(rightTarget1);
        backRightMotor.setTargetPosition(rightTarget2);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(!AtTarget() ){
            telemetry.addData("frontLeftMotor",frontLeftMotor.getCurrentPosition());
            telemetry.addData("backLeftMotor",backLeftMotor.getCurrentPosition());
            telemetry.addData("frontRightMotor",frontRightMotor.getCurrentPosition());
            telemetry.addData("backRightMotor",backRightMotor.getCurrentPosition());
            telemetry.update();
            frontLeftMotor.setPower(power);
            backLeftMotor.setPower(power);
            frontRightMotor.setPower(power);
            backRightMotor.setPower(power);
        }
        ZeroMotors();
    }
    private boolean AtTarget() {
        return (frontLeftMotor.getCurrentPosition() > (leftTarget1 - buffer) &&
                         frontLeftMotor.getCurrentPosition() < (leftTarget1 + buffer)) &&
                (backLeftMotor.getCurrentPosition() > (leftTarget2 - buffer) &&
                        backLeftMotor.getCurrentPosition() < (leftTarget2 + buffer)) &&
                (frontRightMotor.getCurrentPosition() > (rightTarget1 - buffer) &&
                        frontRightMotor.getCurrentPosition() < (rightTarget1 + buffer)) &&
                (backRightMotor.getCurrentPosition() > (rightTarget2 - buffer) &&
                        backRightMotor.getCurrentPosition() < (rightTarget2 + buffer));

    }

    public void ZeroMotors(){
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
    }
    public void Straph (double inches, double power, boolean isRight) {
        int  ticks = 1000;
       if (isRight){

       }
    }
}