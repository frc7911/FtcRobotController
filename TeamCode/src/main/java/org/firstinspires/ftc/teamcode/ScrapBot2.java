package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "ScrapBot2 (Blocks to Java)")
public class ScrapBot2 extends LinearOpMode {

    private DcMotor rightMotor;
    private DcMotor leftMotor;
    private DcMotor corehexmotor;
    private DcMotor Lift;
    private Servo servo1;
    private Servo servo2;
    private Servo servo3;


    /**
     * This OpMode offers Tank Drive style TeleOp control for a direct drive robot.
     *
     * In this Tank Drive mode, the left and right joysticks (up
     * and down) drive the left and right motors, respectively.
     */
    @Override
    public void runOpMode() {
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        corehexmotor = hardwareMap.get(DcMotor.class, "core hex motor");
        Lift = hardwareMap.get(DcMotor.class, "Lift");
        servo1 = hardwareMap.get(Servo.class, "servo 1");
        servo2 = hardwareMap.get(Servo.class, "servo 2");
        servo3 = hardwareMap.get(Servo.class, "servo 3");


        // Reverse one of the drive motors.
        // You will have to determine which motor to reverse for your robot.
        // In this example, the right motor was reversed so that positive
        // applied power makes it move the robot in the forward direction.
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        corehexmotor.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                // The Y axis of a joystick ranges from -1 in its topmost position to +1 in its bottommost position.
                // We negate this value so that the topmost position corresponds to maximum forward power.
                leftMotor.setPower(-gamepad1.left_stick_y);
                rightMotor.setPower(-gamepad1.right_stick_y);
                corehexmotor.setPower(-gamepad1.touchpad_finger_1_x);
                Lift.setDirection(DcMotor.Direction.REVERSE);
                Lift.setPower(-gamepad1.right_trigger);
                Lift.setDirection(DcMotor.Direction.FORWARD);
                Lift.setPower(-gamepad1.left_trigger);
                servo1.setDirection(Servo.Direction.REVERSE);

                // Try setPower instead of setPosition                      -Vincent Scrapcat Robotics

                servo1.setPosition(gamepad1.left_stick_x);
                servo2.setPosition(gamepad1.touchpad_finger_1_x);
                servo3.setPosition(gamepad1.touchpad_finger_1_y);
            }
            telemetry.addData("Left Pow", leftMotor.getPower());
            telemetry.addData("Right Pow", rightMotor.getPower());
            telemetry.update();
            ((DcMotorEx) Lift).setVelocity(Lift.getPower(), AngleUnit.DEGREES);

        }
    }
}