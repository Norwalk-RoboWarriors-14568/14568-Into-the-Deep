/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/*
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@TeleOp(name="Basic: Iterative OpMode", group="Iterative OpMode")

public class TeleOpPOV extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftMotor,backLeftMotor, frontRightMotor, backRightMotor, verticalMotor, extentionMotor;
    private CRServo intakeServo;
    private Servo headServo;

    private boolean gp1rBumper = false;
    private int buffer = 25;
    /*
     * Code to run ONCE when the driver hits INIT
     */

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

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
        //leftDrive.setDirection(DcMotor.Direction.REVERSE);
        //rightDrive.setDirection(DcMotor.Direction.FORWARD);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verticalMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extentionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");
    }


    @Override
    public void init_loop() {
    }


    @Override
    public void start() {
        runtime.reset();
    }


    @Override
    public void loop() {
        if (gamepad1.left_bumper) {
            gp1rBumper = false;
            verticalMotor.setPower(0);
            extentionMotor.setPower(0);
            verticalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            extentionMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        if (gp1rBumper){
            verticalMotor.setTargetPosition(2556);
            verticalMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            verticalMotor.setPower(0.5);
            extentionMotor.setTargetPosition(2811);
            extentionMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            extentionMotor.setPower(0.5);

            if(verticalMotor.getCurrentPosition()>verticalMotor.getTargetPosition()-buffer &&
                    verticalMotor.getCurrentPosition()<verticalMotor.getTargetPosition()+buffer &&
            extentionMotor.getCurrentPosition()>extentionMotor.getTargetPosition()-buffer &&
                    extentionMotor.getCurrentPosition()<extentionMotor.getTargetPosition()+buffer){
                verticalMotor.setPower(0);
                extentionMotor.setPower(0);
                verticalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                extentionMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                gp1rBumper = false;
            }
        }
        else {
            if (gamepad1.right_bumper) gp1rBumper = true;

            if (gamepad2.right_trigger > 0.1  ) intakeServo.setPower(gamepad2.right_trigger);
            else if (gamepad2.left_trigger > 0.1  ) intakeServo.setPower(-gamepad2.left_trigger);
            else intakeServo.setPower(0);

            if (gamepad2.dpad_right) headServo.setPosition(headServo.getPosition()-0.005);
            else if (gamepad2.dpad_left) headServo.setPosition(headServo.getPosition()+0.005);
            else headServo.setPosition(headServo.getPosition());

            int vertTicks = verticalMotor.getCurrentPosition();
            if (gamepad2.left_bumper) verticalMotor.setPower(.05);
            else if (vertTicks > 3374) verticalMotor.setPower(-.1);
            else if (gamepad2.right_stick_y > 0.1 ||gamepad2.right_stick_y < -0.1 ) verticalMotor.setPower(-gamepad2.right_stick_y);
            else verticalMotor.setPower(0);

            int extTicks = extentionMotor.getCurrentPosition();
            if (vertTicks > 3274 && extTicks > 100) extentionMotor.setPower(-0.75);
            else if (vertTicks < 2500 && extTicks > 2300) extentionMotor.setPower(-0.75);
            else if (extTicks < 0 ) extentionMotor.setPower(.5);
            else if (extTicks > 2900 ) extentionMotor.setPower(-.5);
            else if (extTicks >= 0 && extTicks <= 2900 && gamepad2.left_stick_y > 0.1 || gamepad2.left_stick_y < -0.1 ) extentionMotor.setPower(-gamepad2.left_stick_y);
            else extentionMotor.setPower(0);
        }
        double max;

        // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
        double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
        double lateral =  gamepad1.left_stick_x;
        double yaw     =  gamepad1.right_stick_x;

        double leftFrontPower  = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower   = axial - lateral + yaw;
        double rightBackPower  = axial + lateral - yaw;

        double leftFrontSignal = leftFrontPower > 0? 1:-1;
        double rightFrontSignal = rightFrontPower > 0? 1:-1;
        double leftBackSignal = leftBackPower > 0? 1:-1;
        double rightBackSignal = rightBackPower > 0? 1:-1;

        leftFrontPower =    Math.pow(leftFrontPower, 2)  * leftFrontSignal;
        rightFrontPower =    Math.pow(rightFrontPower, 2)  *rightFrontSignal;
        leftBackPower =    Math.pow(leftBackPower, 2)  * leftBackSignal;
        rightBackPower =    Math.pow(rightBackPower, 2)  *rightBackSignal;

        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }


        //leftFrontPower  = gamepad1.x ? 1.0 : 0.0;  // X gamepad
        //leftBackPower   = gamepad1.a ? 1.0 : 0.0;  // A gamepad
        //rightFrontPower = gamepad1.y ? 1.0 : 0.0;  // Y gamepad
        // rightBackPower  = gamepad1.b ? 1.0 : 0.0;  // B gamepad


        double powerPrecentage = .75;
        frontLeftMotor.setPower(leftFrontPower * powerPrecentage);
        frontRightMotor.setPower(rightFrontPower * powerPrecentage);
        backLeftMotor.setPower(leftBackPower * powerPrecentage);
        backRightMotor.setPower(rightBackPower * powerPrecentage);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
        telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
        telemetry.addData("headServo", headServo.getPosition());
        telemetry.addData("extensionMotor", extentionMotor.getCurrentPosition());
        telemetry.addData("verticalMotor", verticalMotor.getCurrentPosition());
        telemetry.addData("frontLeftMotor", frontLeftMotor.getCurrentPosition());
        telemetry.addData("frontRightMotor", frontRightMotor.getCurrentPosition());
        telemetry.addData("backLeftMotor", backLeftMotor.getCurrentPosition());
        telemetry.addData("backRightMotor", backRightMotor.getCurrentPosition());
        telemetry.update();



    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}