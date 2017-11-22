package org.firstinspires.ftc.team8200;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    // Universal OpMode Properties
    public DcMotor frontMotorLeft;
    public DcMotor frontMotorRight;
    public DcMotor backMotorLeft;
    public DcMotor backMotorRight;
    public DcMotor collectorMotorLeft;
    public DcMotor collectorMotorRight;
    public DcMotor elevator;
    public DcMotor arm;
    public Servo collectorServoLeft;
    public Servo collectorServoRight;
    // Create 4 servos for gripper

    // Local OpMode Properties
    HardwareMap hwMap;

    // Constructor
    public Hardware() {}

    // Initialize Hardware Interfaces
    public void init(HardwareMap hardwareMap) {
        // Reference the hardware map
        hwMap = hardwareMap;

        // Names for Hardware Configuration
        frontMotorLeft = hwMap.get(DcMotor.class, "frontmotorleft");
        frontMotorRight = hwMap.get(DcMotor.class, "frontmotorright");
        backMotorLeft = hwMap.get(DcMotor.class, "backmotorleft");
        backMotorRight = hwMap.get(DcMotor.class, "backmotorright");
        collectorMotorLeft = hwMap.get(DcMotor.class, "collectormotorleft");
        collectorMotorRight = hwMap.get(DcMotor.class, "collectormotorright");
        elevator = hwMap.get(DcMotor.class, "elevator");
        arm = hwMap.get(DcMotor.class, "arm");
        collectorServoLeft = hwMap.get(Servo.class, "collectorservoleft");
        collectorServoRight = hwMap.get(Servo.class, "collectorservoright");

        // Motor Direction
        frontMotorLeft.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        backMotorLeft.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        frontMotorRight.setDirection(DcMotor.Direction.REVERSE); // Set to FORWARD if using AndyMark motors
        backMotorRight.setDirection(DcMotor.Direction.REVERSE); // Set to FORWARD if using AndyMark motors

        // Set motors and servos to initial position
        frontMotorLeft.setPower(0);
        frontMotorRight.setPower(0);
        backMotorLeft.setPower(0);
        backMotorRight.setPower(0);
        collectorMotorLeft.setPower(0);
        collectorMotorRight.setPower(0);
        elevator.setPower(0);
        arm.setPower(0);
        collectorServoLeft.setPosition(0);
        collectorServoRight.setPosition(0);

        // Enable encoders
        backMotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backMotorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
