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

public class CannonPivotVertical extends SubsystemBase {

  /**
   * Creates a new Turntable.
   */

  // CAN motor with built in encoder, plus limit switch connected to DIO (latter
  // might change to CAN connected limit switch) '
  private WPI_TalonSRX cannonPivotMotor = new WPI_TalonSRX(Constants.CANNON_PIVOT_HORIZONTAL_MOTOR);
  private DigitalInput cannonPivotLimitZero = new DigitalInput(Constants.CANNON_PIVOT_VERTICAL_LIMIT_ZERO);

  public CannonPivotVertical() {
    // when class instantiated (new Turntable...),
    // 1. stop motor
    // 2.set up CAN connected encoder
    stopCannonPivotVertical();
    cannonPivotMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }

  public void pivotCannon(boolean direction) {
    // direction true, clockwise
    // direction false, counterclockwise
    if (direction) {
      cannonPivotMotor.set(Constants.CANNON_PIVOT_VERTICAL_SPEED);
    } else {
      cannonPivotMotor.set(-Constants.CANNON_PIVOT_VERTICAL_SPEED);
    }
  }

  public double getCannonPivotAngle() {
    return getCannonPivotVerticalEncoderValue() * Constants.CANNON_PIVOT_VERTICAL_TICKS_PER_DEGREE;
  }

  public void resetCannonPivotAngle() {
    resetCannonPivotEncoder();
  }

  public void stopCannonPivotVertical() {
    cannonPivotMotor.set(0.0);
  }

  public void setCannonPivotHome() {
    while (!getCannonPivotHome()) {
      // if cannon pivot turned clockwise (> 0.0) turn counterclockwise (false)
      // if cannon pivot turned counterclockwise (< 0.0) turn clockwise (true)
      if (getCannonPivotAngle() > 0.0) {
        pivotCannon(false);
      } else if (getCannonPivotAngle() < 0.0) {
        pivotCannon(true);
      }

      resetCannonPivotAngle();

    }
  }

  public boolean getCannonPivotHome() {
    return cannonPivotLimitZero.get();
  }

  private void resetCannonPivotEncoder() {
    cannonPivotMotor.setSelectedSensorPosition(0);
  }

  private int getCannonPivotVerticalEncoderValue() {
    return cannonPivotMotor.getSelectedSensorPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
