// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/** Add your docs here. */
public class Constants {
    public static class DriveTrainConstants {
        public static final double MaxSpeed = 20; // Max Speed of the Robot in M/S
        public static final double MaxRotationSpeed = Units.degreesToRadians(60); //Max Speed in Radian/S, Converted from degrees in this example

        public static final double kDriveP = 0.9; // Drive PID Proportional
        public static final double kDriveI = 0.0; // Drive PID Integral
        public static final double kDriveD = 0.0; // Drive PID Derivative
    }
}
