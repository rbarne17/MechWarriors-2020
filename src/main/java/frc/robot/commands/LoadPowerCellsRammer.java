/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CannonRammer;

public class LoadPowerCellsRammer extends CommandBase {
  /**
   * Creates a new LoadPowerCellsRammer.
   */
  private CannonRammer m_cannonRammer;

  public LoadPowerCellsRammer(CannonRammer cannonRammer) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_cannonRammer = cannonRammer;
    addRequirements(m_cannonRammer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_cannonRammer.setHome();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_cannonRammer.getHome();
  }
}