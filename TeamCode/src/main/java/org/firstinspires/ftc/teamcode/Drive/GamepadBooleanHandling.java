package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadBooleanHandling {

    public Gamepad gamepad1;
    public Gamepad gamepad2;

    public GamepadBooleanHandling(Gamepad gamepad1, Gamepad gamepad2) {

        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    public void handle() {

        prev_gamepad1a = cur_gamepad1a;
        cur_gamepad1a = gamepad1.a;
    }

    public boolean prev_gamepad1a = false;
    public boolean cur_gamepad1a = false;

}
