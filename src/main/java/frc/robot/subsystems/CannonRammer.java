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
   * Creates a new CannonRammer.
   */

  private WPI_TalonSRX m_cannonrammermotor = new WPI_TalonSRX(Constants.CANNON_RAMMER_MOTOR);
  //TODO: add break beam sensor declaration
  //TODO: add limit switch declaration

  public CannonRammer() {

    setHome();

  }

  public void setHome() {
    while (!getHome()) {
      setCannonRammer(-Constants.CANNON_RAMMER_SPEED);
    }
    stopCannonRammer();
  }

  public boolean getHome() {
    // TODO: add limit switch code
    return false;
  }

  public void setCannonRammerRam(){
  setCannonRammer(Constants.CANNON_RAMMER_SPEED);
  }
  
  private void stopCannonRammer() {
    setCannonRammer(0.0);
  }

  private void setCannonRammer(double rammerSpeed) {
    m_cannonrammermotor.set(rammerSpeed);
  }

  public boolean getPowerCellsReadyToShoot(){
    //TODO: if beam broken, true; else false
    return true;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
