// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drivetrain;

/** Add your docs here. */
public interface drivetrainIO {
    public static class DriveData {
        public double DriveVoltsLeft = 0.0;
        public double DriveVoltsRight = 0.0;
        public double DriveAMPsLeft = 0.0;
        public double DriveAMPsRight = 0.0;
        public double DriveSpeedLeft = 0.0; //Drive Speed Left in M/S
        public double DriveSpeedRight = 0.0; //Drive Speed Right in M/S
        public double DriveDistanceLeft = 0.0; // Drive Left Distance in Meters
        public double DriveDistanceRight = 0.0; // Drive Right Distance in Meters
    }

    public abstract void setDriveVolts(double lVolts, double rVolts);
    public abstract void getData(DriveData data);
}
