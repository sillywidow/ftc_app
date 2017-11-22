package org.firstinspires.ftc.team8200;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Default", group="TeleOp")
public class Tele_Default extends LinearOpMode {
    // Import objects used in robot
    Hardware robot = new Hardware();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        // Initialize Hardware
        robot.init(hardwareMap);

        // Drive variables
        double leftSpeed, rightSpeed;

        // Wait for "PLAY" to be pressed
        waitForStart();

        // Run until "STOP" is pressed
        while (opModeIsActive()) {
            // Drive speeds
            leftSpeed = -gamepad1.left_stick_y;
            rightSpeed = -gamepad1.right_stick_y;
            robot.frontMotorLeft.setPower(leftSpeed);
            robot.frontMotorRight.setPower(rightSpeed);
            
            // Pause for 40 mS each cycle = update 25 times a second.
            sleep(40);
        }
    }
}
