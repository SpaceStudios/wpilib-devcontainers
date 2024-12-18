// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drivetrain;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

/** Add your docs here. */
public class drivetrainIO_SIM implements drivetrainIO {
    DCMotorSim leftDrive;
    DCMotorSim rightDrive;

    public drivetrainIO_SIM(int LeftID, int LeftFollowerID, int RightID, int RightFollowerID) {
        leftDrive = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getNeo550(1), 0.0, 150.0/7.0),DCMotor.getNeo550(2), 0.0);
        rightDrive = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getNeo550(1), 0.0, 150.0/7.0), DCMotor.getNeo550(2), 0.0);
    }

    @Override
    public void setDriveVolts(double lVolts, double rVolts) {
        leftDrive.setInputVoltage(lVolts);
        rightDrive.setInputVoltage(rVolts);
    }

    @Override
    public void getData(DriveData data) {
        leftDrive.update(0.020);
        rightDrive.update(0.020);

        data.DriveAMPsLeft = leftDrive.getCurrentDrawAmps();
        data.DriveAMPsRight = rightDrive.getCurrentDrawAmps();
        data.DriveDistanceLeft = leftDrive.getAngularPositionRad()*Units.inchesToMeters(2);
        data.DriveDistanceRight = rightDrive.getAngularPositionRad()*Units.inchesToMeters(2);
        data.DriveSpeedLeft = leftDrive.getAngularVelocityRadPerSec()*Units.inchesToMeters(2);
        data.DriveSpeedRight = rightDrive.getAngularVelocityRadPerSec()*Units.inchesToMeters(2);
        data.DriveVoltsLeft = leftDrive.getInputVoltage();
        data.DriveVoltsRight = rightDrive.getInputVoltage();
    }

}
