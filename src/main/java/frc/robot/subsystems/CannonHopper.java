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

public class CannonHopper extends SubsystemBase {
  /**
   * Creates a new Hopper.
   */

  private WPI_TalonSRX m_leftcannonhoppermotor = new WPI_TalonSRX(Constants.CANNON_HOPPER_LEFT_MOTOR);
  private WPI_TalonSRX m_rightcannonhoppermotor = new WPI_TalonSRX(Constants.CANNON_HOPPER_RIGHT_MOTOR);

  public CannonHopper() {

    m_leftcannonhoppermotor.set(0);
    m_rightcannonhoppermotor.set(0);

  }
  public int getSnowBlowerMotor(){
    return 0;

  }
  public int getSrxEncoder(){
    return 0;

  }
//zero position switch

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
