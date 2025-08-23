package org.firstinspires.ftc.teamcode.Drive.tuning;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ProportionalDriveTuner extends LinearOpMode {

    public static double MARGIN_BETWEEN_DISTANCE = 10;

    private DcMotor left_front, right_front, left_back, right_back;

    private ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {

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

        left_front.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_front.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        left_front.setTargetPosition(75_000);
        left_back.setTargetPosition(75_000);
        right_back.setTargetPosition(75_000);
        left_front.setTargetPosition(75_000);

        if (isStopRequested()) return;
        waitForStart();

        timer.reset();

        while(opModeIsActive() && timer.milliseconds() <= 22500) {
            left_front.setPower(1);
            left_back.setPower(1);
            right_front.setPower(-1);
            right_back.setPower(-1);
        }

        left_front.setPower(0);
        left_back.setPower(0);
        right_front.setPower(0);
        right_back.setPower(0);

        double left_distance = (Math.abs((Math.abs(left_front.getTargetPosition()) - Math.abs(left_front.getCurrentPosition()))) + Math.abs((Math.abs(left_back.getTargetPosition()) - Math.abs(left_back.getCurrentPosition())))) / 2;
        double right_distance = (Math.abs((Math.abs(right_front.getTargetPosition()) - Math.abs(right_front.getCurrentPosition()))) + Math.abs((Math.abs(right_back.getTargetPosition()) - Math.abs(right_back.getCurrentPosition())))) / 2;
        double kp = 1;

        if (Math.abs(left_distance - right_distance) <= MARGIN_BETWEEN_DISTANCE) {
            telemetry.addData("Difference of the distance travelled by left and right wheels is less than or equal to", MARGIN_BETWEEN_DISTANCE);
        }
        else if (left_distance > right_distance) {
            kp = right_distance / left_distance;
            telemetry.addLine("kp (list) item 0 => kp, item 1 => 1.0");
        }
        else {
            telemetry.addLine("kp (list) item 0 => 1.0, item 1 => kp");
            kp = left_distance / right_distance;
        }

        telemetry.addData("kp", kp);

        telemetry.update();

    }
}