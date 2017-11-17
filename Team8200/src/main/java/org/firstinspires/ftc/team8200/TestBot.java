package org.firstinspires.ftc.team8200;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TestBot", group="Testing")
public class TestBot extends LinearOpMode {

    // Import objects used in robot
    Hardware robot = new Hardware();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Set motors and servos to initial position
        double servoPosition = 0;
        double shortArmPower = 0;
        double longArmPower = 0;

        // Initialize Hardware
        robot.init(hardwareMap);

        // Wait for "PLAY" to be pressed
        waitForStart();
        runtime.reset();

        // Run until "STOP" is pressed
        while (opModeIsActive()) {
            if (gamepad1.right_stick_y != 0) {
                shortArmPower = gamepad1.right_stick_y;
                longArmPower = gamepad1.left_stick_y;
                shortArmPower = Range.clip(shortArmPower, -0.3, 0.3);
                longArmPower = Range.clip(longArmPower, -0.3, 0.3);
            }
            if (gamepad1.left_stick_x > 0) {
                servoPosition += 0.05;
            } else if (gamepad1.left_stick_x < 0) {
                servoPosition -= 0.05;
            }

            // Send calculated power to wheels
            robot.shortArm.setPower(shortArmPower);
            robot.longArm.setPower(longArmPower);

            robot.claw.setPosition(servoPosition);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "shortArm (%.2f), longArm (%.2f)", shortArmPower, longArmPower);
            telemetry.update();
        }
    }
}
