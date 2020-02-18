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
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutonomousLeft extends SequentialCommandGroup {
  /**
   * Creates a new AutonomousLeft.
   */
  public AutonomousLeft(DriveTrain driveTrain, CannonPivotHorizontal cannonPivotHorizontal,
      CannonPivotVertical cannonPivotVertical) {

    super(
        new PrepareCannonForShooting(driveTrain, cannonPivotHorizontal, cannonPivotVertical,
            Constants.CANNON_PIVOT_HORIZONTAL_AUTONOMOUS_LEFT_ANGLE,
            Constants.CANNON_PIVOT_VERTICAL_AUTONOMOUS_LEFT_ANGLE, Constants.DRIVE_AUTONOMOUS_SHOOTING_DISTANCE),
        new Drive(driveTrain, Constants.DRIVE_AUTONOMOUS_CROSS_LINE_DISTANCE));
  }
}
