// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSystem extends SubsystemBase {

  private final SparkMax m_climbMotor;
  private final RelativeEncoder m_climbEncoder;

  private final DigitalInput m_photoEye;

  /** Creates a new ClimberSystem. */
  public ClimberSystem(int climbCANID, int photoeyeDIO) {
    m_climbMotor = new SparkMax(climbCANID, MotorType.kBrushless);
    m_climbEncoder = m_climbMotor.getEncoder();

    m_photoEye = new DigitalInput(photoeyeDIO);
  }

  public void ClimbUp() {
    m_climbMotor.set(ClimberConstants.kClimbSpeed);
  }

  public void ClimbUp(boolean photoEye) {
    if(photoEye) {
      // PID Loop cool stuff goes here
    }
  } 

  public void ClimbDown() {
    m_climbMotor.set(-ClimberConstants.kClimbSpeed);
  }

  public boolean GetPhotoeye() {
    return m_photoEye.get();
  }

  public void StopClimb() {
    m_climbMotor.stopMotor();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
