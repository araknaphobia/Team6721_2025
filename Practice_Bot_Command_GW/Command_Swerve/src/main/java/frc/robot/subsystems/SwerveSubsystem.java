// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

import static edu.wpi.first.units.Units.Rotation;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.fasterxml.jackson.databind.util.ArrayBuilders.DoubleBuilder;
import com.revrobotics.*;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveSubsystem extends SubsystemBase {
  // Create the Swerve Modules 
  private final SwerveModule frontLeft = new SwerveModule(
    DriveConstants.kFrontLeftDriveMotorPort, 
    DriveConstants.kFrontLeftTurningMotorPort, 
    DriveConstants.kFrontLeftDriveEncoderReversed, 
    DriveConstants.kFrontLeftTurningEncoderReversed, 
    DriveConstants.kFrontLeftDriveAbsoluteEncoderPort, 
    DriveConstants.kFrontLeftDriveAbsoluteEncoderOffsetRad, 
    DriveConstants.kFrontLeftDriveAbsoluteEncoderReversed);
  
  private final SwerveModule frontRight = new SwerveModule(
    DriveConstants.kFrontRightDriveMotorPort,
    DriveConstants.kFrontRightTurningMotorPort,
    DriveConstants.kFrontRightDriveEncoderReversed,
    DriveConstants.kFrontRightTurningEncoderReversed,
    DriveConstants.kFrontRightDriveAbsoluteEncoderPort,
    DriveConstants.kFrontRightDriveAbsoluteEncoderOffsetRad,
    DriveConstants.kFrontRightDriveAbsoluteEncoderReversed);

  private final SwerveModule backLeft = new SwerveModule(
    DriveConstants.kBackLeftDriveMotorPort,
    DriveConstants.kBackLeftTurningMotorPort,
    DriveConstants.kBackLeftDriveEncoderReversed,
    DriveConstants.kBackLeftTurningEncoderReversed,
    DriveConstants.kBackLeftDriveAbsoluteEncoderPort,
    DriveConstants.kBackLeftDriveAbsoluteEncoderOffsetRad,
    DriveConstants.kBackLeftDriveAbsoluteEncoderReversed);

  private final SwerveModule backRight = new SwerveModule(
    DriveConstants.kBackRightDriveMotorPort,
    DriveConstants.kBackRightTurningMotorPort,
    DriveConstants.kBackRightDriveEncoderReversed,
    DriveConstants.kBackRightTurningEncoderReversed,
    DriveConstants.kBackRightDriveAbsoluteEncoderPort,
    DriveConstants.kBackRightDriveAbsoluteEncoderOffsetRad,
    DriveConstants.kBackRightDriveAbsoluteEncoderReversed);
  
    private final Pigeon2 gyro = new Pigeon2(DriveConstants.kPigeonPort);
    /*/
    private final SwerveDriveOdometry odometer = new SwerveDriveOdometry(
      DriveConstants.kDriveKinematics, 
      new Rotation2d(0), new SwerveModulePosition[] {
      frontLeft.getDrivePosition(),
      frontRight.getPosition(),
      backLeft.getPosition(),
      backRight.getPosition()},
      new Pose2d());
      */
    //private final SwerveModuleState SwerveState;

  /** Creates a new SwerveSubsystem. */
  public SwerveSubsystem() {
    new Thread(() -> {
      try {
        Thread.sleep(1000);
        zeroHeading();
      } catch (Exception e) {}
    }).start();
  }

  public void zeroHeading() {
    gyro.reset();
  }

  public double getHeading() {
    return Math.IEEEremainder(gyro.getAngle(), 360);
  }

  public Rotation2d getRotation2d() {
    return Rotation2d.fromDegrees(getHeading());
  }

  // public Pose2d getPose() {
  //  return odometer.getPoseMeters();
  // }

  // public void resetOdometry(Pose2d pose) {
  //   odometer.resetPosition(getRotation2d(), pose);;
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //updateStates(frontLeft.getState(), frontRight.getState(), backLeft.getState(), backRight.getState());
    //odometer.update(getRotation2d(), this.SwerveState);
    SmartDashboard.putNumber("Robot Heading", getHeading());
    //SmartDashboard.putString("Robot Location", getPose().getTranslation().toString());
  }

  public void stopModules() {
    frontLeft.stop();
    frontRight.stop();
    backLeft.stop();
    backRight.stop();
  }

  public void setModuleStates(SwerveModuleState[] desiredStates) {
    SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, DriveConstants.kPhysicalMaxSpeedMetersPerSecond);
    frontLeft.setDesiredState(desiredStates[0]);
    frontRight.setDesiredState(desiredStates[1]);
    backLeft.setDesiredState(desiredStates[2]);
    backRight.setDesiredState(desiredStates[3]);
  }

  public void calibModules() {
    frontLeft.calibrate();
    frontRight.calibrate();
    backLeft.calibrate();
    backRight.calibrate();
  }
}
