/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  private WPI_TalonSRX leftShooterMotor = new WPI_TalonSRX(Constants.SHOOTER_LEFT_MOTOR);
  private WPI_TalonSRX rightShooterMotor = new WPI_TalonSRX(Constants.SHOOTER_RIGHT_MOTOR);

  public Shooter() {

    leftShooterMotor.set(0);
    rightShooterMotor.set(0);

  }
//Encoder will be used for speed 
public int getMiniSims(){

}
 public int getSimEncoder(){

 }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
