// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {

  public final SparkMax m_elev1Motor;
  public final SparkMax m_elev2Motor;

  public final RelativeEncoder m_elev1Encoder;
  public final RelativeEncoder m_elev2Encoder;


  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem(int elev1CANID, int elev2CANID) {
    m_elev1Motor = new SparkMax(elev1CANID, MotorType.kBrushless);
    m_elev2Motor = new SparkMax(elev2CANID, MotorType.kBrushless);

    m_elev1Encoder = m_elev1Motor.getEncoder();
    m_elev2Encoder = m_elev2Motor.getEncoder();
  }

  public void ElevatorUp() {

  }

  public void ElevatorUp(double position) {

  }

  public void ElevatorDown() {

  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
