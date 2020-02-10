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

public class Turntable extends SubsystemBase {
  /**
   * Creates a new Turntable.
   */

  // CAN motor with built in encoder, plus limit switch connected to DIO (latter
  // might change to CAN connected limit switch) '
  private WPI_TalonSRX turntableMotor = new WPI_TalonSRX(Constants.TURNTABLE_MOTOR);
  private DigitalInput turntableLimitZero = new DigitalInput(Constants.TURNTABLE_LIMIT_ZERO);

  public Turntable() {
    // when class instantiated (new Turntable...),
    // 1. stop motor
    // 2.set up CAN connected encoder
    stopTurntable();
    turntableMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }

  public void pivotTurntable(boolean direction) {
    // direction true, clockwise
    // direction false, counterclockwise
    if (direction) {
      turntableMotor.set(Constants.TURNTABLE_MOTOR_SPEED);
    } else {
      turntableMotor.set(-Constants.TURNTABLE_MOTOR_SPEED);
    }
  }

  public double getTurntablePivotAngle() {
    return getTurntableEncoderValue() * Constants.TURNTABLE_TICKS_PER_DEGREE;
  }

  public void resetTurntablePivotAngle() {
    resetTurntableEncoder();
  }

  public void stopTurntable() {
    turntableMotor.set(0.0);
  }

  public void setHome() {
    while (!getHome()) {
      // if turntable turned clockwise (> 0.0) turn counterclockwise (false)
      // if turntable turned counterclockwise (< 0.0) turn clockwise (true)
      if (getTurntablePivotAngle() > 0.0) {
        pivotTurntable(false);
      } else if (getTurntablePivotAngle() < 0.0) {
        pivotTurntable(true);
      }

      resetTurntablePivotAngle();

    }
  }

  public boolean getHome() {
    return turntableLimitZero.get();
  }

  private void resetTurntableEncoder() {
    turntableMotor.setSelectedSensorPosition(0);
  }

  private int getTurntableEncoderValue() {
    return turntableMotor.getSelectedSensorPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
