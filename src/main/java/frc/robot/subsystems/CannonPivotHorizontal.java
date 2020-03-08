/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CannonPivotHorizontal extends SubsystemBase {

  /**
   * Creates a new CannonPivot.
   */

  // CAN motor with built in encoder, plus limit switch connected to DIO (latter
  // might change to CAN connected limit switch) '
  private WPI_TalonSRX cannonPivotMotor = new WPI_TalonSRX(Constants.CANNON_PIVOT_HORIZONTAL_MOTOR);
  private DigitalInput cannonpivotLimitZero = new DigitalInput(Constants.CANNON_PIVOT_HORIZONTAL_LIMIT_ZERO);

  public CannonPivotHorizontal() {
    // when class instantiated (new CannonPivot...),
    // 1. stop motor
    // 2.set up CAN connected encoder
    stopCannonPivot();
    cannonPivotMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }

  public void pivotCannonAutonomous(boolean direction) {
    // direction true, clockwise
    // direction false, counterclockwise
    if (direction) {
      cannonPivotMotor.set(Constants.CANNON_PIVOT_HORIZONTAL_SPEED);
    } else {
      cannonPivotMotor.set(-Constants.CANNON_PIVOT_HORIZONTAL_SPEED);
    }
  }

  public void pivotCannonTeleop(double speed) {
    cannonPivotMotor.set(speed);
  }

  public double getCannonPivotAngle() {
    return getCannonPivotEncoderValue() * Constants.CANNON_PIVOT_VERTICAL_TICKS_PER_DEGREE;
  }

  public void resetCannonPivotAngle() {
    resetCannonPivotEncoder();
  }

  public void stopCannonPivot() {
    cannonPivotMotor.set(0.0);
  }

  public void setCannonPivotHome() {
    while (!getCannonPivotHome()) {
      // if cannonpivot turned clockwise (> 0.0) turn counterclockwise (false)
      // if cannonpivot turned counterclockwise (< 0.0) turn clockwise (true)
      if (getCannonPivotAngle() > 0.0) {
        pivotCannonAutonomous(false);
      } else if (getCannonPivotAngle() < 0.0) {
        pivotCannonAutonomous(true);
      }

      resetCannonPivotAngle();

    }
  }

  public boolean getCannonPivotHome() {
    return cannonpivotLimitZero.get();
  }

  private void resetCannonPivotEncoder() {
    cannonPivotMotor.setSelectedSensorPosition(0);
  }

  private int getCannonPivotEncoderValue() {
    return cannonPivotMotor.getSelectedSensorPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
