package org.firstinspires.ftc.team8200;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "PlaceCube", group = "Autonomous")
public class Auto_PlaceCube extends LinearOpMode {
    Hardware robot = new Hardware();
    private ElapsedTime runtime = new ElapsedTime();

    // Static variables for general use
    static final double DRIVE_SPEED = 0.5;
    static final double DRIVE_SLOW_SPEED = 0.1;
    static final double TURN_SPEED = 1;

    // Static variables for encoders
    static final double COUNTS_PER_MOTOR_REV = 280; // Source: NeveRest 40 Specifications Sheet
    static final double DRIVE_GEAR_REDUCTION = 40.0;
    static final double WHEEL_DIAMETER_INCHES = 2.8; // For figuring circumference
    static final double PI = 3.1415;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * PI);

    @Override
    public void runOpMode() {
        // Initialize Hardware
        robot.init(hardwareMap);

        // Reset encoders
        stopAndResetEncoders();

        // Wait for "PLAY"
        waitForStart();

        // Run methods in sequence
        readPattern();
        moveToGems();
        scanGemColor();
        pushGem();
        alignToCryptobox();
        findColumn();
        dropGlyph();
    }

    // Read and store the value of the pattern
    public void readPattern() {}

    // Move forward to Gems
    public void moveToGems() {}

    // Scan Gems' colors
    public void scanGemColor() {}

    // Push correct Gem
    public void pushGem() {}

    // Rotate to Cryptobox
    public void alignToCryptobox() {}

    // Find correct column
    public void findColumn() {}

    // Drop Glyph
    public void dropGlyph() {}

    // Encoder enabler
    public void move(double speed, double leftInches, double rightInches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.leftMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.rightMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            robot.leftMotor.setTargetPosition(newLeftTarget);
            robot.rightMotor.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Reset the timeout time and start motion
            runtime.reset();
            robot.leftMotor.setPower(Math.abs(speed));
            robot.rightMotor.setPower(Math.abs(speed));

            // Stop all motion
            robot.leftMotor.setPower(0);
            robot.rightMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    // Reset encoders and kill motors
    public void stopAndResetEncoders() {
        robot.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sleep(50); // Wait 50ms to make sure it fully processes

        robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // Rotate with encoders ( > 0 goes CW, < 0 goes CCW )
    public void turn(double degrees) {
        stopAndResetEncoders(); // Reset encoders
        double motorInches = degrees * .1;
        if (degrees > 0) { // CW turns
            move(TURN_SPEED, motorInches, -motorInches, 5000);
        }
        else if (degrees < 0) { // CCW turns
            move(TURN_SPEED, -motorInches, motorInches, 5000);
        }
    }
}
