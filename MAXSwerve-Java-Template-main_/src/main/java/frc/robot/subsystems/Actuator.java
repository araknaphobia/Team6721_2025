// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Actuator extends SubsystemBase {

  private final SparkMax m_intakeSparkMax;
  private final RelativeEncoder m_intakEncoder;

  /** Creates a new Actuator. */
  public Actuator(int IntakeCANID) {
    m_intakeSparkMax = new SparkMax(IntakeCANID, MotorType.kBrushless);
    m_intakEncoder = m_intakeSparkMax.getEncoder();
  }

  public void ActuatorIn() {

  }

  public void ActuatorIn(RelativeEncoder encoder) {

  }

  public void ActuatorOut() {

  }

  public void ActuatorOut(RelativeEncoder encoder) {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
