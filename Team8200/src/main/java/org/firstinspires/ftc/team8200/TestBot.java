package org.firstinspires.ftc.team8200;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Test TeleOp", group = "Testing")
public class TestBot extends LinearOpMode {
    // Declare Robot's Hardware
    Hardware robot = new Hardware();

    @Override
    public void runOpMode() {
        // Motor Power Variables
        double leftMotorPower;
        double rightMotorPower;

        // Initialize Hardware
        robot.init(hardwareMap);

        // Test Telemetry
        telemetry.addData("Say", "I'm working");
        telemetry.update();

        // Wait for "PLAY"
        waitForStart();

        // Run until "STOP"
        while (opModeIsActive()) {
            // Set Power to Push of Joystick(Joystick goes negative when pushed forward, value must be negated)
            leftMotorPower = -gamepad1.left_stick_y;
            robot.leftMotor.setPower(leftMotorPower);
            rightMotorPower = -gamepad1.right_stick_y;
            robot.rightMotor.setPower(rightMotorPower);
        }
    }
}