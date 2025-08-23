package org.firstinspires.ftc.teamcode.Drive;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.MecanumDrive;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PresetActionThreads {

    public PresetActionThreads(MecanumDrive drive) {
        this.drive = drive;
    }

    private Pose2d zeroPose = new Pose2d(0,0,0);
    private MecanumDrive drive;

    public void end() {
        executor.shutdownNow();
    }

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Future<?> spinFuture;

    public void cancelAll() {
        spinCancel();
    }

    public void spin() {

        spinFuture = executor.submit(() -> {

            try {
                Actions.runBlocking(
                        drive.actionBuilder(zeroPose)
                                .splineTo(new Vector2d(24, 24), Math.toRadians(90), new TranslationalVelConstraint(35), new ProfileAccelConstraint(-20, 20))
                                .splineTo(new Vector2d(0, 48), Math.toRadians(180), new TranslationalVelConstraint(35), new ProfileAccelConstraint(-20, 20))
                                .splineTo(new Vector2d(-24, 24), Math.toRadians(270), new TranslationalVelConstraint(35), new ProfileAccelConstraint(-20, 20))
                                .splineTo(new Vector2d(0, 0), Math.toRadians(360), new TranslationalVelConstraint(35), new ProfileAccelConstraint(-20, 20))
                                .build()
                );
                AutonomousDrive.spinToggle = false;
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    public void spinCancel() {

        AutonomousDrive.spinToggle = false;

        try {
            spinFuture.cancel(true);
        } catch (Exception ignored) {}
    }

}
