package org.firstinspires.ftc.teamcode.opModes.subroutines.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Timer;
import org.firstinspires.ftc.teamcode.hardware.subsystems.CarouselSpinner;

public class Deliver {
    public void run(LinearOpMode opMode, long timeMs, double speed, boolean isInverted) throws InterruptedException {
        // Get hardwareMap
        HardwareMap hardwareMap = opMode.hardwareMap;

        Timer timer = new Timer();

        CarouselSpinner spinner = new CarouselSpinner(hardwareMap.get(DcMotor.class, "carousel_spinner"), isInverted);

        timer.reset();

        spinner.spin(speed);

        while (timer.getTimePassed() < timeMs) {
            if (opMode.isStopRequested()) return;
            opMode.sleep(50);
        }

        spinner.spin(0);
    }
}
