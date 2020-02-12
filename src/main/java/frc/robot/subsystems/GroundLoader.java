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

  private WPI_TalonSRX m_groundloadermotor = new WPI_TalonSRX(Constants.GROUND_LOADER_MOTOR);

  public GroundLoader() {
    m_groundloadermotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    stopGroundLoader();
  }

  /*
  public void initDefaultCommand() {
    setDefaultCommand(new FuelIntakeWithController());
  }
*/
  public int getGroundLoaderEncoder() {
    return m_groundloadermotor.getSelectedSensorPosition();
  }

  public boolean getGroundLoaderLimitSwitchLow() {
    return m_groundloadermotor.getSensorCollection().isRevLimitSwitchClosed();
  }

  public boolean getGroundLoaderLimitSwitchHigh() {
    return m_groundloadermotor.getSensorCollection().isFwdLimitSwitchClosed();
  }

  public void setGroundLoaderDirection(double loadSpeed, int loadTarget) {
    if (getGroundLoaderEncoder() < loadTarget) {
      setGroundLoaderUp(loadSpeed);
    } 
    else {
      setGroundLoaderDown(loadSpeed);
    }
  }

  public void setGroundLoaderUp(double loadSpeed) {
    m_groundloadermotor.set(loadSpeed);
  }

  public void setGroundLoaderDown(double loadSpeed) {
    m_groundloadermotor.set(-loadSpeed);
  }

  public void resetGroundLoaderEncoder() {
    m_groundloadermotor.setSelectedSensorPosition(0);
  }

  public void stopGroundLoader() {
    m_groundloadermotor.set(0.0);
  }
  @Override
  public void periodic() {
    
  }
    //
  }

