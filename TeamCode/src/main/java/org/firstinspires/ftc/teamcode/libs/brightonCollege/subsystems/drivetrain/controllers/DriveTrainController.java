package org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.controllers;

abstract public class DriveTrainController {
    public final AutoDrive autoDrive;
    private DriveTrainState driveTrainState;

    public DriveTrainController(DriveTrainState state, AutoDrive autoDrive){
        this.driveTrainState = state;
        this.autoDrive = autoDrive;
    }

    /**
     * Gives the driver the control, while interrupting automatic movement
     */
    public void force_manual_drive(double input1, double input2){
        setState(DriveTrainState.DRIVER_CONTROLLED);
        manualDrive(input1, input2);
    }

    /**
     * Forcibly starts an auto drive, while removing driver's control of the robot
     */
    public void force_auto_drive(){
        setState(DriveTrainState.AUTO_DRIVE);
    }

    /**
     * Gives the driver the control, without interrupting automatic movement
     */
    public void manual_drive(double input1, double input2){
        if (driveTrainState == DriveTrainState.AUTO_DRIVE) return;
        manualDrive(input1, input2);
    }

    private void setState(DriveTrainState newState){
        driveTrainState = newState;
        if (newState == DriveTrainState.AUTO_DRIVE) startAuto();
        else endAuto();
    }

    abstract protected void startAuto();
    abstract protected void endAuto();
    abstract protected void manualDrive(double input1, double input2);
}
