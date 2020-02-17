/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CannonBarrel extends SubsystemBase {
  /**
   * Creates a new CannonBarrel.
   */
  private WPI_TalonSRX leftCannonBarrelMotor = new WPI_TalonSRX(Constants.CANNON_BARREL_LEFT_MOTOR);
  private WPI_TalonSRX rightCannonBarrelMotor = new WPI_TalonSRX(Constants.CANNON_BARREL_RIGHT_MOTOR);
  // TODO: create motor declarations for the CannonBarrel feed wheels

  public CannonBarrel() {

    stopCannonBarrel();
    leftCannonBarrelMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    rightCannonBarrelMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }

  public void stopCannonBarrel() {
    leftCannonBarrelMotor.set(0);
    rightCannonBarrelMotor.set(0);
    // TODO: reset motors for the CannonBarrel feed wheels

  }

  public void loadCannonBarrelForStorage(double barrelSpeed, double feederSpeed) {
    setCannonBarrel(-barrelSpeed);
    setCannonBarrelFeeder(-feederSpeed);

  }

  public void loadCannonBarrelForShooting(double feederSpeed) {
    setCannonBarrelFeeder(feederSpeed);
  }

  private void setCannonBarrel(double speed) {
    leftCannonBarrelMotor.set(speed);
    rightCannonBarrelMotor.set(speed);
  }

  private void setCannonBarrelFeeder(double feederSpeed) {
    // TODO: add code to set feeder motors once we know what the motors will be
  }

  public void shootCannonBarrel() {
    setCannonBarrel(Constants.CANNON_BARREL_SPEED);
    while (!getCannonBarrelReadyToShoot()) {
      continue;
    }
    loadCannonBarrelForShooting(Constants.CANNON_BARREL_FEEDER_SPEED);
  }

  public boolean getCannonBarrelReadyToShoot() {
    if ((getLeftEncoderSpeed() >= Constants.CANNON_BARREL_SPEED)
        && (getRightEncoderSpeed() >= Constants.CANNON_BARREL_SPEED)) {
      return true;
    } else {
      return false;
    }
  }

  private double getLeftEncoderSpeed() {
    // TODO: figure out how encoders will tell us what speed
    return 0;

  }

  private double getRightEncoderSpeed() {
    // TODO: figure out how encoders will tell us what speed
    return 0;

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
