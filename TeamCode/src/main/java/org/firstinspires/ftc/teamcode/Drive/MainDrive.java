package org.firstinspires.ftc.teamcode.Drive;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@TeleOp (name = "MainDrive")
public final class MainDrive extends LinearOpMode {


    @Override
    public void runOpMode() {

        Pose2d zeroPose = new Pose2d(0, 0, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, zeroPose);

        TeleOperatedDrive botDrive = new TeleOperatedDrive(gamepad1);
        AutonomousDrive autoDrive = new AutonomousDrive(drive);

        GamepadBooleanHandling gamepadBooleanHandler = new GamepadBooleanHandling(gamepad1, gamepad2);

        DcMotor left_front, right_front, left_back, right_back;

        left_front = hardwareMap.get(DcMotor.class, "lf");
        right_front = hardwareMap.get(DcMotor.class, "rf");
        left_back = hardwareMap.get(DcMotor.class, "lb");
        right_back = hardwareMap.get(DcMotor.class, "rb");

        left_front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        left_front.setDirection(DcMotor.Direction.REVERSE);
        left_back.setDirection(DcMotor.Direction.REVERSE);

        if (isStopRequested()) return;
        waitForStart();

        while (opModeIsActive()) {

            //handling gamepad booleans
            gamepadBooleanHandler.handle();

            //handling drive
            autoDrive.autonomousDrive(gamepadBooleanHandler);
            botDrive.teleOperatedDrive(left_front, right_front, left_back, right_back);
        }

        autoDrive.presetActions.end();

    }

}