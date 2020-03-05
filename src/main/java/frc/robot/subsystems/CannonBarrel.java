/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CannonBarrel extends SubsystemBase {
  /**
   * Creates a new CannonBarrel.
   */
  /*private WPI_TalonSRX leftCannonBarrelMotor = new WPI_TalonSRX(Constants.CANNON_BARREL_LEFT_MOTOR);
  private WPI_TalonSRX rightCannonBarrelMotor = new WPI_TalonSRX(Constants.CANNON_BARREL_RIGHT_MOTOR);
  private WPI_TalonSRX leftCannonBarrelFeederMotor = new WPI_TalonSRX(Constants.CANNON_BARREL_FEEDER_LEFT_MOTOR);
  private WPI_TalonSRX rightCannonBarrelFeederMotor = new WPI_TalonSRX(Constants.CANNON_BARREL_FEEDER_RIGHT_MOTOR);*/

  public CannonBarrel() {

    stopCannonBarrel();
    //leftCannonBarrelMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    //rightCannonBarrelMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }

  public void stopCannonBarrel() {

    //leftCannonBarrelMotor.set(0);
    //rightCannonBarrelMotor.set(0);

    //leftCannonBarrelFeederMotor.set(0);
    //rightCannonBarrelFeederMotor.set(0);
  }

  public void loadCannonBarrelForStorage(double barrelSpeed, double feederSpeed) {
    setCannonBarrel(-barrelSpeed);
    setCannonBarrelFeeder(-feederSpeed);

  }

  public void loadCannonBarrelForShooting(double feederSpeed) {
    setCannonBarrelFeeder(feederSpeed);
  }

  public void shootCannonBarrel() {
    setCannonBarrel(Constants.CANNON_BARREL_LOAD_SPEED);
    while (!getCannonBarrelReadyToShoot()) {
      continue;
    }
    loadCannonBarrelForShooting(Constants.CANNON_BARREL_FEEDER_SPEED);
  }

  public boolean getCannonBarrelReadyToShoot() {
    if ((getLeftEncoderSpeed() >= Constants.CANNON_BARREL_LOAD_SPEED)
        && (getRightEncoderSpeed() >= Constants.CANNON_BARREL_LOAD_SPEED)) {
      return true;
    } else {
      return false;
    }
  }

  private void setCannonBarrel(double speed) {
    //leftCannonBarrelMotor.set(speed);
    //rightCannonBarrelMotor.set(speed);
  }

  private void setCannonBarrelFeeder(double feederSpeed) {
    //leftCannonBarrelFeederMotor.set(feederSpeed);
    //rightCannonBarrelFeederMotor.set(feederSpeed);
  }

  private double getLeftEncoderSpeed() {
    //return leftCannonBarrelMotor.getSelectedSensorVelocity();
    return 0;
  }

  private double getRightEncoderSpeed() {
    //return rightCannonBarrelMotor.getSelectedSensorVelocity();
    return 0;

  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Cannon Barrel Ready To Shoot", getCannonBarrelReadyToShoot());
    SmartDashboard.putNumber("Left Encoder Speed", getLeftEncoderSpeed());
    SmartDashboard.putNumber("Right Encoder Speed", getRightEncoderSpeed());
  }
}
