// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import java.util.function.BooleanSupplier;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Constants.ActuatorConstants;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {

  

  
  public enum Setpoint {
    kStow,
    kL1,
    kL2,
    kL3,
    kL4;
  }
  private SparkMax elevatorMotor1 = new SparkMax(ElevatorConstants.kElevator1ID, MotorType.kBrushless);
  private SparkMax elevatorMotor2 = new SparkMax(ElevatorConstants.kElevator2ID, MotorType.kBrushless);
  
  


  private RelativeEncoder elevatorEncoder = elevatorMotor1.getEncoder();
  private SparkClosedLoopController elevator1ClosedLoopController = elevatorMotor1.getClosedLoopController();
  private SparkClosedLoopController elevator2ClosedLoopController = elevatorMotor1.getClosedLoopController();

  private boolean wasResetByButton = false;
  private double elevatorCurrentTarget = ElevatorConstants.kStow;
 
  public ElevatorSubsystem(){

    elevatorMotor1.configure(
      Configs.ElevatorSubsystem.elevatorConfig,
      ResetMode.kResetSafeParameters,
      PersistMode.kPersistParameters);

    
      

    elevatorMotor2.configure(
      Configs.ElevatorSubsystem.elevatorFollower, 
      ResetMode.kResetSafeParameters,
      PersistMode.kPersistParameters);

    elevatorEncoder.setPosition(0);
  }

    private void moveToSetpoint(){
      elevator1ClosedLoopController.setReference(
        elevatorCurrentTarget,
        ControlType.kMAXMotionPositionControl
        
      );

    }

    public BooleanSupplier autoElevPos = () ->{
    return elevatorPosition(57);
  };

  

    public boolean elevatorPosition(double pos)
    {
      double currPos = elevatorEncoder.getPosition();
      double high = pos + 10;
      double low = pos -10;
      
      if((currPos > low) && (currPos < high))
      {
        return true;
      }
      else 
      {
        return true;
      }
    }

    private void zeroElevatorOnUserButton(){
      if (!wasResetByButton && RobotController.getUserButton()){
        wasResetByButton = true;
        elevatorEncoder.setPosition(0);
      } else if (!RobotController.getUserButton()){
        wasResetByButton = false;
      }
    }

    public Command setSetpointCommand(Setpoint setpoint){
      return this.runOnce(
        () -> {
          switch (setpoint){
            case kStow:
            elevatorCurrentTarget = ElevatorConstants.kStow;
            break;
            case kL1:
            elevatorCurrentTarget = ElevatorConstants.kL1;
            break;
            case kL2:
            elevatorCurrentTarget = ElevatorConstants.kL2;
            break;
            case kL3:
            elevatorCurrentTarget = ElevatorConstants.kL3;
            break;
            case kL4:
            elevatorCurrentTarget = ElevatorConstants.kL4;
            break;
          }
        }
      );
    }
    
  

  
  

  
  
  @Override
  public void periodic() {

    moveToSetpoint();
    zeroElevatorOnUserButton();

    SmartDashboard.putNumber("Elevator Target Position" , elevatorCurrentTarget);
    SmartDashboard.putNumber("Elevator Actual Position" , elevatorEncoder.getPosition());
  
    // This method will be called once per scheduler run
  }
}
