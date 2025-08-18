package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class TeleOperatedDrive {

    private double driveLeftX_debugger;
    private double driveLeftY_debugger;
    private double driveRightX_debugger;
    private final double joyStickMargin = 0.004;

    private Gamepad gamepad1;

    public TeleOperatedDrive(Gamepad gamepad1) {
        this.gamepad1 = gamepad1;
    }

    public void teleOperatedDrive(DcMotor left_front, DcMotor right_front, DcMotor left_back, DcMotor right_back) {

        if (Math.abs(gamepad1.left_stick_x) >= joyStickMargin) {
            driveLeftX_debugger = -gamepad1.left_stick_x;
        } else driveLeftX_debugger = 0;

        if (Math.abs(gamepad1.left_stick_y) >= joyStickMargin) {
            driveLeftY_debugger = -gamepad1.left_stick_y;
        } else driveLeftY_debugger = 0;

        if (Math.abs(gamepad1.right_stick_x) >= joyStickMargin) {
            driveRightX_debugger = -gamepad1.right_stick_x;
        } else driveRightX_debugger = 0;

        if (AutonomousDrive.ALLOWED_DRIVE_TYPE == AutonomousDrive.DRIVE_TYPES.AUTONOMOUS && (driveLeftX_debugger != 0 || driveLeftY_debugger != 0 || driveRightX_debugger != 0)) {
            AutonomousDrive.ALLOWED_DRIVE_TYPE = AutonomousDrive.DRIVE_TYPES.TELEOPERATED_AND_CANCEL_ALL_TASKS;
        }

        left_front.setPower(driveLeftY_debugger - driveRightX_debugger - driveLeftX_debugger);
        left_back.setPower(driveLeftY_debugger - driveRightX_debugger + driveLeftX_debugger);
        right_front.setPower(driveLeftY_debugger + driveRightX_debugger + driveLeftX_debugger);
        right_back.setPower(driveLeftY_debugger + driveRightX_debugger - driveLeftX_debugger);
    }

}