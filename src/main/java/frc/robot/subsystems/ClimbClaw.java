//*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ClimbClaw extends SubsystemBase {
  /**
   * Creates a new ClimbClaw.
   */

  //private WPI_TalonSRX climbClawMotor = new WPI_TalonSRX(Constants.CLIMB_CLAW_MOTOR);

  public ClimbClaw() {

    stopClimbClaw();

  }

  public void stopClimbClaw() {
    setClimbClawMotor(0.0);
  }

  public void setClimbClawAscend() {
    setClimbClawMotor(Constants.CLIMB_CLAW_SPEED);
  }

  public void setClimbClawAscend(double climbSpeed) {
    setClimbClawMotor(climbSpeed);
  }

  public void setClimbClawDescend()
  {
    setClimbClawMotor(-Constants.CLIMB_CLAW_SPEED);
  }

  private void setClimbClawMotor(double climbSpeed) {
    //climbClawMotor.set(climbSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
