/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turntable;

public class PositionTurntable extends CommandBase {
  /**
   * Creates a new PositionTurntable.
   */
  private double m_turntabledegrees;
  private Turntable m_turntable;

  public PositionTurntable(Turntable turntable, double turnTableDegrees) {
    this.m_turntable = turntable;
    this.m_turntabledegrees = turnTableDegrees;
    addRequirements(m_turntable);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if passing through home position, reset pivot degrees to 0
    if (m_turntable.getHome()) {
      m_turntable.resetTurntablePivotDegrees();
    }

    // false means turn counterclockwise, true clockwise
    if (m_turntabledegrees > 0.0) {
      m_turntable.pivotTurntable(false);
    } else if (m_turntabledegrees < 0.0) {
      m_turntable.pivotTurntable(true);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(m_turntable.getTurntablePivotDegrees()) >= Math.abs(m_turntabledegrees)) {
      return true;
    } else {
      return false;
    }
  }
}
