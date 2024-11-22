package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Automethods {

    double TicsPerRevolution = 537.6;

    double Circumfrence = 12.85516;

    double TPI = TicsPerRevolution / Circumfrence;

    double StrafeTPI = 48.7804878;

    double TPD = 11.9;

    double extTPI = 123.07692;

    double pivotTPD = 27.7777778;
    int leftTarget1 = 0, leftTarget2 = 0, rightTarget1 = 0, rightTarget2 = 0, target1 = 0, target2 = 0, target3 = 0, buffer = 10;

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
    public void DriveStraight (double inches, double power, boolean b){
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
        ZeroMotors();    }
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
    public void Strafe(double inches, double power, boolean isRight) {
        int  ticks = (int) (StrafeTPI * inches);
       if (isRight){
           leftTarget1 = frontLeftMotor.getCurrentPosition()+ticks;
           leftTarget2 = backLeftMotor.getCurrentPosition()-ticks;
           rightTarget1 = frontRightMotor.getCurrentPosition()-ticks;
           rightTarget2 = backRightMotor.getCurrentPosition()+ticks;
       }
       else {
           leftTarget1 = frontLeftMotor.getCurrentPosition()-ticks;
           leftTarget2 = backLeftMotor.getCurrentPosition()+ticks;
           rightTarget1 = frontRightMotor.getCurrentPosition()+ticks;
           rightTarget2 = backRightMotor.getCurrentPosition()-ticks;
       }
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
    public void Turn(double degrees, double power, boolean isRight) {
        int  ticks = (int) (TPD * degrees);
        if (isRight){
            leftTarget1 = frontLeftMotor.getCurrentPosition()+ticks;
            leftTarget2 = backLeftMotor.getCurrentPosition()+ticks;
            rightTarget1 = frontRightMotor.getCurrentPosition()-ticks;
            rightTarget2 = backRightMotor.getCurrentPosition()-ticks;
        }
        else {
            leftTarget1 = frontLeftMotor.getCurrentPosition()-ticks;
            leftTarget2 = backLeftMotor.getCurrentPosition()-ticks;
            rightTarget1 = frontRightMotor.getCurrentPosition()+ticks;
            rightTarget2 = backRightMotor.getCurrentPosition()+ticks;
        }

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

    public void ZeroArmMotors() {
        pivotMotor.setPower(0);
        extensionMotor.setPower(0);
    }

    public void HoldArmMotors() {
        pivotMotor.setPower(0.01);
        extensionMotor.setPower(0);
    }

    private boolean ArmAtTarget() {
        return (pivotMotor.getCurrentPosition() > (target1 - buffer) &&
                pivotMotor.getCurrentPosition() < (target1 + buffer)) &&
                (extensionMotor.getCurrentPosition() > (target2 - buffer) &&
                       extensionMotor.getCurrentPosition() < (target2 + buffer));

    }

    public void Arm(double headPosition, double degrees, double inches, double power, boolean isUp) {
        int extTicks = (int) (extTPI * inches);
        target2 = extensionMotor.getCurrentPosition() + extTicks;
        int pivotTicks = (int) (pivotTPD * degrees);
        if (isUp) {
            target1 = pivotMotor.getCurrentPosition() + pivotTicks;
        } else {
            target1 = pivotMotor.getCurrentPosition() - pivotTicks;
        }

        pivotMotor.setTargetPosition(target1);
        extensionMotor.setTargetPosition(target2);
        pivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        extensionMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        headServo.setPosition(headPosition);
        while (!ArmAtTarget()) {
            telemetry.addData("pivotMotor", pivotMotor.getCurrentPosition());
            telemetry.addData("extensionMotor", extensionMotor.getCurrentPosition());
            telemetry.update();
            pivotMotor.setPower(power);
            extensionMotor.setPower(power);

        }
        HoldArmMotors();
    }
    public void Head( double power, double position) {

        //target2 = extensionMotor.getCurrentPosition() + extTicks;
       //headServo.getCurrentPosition() - pivotTicks;


        }
}