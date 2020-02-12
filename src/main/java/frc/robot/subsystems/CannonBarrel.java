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

public class CannonBarrel extends SubsystemBase {
  private static final WPI_TalonSRX shooterrightMotor = null;
  private static final double CONSTANTS_SHOOTER_RIGHT_MOTOR_SPEED = 0;
  private static final WPI_TalonSRX shooterleftMotor = null;
  private static final double CONSTANTS_SHOOTER_LEFT_MOTOR_SPEED = 0;
  /**
   * Creates a new Shooter.
   */
  private WPI_TalonSRX leftCannonBarrelMotor = new WPI_TalonSRX(Constants.CANNON_BARREL_LEFT_MOTOR);
  private WPI_TalonSRX rightCannonBarrelMotor = new WPI_TalonSRX(Constants.CANNON_BARREL_RIGHT_MOTOR);

  public CannonBarrel() {

    leftCannonBarrelMotor.set(0);
    rightCannonBarrelMotor.set(0);
    stopShooter();
    leftCannonBarrelMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    rightCannonBarrelMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }
  public void stopShooter(){
  }
  public void positionShooter(boolean direction){
    if (direction)
    shooterrightMotor.set(CONSTANTS_SHOOTER_RIGHT_MOTOR_SPEED);
  } {
    shooterleftMotor.set(CONSTANTS_SHOOTER_LEFT_MOTOR_SPEED);
  };
{
  shooterleftMotor.set(CONSTANTS_SHOOTER_LEFT_MOTOR_SPEED);
} {
  shooterleftMotor.set(CONSTANTS_SHOOTER_LEFT_MOTOR_SPEED);
  shooterleftMotor.set(CONSTANTS_SHOOTER_LEFT_MOTOR_SPEED);
}

  
//Encoder will be used for speed 
public int getMiniSims(){
  return 0;
}
 public int getSimEncoder(){
   return 0;
 }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
