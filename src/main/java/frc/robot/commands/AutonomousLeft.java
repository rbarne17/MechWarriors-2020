/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.CannonPivotHorizontal;
import frc.robot.subsystems.CannonPivotVertical;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutonomousLeft extends SequentialCommandGroup {
  /**
   * Creates a new AutonomousLeft.
   */
  // TODO: create constructor with at least the necessary subsystem(s)
  public AutonomousLeft(CannonPivotHorizontal cannonPivotHorizontal, CannonPivotVertical cannonPivotVertical) {
    // TODO: Add commands that make up AutonomousLeft in the super() call,  super(new FooCommand(), new BarCommand());

    super(new PivotCannon (cannonPivotHorizontal, cannonPivotVertical, Constants.CANNON_PIVOT_HORIZONTAL_AUTONOMOUS_LEFT_ANGLE, Constants.CANNON_PIVOT_VERTICAL_AUTONOMOUS_LEFT_ANGLE));
  }
}
