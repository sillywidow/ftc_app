package org.firstinspires.ftc.team8200;

import android.graphics.Color;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.Locale;

@Autonomous(name = "PlaceCube", group = "Autonomous")
public class Auto_PlaceCube extends LinearOpMode {
    // Import objects used in robot
    Hardware robot = new Hardware();
    VuforiaLocalizer vuforia;
    ColorSensor colorSensor;
    DistanceSensor distanceSensor;
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

    // Static variables for color sensor
    // Change these depending on what color this opmode will function for // Erase this comment after values are final
    static final int RED_THRESHOLD = 100;
    static final int BLUE_THRESHOLD = 100;
    static final int GREEN_THRESHOLD = 100;

    @Override
    public void runOpMode() {
        // Initialize Hardware
        robot.init(hardwareMap);

        // Reset encoders
        stopAndResetEncoders();

        // Wait for "PLAY" to be pressed
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

    // TODO Mention how we might need to add a method for picking up the cube when the match starts

    // Read and store the value of the pattern
    public void readPattern() {readVuMark();}

    // Move forward to Gems
    public void moveToGems() {}

    // Scan Gems' colors
    public void scanGemColor() {readColor();}

    // Push correct Gem
    public void pushGem() {
        if (readColor().equals("red")) {

        } else if (readColor().equals("blue")) {

        }
    }

    // Rotate to Cryptobox
    public void alignToCryptobox() {}

    // Find correct column
    public void findColumn() {
        if (readVuMark().equals("left")) {

        } else if (readVuMark().equals("center")) {

        } else if (readVuMark().equals("right")) {

        }
    }

    // Drop Glyph
    public void dropGlyph() {
        robot.claw.setPosition(0);
    }

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

    public String readVuMark() {
        String vuMarkPattern = "";
        // Store parameters used to initialize the Vuforia engine
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        /* UNCOMMENT THIS IF WANTING TO TEST WITH A VIEW ON SCREEN AND COMMENT LINE ABOVE
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        */

        // Add license key
        parameters.vuforiaLicenseKey = "ARH6A3z/////AAAAGZ4wVweTjU73lDoZeCr/rzwpfNAFaWSGUn4qhsRk/g7XznybiUzddzhqWfAWncGfPY8Q8fqY3lozXjMIMdWiZYPQkmYSmb2NkIry1JizLHG3PvtS5yr3fYCT0Tpia25pg03b3lQeoEVYRQUTnAFXQnO4wSwGOmz2wWWMg0rNDBN6gxnUipEKrLaLajvGvwtmkl/EB0P3Rib3zTgQzJXxgi3nHVV4m06LZ3twCd0l4p4EA7W2Js1V+iR7ue94ObAH4FUfJ0qcOsnlnM+DDq5LdJOAP5HbgldfzsncBeqyRA8O4u4TZ6ABu+4u8T1T/tY1dG7doWkIDjFD/z40F4bEQYGrEo1VuEnsURpIZugF9Ahc";

        // Select the camera the robot will use
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        // Start Vuforia engine with the 2 parameters that were taken
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        // Load tracking dataset
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);

        // Activate tracker
        relicTrackables.activate();

        while (opModeIsActive()) {
            // Variable that stores value from what's seen
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

            if (vuMark == RelicRecoveryVuMark.LEFT) {
                vuMarkPattern = "left";
                telemetry.addData("VuMark", "%s visible", vuMark);
                telemetry.update();
            } else if (vuMark == RelicRecoveryVuMark.CENTER) {
                vuMarkPattern = "left";
                telemetry.addData("VuMark", "%s visible", vuMark);
                telemetry.update();
            } else if (vuMark == RelicRecoveryVuMark.RIGHT) {
                vuMarkPattern = "right";
                telemetry.addData("VuMark", "%s visible", vuMark);
                telemetry.update();
            } else {
                vuMarkPattern = "";
            }
        }
        telemetry.addData("VuMark", "%s visible", vuMarkPattern);
        telemetry.update();
        return vuMarkPattern;
    }

    public String readColor() {
        // Store found color
        String color = "none";

        // Store HSV Values
        float hsvValues[] = {0F, 0F, 0F};

        // Scale to convert RGB to HSV
        final double SCALE_FACTOR = 255;

        while (opModeIsActive()) {
            // Convert from RGB to HSV
            Color.RGBToHSV((int) (colorSensor.red() * SCALE_FACTOR), (int) (colorSensor.green() * SCALE_FACTOR), (int) (colorSensor.blue() * SCALE_FACTOR), hsvValues);

            // Show values at Driver Station
            telemetry.addData("Distance (cm)", String.format(Locale.US, "%.02f", distanceSensor.getDistance(DistanceUnit.CM)));
            telemetry.addData("Alpha", colorSensor.alpha());
            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.addData("Saturation", hsvValues[1]);
            telemetry.addData("Value", hsvValues[2]);
            telemetry.update();

            // Considered adding a timer just to confirm that the color is accurate
            // TODO talk with the team to make sure this approach with time is agreed upon
            runtime.reset();
            if (runtime.seconds() > 2 && colorSensor.red() > RED_THRESHOLD && colorSensor.green() < GREEN_THRESHOLD && colorSensor.blue() < BLUE_THRESHOLD) { // Condition for RED
                color = "red";
            } else if (runtime.seconds() > 2 && colorSensor.red() < RED_THRESHOLD && colorSensor.green() < GREEN_THRESHOLD && colorSensor.blue() > BLUE_THRESHOLD) { // Condition for BLUE
                color = "blue";
            }
        }
        return color;
    }
}
