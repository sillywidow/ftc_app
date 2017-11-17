package org.firstinspires.ftc.team8200;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    // Universal OpMode Properties
    public DcMotor leftMotor;
    public DcMotor rightMotor;
    public DcMotor shortArm;
    public DcMotor longArm;
    public Servo claw;
    public ColorSensor colorSensor;
    public DistanceSensor distanceSensor;

    // Local OpMode Properties
    HardwareMap hwMap;

    // Constructor
    public Hardware() {}

    // Initialize Hardware Interfaces
    public void init(HardwareMap hardwareMap) {
        // Reference the hardware map
        hwMap = hardwareMap;

        // Names for Hardware Configuration
        leftMotor = hwMap.get(DcMotor.class, "leftmotor");
        rightMotor = hwMap.get(DcMotor.class, "rightmotor");
        shortArm = hwMap.get(DcMotor.class, "shortarm");
        longArm = hwMap.get(DcMotor.class, "longarm");
        claw = hwMap.get(Servo.class, "claw");
        colorSensor = hwMap.get(ColorSensor.class, "sensor");
        distanceSensor = hwMap.get(DistanceSensor.class, "sensor");

        // Motor Direction
        leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightMotor.setDirection(DcMotor.Direction.REVERSE); // Set to FORWARD if using AndyMark motors

        // Set motors and servos to initial position
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        shortArm.setPower(0);
        longArm.setPower(0);
        claw.setPosition(0);

        // Enable encoders
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
