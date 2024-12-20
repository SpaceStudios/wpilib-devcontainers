// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drivetrain;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelPositions;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.subsystems.drivetrain.drivetrainIO.DriveData;

public class drivetrainSubsys extends SubsystemBase {
  /** Creates a new Drivetrain. */
  boolean voltageBased;
  drivetrainIO mainIO;
  ChassisSpeeds setSpeeds;
  PIDController leftDrivePID;
  PIDController rightDrivePID;
  DifferentialDriveKinematics kinematics;
  DifferentialDriveOdometry odometry;
  DriveData data;
  Field2d field2d;

  public drivetrainSubsys(boolean useVolts, drivetrainIO io) {
    voltageBased = useVolts;
    mainIO = io;
    setSpeeds = new ChassisSpeeds();
    kinematics = new DifferentialDriveKinematics(0.5);
    data = new DriveData();
    field2d = new Field2d();
    SmartDashboard.putData("Field2d", field2d);
  }

  public void joystickDrive(double joystick1y,double joystick2x) {
    setSpeeds = new ChassisSpeeds(joystick1y*DriveTrainConstants.MaxSpeed,0,joystick2x*DriveTrainConstants.MaxRotationSpeed);
  }

  public void DriveChassisSpeeds(ChassisSpeeds driveSpeeds) {
    DifferentialDriveWheelSpeeds setWheelSpeeds = kinematics.toWheelSpeeds(driveSpeeds);
    if (voltageBased) {
      mainIO.setDriveVolts((setWheelSpeeds.leftMetersPerSecond/DriveTrainConstants.MaxSpeed)*12, (setWheelSpeeds.rightMetersPerSecond/DriveTrainConstants.MaxSpeed)*12);
    } else {
      double driveVoltsLeft = leftDrivePID.calculate(data.DriveSpeedLeft, setWheelSpeeds.leftMetersPerSecond);
      double DriveVoltsRight = rightDrivePID.calculate(data.DriveSpeedRight, setWheelSpeeds.rightMetersPerSecond);
      mainIO.setDriveVolts(driveVoltsLeft, DriveVoltsRight);
    }
  }

  @Override
  public void periodic() {
    mainIO.getData(data);
    odometry.update(
      odometry.getPoseMeters().getRotation().plus(Rotation2d.fromRadians(0)), 
      new DifferentialDriveWheelPositions(data.DriveDistanceLeft, data.DriveDistanceRight));

    field2d.setRobotPose(odometry.getPoseMeters());
  }
}
