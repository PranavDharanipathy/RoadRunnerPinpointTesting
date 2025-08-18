package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.MecanumDrive;

public class AutonomousDrive {

    public AutonomousDrive(MecanumDrive drive) {
        presetActions = new PresetActionThreads(drive);
    }

    public PresetActionThreads presetActions;

    public enum DRIVE_TYPES { TELEOPERATED, TELEOPERATED_AND_CANCEL_ALL_TASKS, AUTONOMOUS }
    public static volatile DRIVE_TYPES ALLOWED_DRIVE_TYPE;

    public static boolean spinToggle = false;

    public void autonomousDrive(GamepadBooleanHandling gbh) {

        if (ALLOWED_DRIVE_TYPE == DRIVE_TYPES.TELEOPERATED_AND_CANCEL_ALL_TASKS) {
            presetActions.cancelAll();
            ALLOWED_DRIVE_TYPE = DRIVE_TYPES.TELEOPERATED;
        }

        if (gbh.cur_gamepad1a & !gbh.prev_gamepad1a) {

            if (!spinToggle) {
                ALLOWED_DRIVE_TYPE = DRIVE_TYPES.AUTONOMOUS;
                presetActions.spin();
            }
            else {
                ALLOWED_DRIVE_TYPE = DRIVE_TYPES.TELEOPERATED;
                presetActions.spinCancel();
            }

            spinToggle = !spinToggle;
        }
    }

}