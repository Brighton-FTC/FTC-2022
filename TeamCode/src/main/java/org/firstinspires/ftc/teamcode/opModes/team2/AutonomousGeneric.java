package org.firstinspires.ftc.teamcode.opModes.team2;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.hardware.subsystems.DiscretePositionArm;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.TankDrive;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.controllers.DriveTrainController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.CosMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.RootMapping;
import org.firstinspires.ftc.teamcode.opModes.subroutines.autonomous.DriveForwardCounts;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.modeBases.AutonomousModeBase;

public abstract class AutonomousGeneric extends AutonomousModeBase {
    protected boolean isSpinnerReversed;
    @Override
    public void run() throws InterruptedException {
        setup();
        waitForStart();
        DiscretePositionArm arm = new DiscretePositionArm(
                        hardwareMap.get(DcMotor.class, "slide"),
                        false,
                        Constants.TEAM2_SLIDE_FRONT_COUNTS,
                        Constants.TEAM2_SLIDE_BACK_COUNTS
                );
        arm.moveToFront(Constants.TEAM2_SLIDE_SPEED);
        DriveTrainController driveTrain = new DriveTrainController(new TankDrive(
                hardwareMap.get(DcMotor.class, "left_drivetrain_motor"),
                hardwareMap.get(DcMotor.class, "right_drivetrain_motor"),
                false
        ),
                new RootMapping(2),
                new CosMapping(),
                0,0
        );

        new DriveForwardCounts().run(this, driveTrain, 2000, 0.25);
//        new DriveForwardCounts().run(this, driveTrain, 2000, 0.5);
//        new TurnCounts().run(this, driveTrain, -420, 0.5);
//        new Deliver().run(this, 1000, 1, isSpinnerReversed);
//        new Team2Deposit().run(this, arm);
    }

    public abstract void setup();
}
