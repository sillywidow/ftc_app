package org.firstinspires.ftc.team8200;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    // Universal OpMode Properties
    public DcMotor elevator;
    public DcMotor frontMotorLeft;
    public DcMotor frontMotorRight;
    public DcMotor backMotorLeft;
    public DcMotor backMotorRight;
    public DcMotor harvesterMotorLeft;
    public DcMotor harvesterMotorRight;

    public Servo arm;
    public Servo harvesterServoLeft;
    public Servo harvesterServoRight;
    public Servo holdTopLeft;
    public Servo holdTopRight;
    public Servo holdBottomLeft;
    public Servo holdBottomRight;

    // Local OpMode Properties
    HardwareMap hwMap;

    // Constructor
    public Hardware() {}

    // Initialize Hardware Interfaces
    public void init(HardwareMap hardwareMap) {
        // Reference the hardware map
        hwMap = hardwareMap;

        // Names for Hardware Configuration
        elevator = hwMap.get(DcMotor.class, "elevator");
        frontMotorLeft = hwMap.get(DcMotor.class, "frontMotorLeft");
        frontMotorRight = hwMap.get(DcMotor.class, "frontMotorRight");
        backMotorLeft = hwMap.get(DcMotor.class, "backMotorLeft");
        backMotorRight = hwMap.get(DcMotor.class, "backMotorRight");
        harvesterMotorLeft = hwMap.get(DcMotor.class, "harvesterMotorLeft");
        harvesterMotorRight = hwMap.get(DcMotor.class, "harvesterMotorRight");

        arm = hwMap.get(Servo.class, "arm");
        harvesterServoLeft = hwMap.get(Servo.class, "harvesterServoLeft");
        harvesterServoRight = hwMap.get(Servo.class, "harvesterServoRight");
        holdTopLeft = hwMap.get(Servo.class, "holdTopLeft");
        holdTopRight = hwMap.get(Servo.class, "holdTopRight");
        holdBottomLeft = hwMap.get(Servo.class, "holdBottomLeft");
        holdBottomRight = hwMap.get(Servo.class, "holdBottomRight");

        // Motor Direction
        frontMotorLeft.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        backMotorLeft.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        frontMotorRight.setDirection(DcMotor.Direction.REVERSE); // Set to FORWARD if using AndyMark motors
        backMotorRight.setDirection(DcMotor.Direction.REVERSE); // Set to FORWARD if using AndyMark motors

        // Set motors and servos to initial position
        elevator.setPower(0);
        frontMotorLeft.setPower(0);
        frontMotorRight.setPower(0);
        backMotorLeft.setPower(0);
        backMotorRight.setPower(0);
        harvesterMotorLeft.setPower(0);
        harvesterMotorRight.setPower(0);
        arm.setPosition(0);

        harvesterServoLeft.setPosition(0);
        harvesterServoLeft.setPosition(0);
        holdTopLeft.setPosition(0);
        holdTopRight.setPosition(0);
        holdBottomLeft.setPosition(0);
        holdBottomRight.setPosition(0);

        // Enable encoders
        backMotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backMotorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
