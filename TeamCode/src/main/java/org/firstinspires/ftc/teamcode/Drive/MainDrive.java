package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class MainDrive extends OpMode {

    private String ALLOWED_DRIVE_TYPE;

    private DcMotorEx left_front, right_front, left_back, right_back;

    private TeleOperatedDrive botDrive = new TeleOperatedDrive();
    private AutonomousDrive autoDrive = new AutonomousDrive(hardwareMap);

    @Override
    public void init() {
        left_front = hardwareMap.get(DcMotorEx.class, "left_front");
        right_front = hardwareMap.get(DcMotorEx.class, "right_front");
        left_back = hardwareMap.get(DcMotorEx.class, "left_back");
        right_back = hardwareMap.get(DcMotorEx.class, "right_back");
    }

    @Override
    public void loop() {



        if (ALLOWED_DRIVE_TYPE == "TELEOPERATED") {
            botDrive.teleOperatedDrive(left_front, right_front, left_back, right_back, gamepad1);
        }
        else autoDrive.autonomousDrive(gamepad1);
    }
}