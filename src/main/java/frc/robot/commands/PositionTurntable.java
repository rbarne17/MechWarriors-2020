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
  //Not sure if the following line works ~Jay
  private static final double turntableDegree = 0;

  /**
   * Creates a new PositionTurntable.
   */
  private double m_pivotangle;
  private Turntable m_turntable;

  public PositionTurntable(Turntable turntable, double pivotAngle) {
    this.m_turntable = turntable;
    this.m_pivotangle = pivotAngle;
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
      m_turntable.resetTurntablePivotAngle();
    }

    // false means turn counterclockwise, true clockwise
    if (m_pivotangle > 0.0) {
      m_turntable.pivotTurntable(true);
    } else if (m_pivotangle < 0.0) {
      m_turntable.pivotTurntable(false);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //if angle to pivot to is greater than zero (clockwise) has the turntable pivoted to equal or greater than that angle
    if (((m_turntable.getTurntablePivotAngle() >= m_pivotangle) && (m_pivotangle > 0))
     //if angle to pivot to is less than zero (counterclockwise) has the turntable pivoted to equal or greater than that angle
     || (m_turntable.getTurntablePivotAngle() <= m_pivotangle) && (m_pivotangle < 0)) {
      return true;
    } else {
      return false;
    }
  }
}
