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

public class CannonRammer extends SubsystemBase {
  /**
   * Creates a new Hopper.
   */

  private WPI_TalonSRX m_leftcannonrammermotor = new WPI_TalonSRX(Constants.CANNON_RAMMER_LEFT_MOTOR);
  private WPI_TalonSRX m_rightcannonrammermotor = new WPI_TalonSRX(Constants.CANNON_RAMMER_RIGHT_MOTOR);

  public CannonRammer() {

    m_leftcannonrammermotor.set(0);
    m_rightcannonrammermotor.set(0);

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
