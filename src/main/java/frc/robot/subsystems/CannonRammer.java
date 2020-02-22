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
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CannonRammer extends SubsystemBase {
  /**
   * Creates a new CannonRammer.
   */

  private WPI_TalonSRX m_cannonrammermotor = new WPI_TalonSRX(Constants.CANNON_RAMMER_MOTOR);
  private DigitalInput rammerHomeLimitSwitch = new DigitalInput(Constants.CANNON_RAMMER_LIMIT_HOME);
  private DigitalInput rammerBallPositionIRSensor = new DigitalInput(Constants.CANNON_RAMMER_BEAM_SENSOR_BALL_IN_POSITION);

  public CannonRammer() {

    setCannonRammerLoad();

  }

  public void setCannonRammerLoad() {
    while (!getHome()) {
      setCannonRammer(-Constants.CANNON_RAMMER_SPEED);
    }
    stopCannonRammer();
  }

  public boolean getHome() {
    return rammerHomeLimitSwitch.get();
  }

  public void setCannonRammerRam() {
    while (!getPowerCellsReadyToShoot()) {
      setCannonRammer(Constants.CANNON_RAMMER_SPEED);
    }
  }

  public boolean getPowerCellsReadyToShoot() {
    return rammerBallPositionIRSensor.get();
  }

  private void stopCannonRammer() {
    setCannonRammer(0.0);
  }

  private void setCannonRammer(double rammerSpeed) {
    m_cannonrammermotor.set(rammerSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
