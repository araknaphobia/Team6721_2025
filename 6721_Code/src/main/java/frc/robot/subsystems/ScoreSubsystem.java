// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ActuatorConstants;
import frc.robot.Constants.ElevatorConstants;

public class ScoreSubsystem extends SubsystemBase {

  private final Actuator m_Actuator = new Actuator(ActuatorConstants.kIntakeID, ActuatorConstants.kBreakBeam1ID);
  private final ElevatorSubsystem m_Elevator = new ElevatorSubsystem(ElevatorConstants.kLift1ID, ElevatorConstants.kLift2ID);

  public void GoLevel4() {
    m_Elevator.ElevatorUp(ElevatorConstants.kLevel4);
  }

  public void GoLevel3() {
    m_Elevator.ElevatorUp(ElevatorConstants.kLevel3);
  }

  public void GoLevel2() {
    m_Elevator.ElevatorUp(ElevatorConstants.kLevel2);
  }

  public void GoLevel1() {
    m_Elevator.ElevatorUp(ElevatorConstants.kLevel1);
  }

  public void Stow() {
    m_Actuator.StopActuator();
    m_Elevator.ElevatorUp(ElevatorConstants.kStow);
  }

  /** Creates a new ScoreSubsystem. */
  public ScoreSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
