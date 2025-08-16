package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@Autonomous (name = "RepetitiveSplineTuner")
public final class RepetitiveSplineTuner extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(0, 0, 0);

        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        if (isStopRequested()) return;
        waitForStart();

        while (opModeIsActive()) {
            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .splineTo(new Vector2d(24, 24), Math.toRadians(90))
                            .splineTo(new Vector2d(0, 48), Math.toRadians(180))
                            .splineTo(new Vector2d(-24, 24), Math.toRadians(270))
                            .splineTo(new Vector2d(0, 0), Math.toRadians(360))
                            .build());
            beginPose = new Pose2d(drive.updatePoseEstimate().component1().x, drive.updatePoseEstimate().component1().y, drive.updatePoseEstimate().component2());
            sleep(250);
        }

    }
}