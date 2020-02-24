/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CannonBarrel;

public class ShootCannonBarrel extends CommandBase {

  private CannonBarrel m_cannonBarrel;
  private int m_shootSeconds;
  private Timer m_shootCannonBarrelTimer;

  /**
   * Creates a new ShootCannonBarrel.
   */
  public ShootCannonBarrel(CannonBarrel cannonBarrel, int shootSeconds) {
    m_cannonBarrel = cannonBarrel;
    m_shootSeconds = shootSeconds;
    m_shootCannonBarrelTimer = new Timer();
    addRequirements(m_cannonBarrel);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (m_shootSeconds > 0) {
      m_shootCannonBarrelTimer.start();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_cannonBarrel.shootCannonBarrel();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //if m_shootSeconds is 0 or less, don't stop shooting
    if (m_shootCannonBarrelTimer.get() > m_shootSeconds * 1000) {
      return true;
    } else {
      return false;
    }
  }
}
