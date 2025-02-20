// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSystem extends SubsystemBase {

  private final SparkMax m_climbMotor;
  private final RelativeEncoder m_climbEncoder;

  /** Creates a new ClimberSystem. */
  public ClimberSystem(int climbCANID, int photoeyeDIO) {
    m_climbMotor = new SparkMax(climbCANID, MotorType.kBrushless);
    m_climbEncoder = m_climbMotor.getEncoder();
  }

  public void ClimbUp() {

  }

  public void ClimbDown() {

  }

  public boolean GetPhotoeye() {

    return true;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
