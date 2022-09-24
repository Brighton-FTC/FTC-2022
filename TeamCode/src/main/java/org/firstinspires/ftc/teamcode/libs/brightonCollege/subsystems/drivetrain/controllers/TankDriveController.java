package org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.JoystickMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.LinearMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.TankDrive;

public class TankDriveController extends DriveTrainController {
    public final TankDrive tankDrive;
    public final JoystickMapping speedMapping;
    public final JoystickMapping turnMapping;

    public TankDriveController(TankDrive tankDrive, AutoDrive autoDrive, DriveTrainState state){
        this(tankDrive, autoDrive, state, new LinearMapping(), new LinearMapping());
    }

    public TankDriveController(TankDrive tankDrive, AutoDrive autoDrive, DriveTrainState state, JoystickMapping speedMapping, JoystickMapping turnMapping){
        super(state, autoDrive);
        this.tankDrive = tankDrive;

        this.speedMapping = speedMapping;
        this.turnMapping = turnMapping;
    }

    @Override
    protected void startAuto(){
        tankDrive.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        tankDrive.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        tankDrive.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        tankDrive.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    protected void endAuto(){
        tankDrive.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        tankDrive.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        tankDrive.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tankDrive.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Manually drive the robot
     * @param speed the speed at which the robot is moving (-1 to 1; -1 is back and 1 is front)
     * @param turn how much do we turn? (-1 to 1; -1 is left and 1 is right)
     */
    protected void manualDrive(double speed, double turn){
        // Map the inputs
        speed = speedMapping.map(speed);
        turn = turnMapping.map(turn);

        // Run
        tankDrive.arcadeDrive(speed, turn);
    }
}
