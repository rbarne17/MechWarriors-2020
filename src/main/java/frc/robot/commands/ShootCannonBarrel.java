/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CannonBarrel;

// TODO: create constructor with at least the necessary subsystem(s)
// TODO: place subsystem in addRequirements()
public class ShootCannonBarrel extends CommandBase {
  
  private CannonBarrel m_cannonBarrel;

/**
   * Creates a new ShootCannonBarrel.
   */
  public ShootCannonBarrel(CannonBarrel cannonBarrel) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_cannonBarrel = cannonBarrel;
    addRequirements(m_cannonBarrel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
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
    return false;
  }
}
