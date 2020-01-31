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


public class Turntable extends SubsystemBase {
  /**
   * Creates a new Turntable.
   */
  private WPI_TalonSRX leftTurntableMotor = new WPI_TalonSRX(Constants.TURNTABLE_LEFT_MOTOR);
  private WPI_TalonSRX rightTurntableMotor = new WPI_TalonSRX(Constants.TURNTABLE_RIGHT_MOTOR);

  public Turntable() {

      leftTurntableMotor.set(0);
      rightTurntableMotor.set(0);
  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}