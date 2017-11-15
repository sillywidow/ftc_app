package org.firstinspires.ftc.team8200;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Hardware {
    // Universal OpMode Properties
    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;
    public DcMotor shortArm = null;
    public DcMotor longArm = null;
    public Servo claw = null;

    // Local OpMode Properties
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();
    public final static double ARM_HOME = .2;
    public final static double CLAW_HOME = .2;
    public final static double ARM_MIN_RANGE = .2;
    public final static double ARM_MAX_RANGE = .9;
    public final static double CLAW_MIN_RANGE = .2;
    public final static double CLAW_MAX_RANGE = .7;

    // Constructor
    public Hardware() {}

    // Initialize Hardware Interfaces
    public void init(HardwareMap hardwareMap) {
        hwMap = hardwareMap;

        // Names for Hardware Configuration
        leftMotor = hwMap.get(DcMotor.class, "leftmotor");
        rightMotor = hwMap.get(DcMotor.class, "rightmotor");
        shortArm = hwMap.get(DcMotor.class, "shortarm");
        longArm = hwMap.get(DcMotor.class, "longarm");
        claw = hwMap.get(Servo.class, "claw");

        // Motor Direction
        leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightMotor.setDirection(DcMotor.Direction.REVERSE); // Set to FORWARD if using AndyMark motors

        // Set motors and servos to initial position
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        shortArm.setPower(0);
        longArm.setPower(0);
        claw.setPosition(CLAW_HOME);

        // Set all motors to run without encoders unless they're being used
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
