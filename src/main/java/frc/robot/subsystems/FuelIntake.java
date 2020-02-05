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

public class FuelIntake extends SubsystemBase {

  /*
   * get means use encoder and set means use LIFT_MOTOR
   */

  private WPI_TalonSRX fuelIntakeMotor = new WPI_TalonSRX(Constants.FUEL_INTAKE_MOTOR);

  public FuelIntake() {
    fuelIntakeMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    stopFuelIntake();
  }

  /*
  public void initDefaultCommand() {
    setDefaultCommand(new FuelIntakeWithController());
  }
*/
  public int getFuelIntakeEncoder() {
    return fuelIntakeMotor.getSelectedSensorPosition();
  }

  public boolean getFuelIntakeLimitSwitchLow() {
    return fuelIntakeMotor.getSensorCollection().isRevLimitSwitchClosed();
  }

  public boolean getFuelIntakeLimitSwitchHigh() {
    return fuelIntakeMotor.getSensorCollection().isFwdLimitSwitchClosed();
  }

  public void setFuelIntakeDirection(double intakeSpeed, int fuelIntakeTarget) {
    if (getFuelIntakeEncoder() < fuelIntakeTarget) {
      setFuelIntakeUp(intakeSpeed);
    } 
    else {
      setFuelIntakeDown(intakeSpeed);
    }
  }

  public void setFuelIntakeUp(double intakeSpeed) {
    fuelIntakeMotor.set(intakeSpeed);
  }

  public void setFuelIntakeDown(double intakeSpeed) {
    fuelIntakeMotor.set(-intakeSpeed);
  }

  public void resetFuelIntakeEncoder() {
    fuelIntakeMotor.setSelectedSensorPosition(0);
  }

  public void stopFuelIntake() {
    fuelIntakeMotor.set(0.0);
  }
  @Override
  public void periodic() {
    //
  }
}
