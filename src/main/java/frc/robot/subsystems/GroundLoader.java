/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


//import frc.robot.RobotContainer;
//import frc.robot.commands.Drive;

import frc.robot.Constants;

public class GroundLoader extends SubsystemBase {

  /*
   * get means use encoder and set means use LIFT_MOTOR
   */

  private WPI_TalonSRX m_groundLoaderMotor = new WPI_TalonSRX(Constants.GROUND_LOADER_MOTOR);

  public GroundLoader() {
    stopGroundLoader();
  }

  public void setGroundLoaderLoad() {
    setGroundLoaderMotor(Constants.GROUND_LOADER_SPEED);
  }

  public void setGroundLoaderUnLoad() {
    setGroundLoaderMotor(-Constants.GROUND_LOADER_SPEED);
  }

  public void stopGroundLoader() {
    setGroundLoaderMotor(0.0);
  }

  private void setGroundLoaderMotor(double loadSpeed)
  {
    m_groundLoaderMotor.set(loadSpeed);
  }
  
  @Override
  public void periodic() {
    
  }
    //
  }

