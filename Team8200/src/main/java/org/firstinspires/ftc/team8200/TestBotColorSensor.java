package org.firstinspires.ftc.team8200;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

@Disabled
@TeleOp(name="AutoColorSensor", group="Autonomous")
public class TestBotColorSensor extends LinearOpMode {

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

            //if ()

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
