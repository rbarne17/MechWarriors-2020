/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  public Spark m_leftMotor = new Spark(Constants.DRIVETRAIN_LEFT_SPARK);
  public Spark m_rightMotor = new Spark(Constants.DRIVETRAIN_RIGHT_SPARK);
  private final AnalogInput m_ultrasonic = new AnalogInput(Constants.DRIVETRAIN_ULTRASONIC);

  public DriveTrain() {
    m_leftMotor.set(0);
    m_rightMotor.set(0);
  }

  // COME BACK TO THIS for PERIODIC (using)
  //
  public void driveTrain() {
  }

  public void driveByTank(double LeftpercentThrottle, double RightpercentThrottle) {
    m_leftMotor.set(LeftpercentThrottle);
    m_rightMotor.set(RightpercentThrottle);

  }

  public void driveByArcade(double percentThrottle, double percentRotationOutput) {

    percentThrottle = valueAfterDeadzone(percentThrottle);
    percentRotationOutput = valueAfterDeadzone(percentRotationOutput);

    percentThrottle = scalingSpeed(percentThrottle);
    percentRotationOutput = scalingSpeed(percentRotationOutput);

    m_leftMotor.set((-percentThrottle - percentRotationOutput)*0.75);
    m_rightMotor.set((percentThrottle - percentRotationOutput)*0.75);

  }

  public double scalingSpeed(double joystickValue) {
    double scalingCutoff = .8;
    double joystickValueToTheThird = Math.pow(joystickValue, 3);
    return scalingCutoff * joystickValueToTheThird + ((1 - scalingCutoff) * joystickValue);

  }

  public void reset() {
    driveByTank(0.0, 0.0);
  }

  public double getDistance() {
    return m_ultrasonic.getValue() * Constants.DRIVETRAIN_ULTRASONIC_VALUE_TO_INCHES;
  }

  private double valueAfterDeadzone(double currentValue) {
    double deadzone = 0.2;
    if (Math.abs(currentValue) < deadzone) {
      return 0;
    } else {
      return currentValue;

    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
