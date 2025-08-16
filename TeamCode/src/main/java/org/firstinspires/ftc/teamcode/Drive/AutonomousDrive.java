package org.firstinspires.ftc.teamcode.Drive;

import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AutonomousDrive {

    public AutonomousDrive(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        drive = new MecanumDrive(hardwareMap, zeroPose);
    }

    HardwareMap hardwareMap;

    private Pose2d zeroPose = new Pose2d(0,0,0);
    private MecanumDrive drive;

    public void autonomousDrive(Gamepad gamepad1) {

        if (gamepad1.a) {

        }
    }

}