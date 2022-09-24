package org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;

public class AutoGyroTankDrive implements AutoDrive {
//    public void startDrivingForward(double distance, double speed) {
//        // BUG: see https://ftcforum.firstinspires.org/forum/ftc-technology/3315-questionable-isbusy-behavior
////        if (isBusy()) throw new RuntimeException("Interrupting drivetrain operation");
//        driveTrainState = DriveTrainState.AUTO_DRIVE;
//
//        tankDrive.rightMotor.setPower(speed);
//        tankDrive.leftMotor.setPower(speed);
//        int countDiff = (int)(distance * forwardsGradient + forwardsIntercept);
//        incrementDesiredMotorCounts(countDiff, countDiff);
//    }
//
//    /**
//     * Uses counts of each motor
//     * @param leftCounts number of counts to add to left drive motor
//     * @param rightCounts number of counts to add to right drive motor
//     * @param speed maximum speed of motors
//     */
//    public void startDrivingCounts(int leftCounts, int rightCounts, double speed) {
//        if (isBusy()) throw new RuntimeException("Interrupting drivetrain operation");
//        driveTrainState = DriveTrainState.AUTO_DRIVE;
//
//        tankDrive.rightMotor.setPower(speed);
//        tankDrive.leftMotor.setPower(speed);
//        incrementDesiredMotorCounts(leftCounts, rightCounts);
//    }
////
////    public void startTurn(double turnRadians, double speed) {
////        if (isBusy()) throw new RuntimeException("Interrupting drivetrain operation");
////        driveTrainState = DriveTrainState.AUTO_TURN;
////
////        driveTrain.rightMotor.setPower(speed);
////        driveTrain.leftMotor.setPower(speed);
////        incrementDesiredMotorCounts((int) (turnRadians * countsPerRadian), -(int)(turnRadians * countsPerRadian));
////    }
//
//    private void incrementDesiredMotorCounts(int leftCountsChange, int rightCountsChange) {
//        int desiredLeftCounts = tankDrive.leftMotor.getCurrentPosition() + leftCountsChange;
//        int desiredRightCounts = tankDrive.rightMotor.getCurrentPosition() + rightCountsChange;
//
//        tankDrive.leftMotor.setTargetPosition(desiredLeftCounts);
//        tankDrive.rightMotor.setTargetPosition(desiredRightCounts);
//        tankDrive.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        tankDrive.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    }
}
