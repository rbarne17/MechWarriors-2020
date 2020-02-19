/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.CannonPivotHorizontal;
import frc.robot.subsystems.CannonPivotVertical;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:s
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class PrepareCannonForShooting extends ParallelCommandGroup {
  /**
   * Creates a new PivotCannon.
   */
  public PrepareCannonForShooting(DriveTrain driveTrain, CannonPivotHorizontal cannonPivotHorizontal, CannonPivotVertical cannonPivotVertical,
      double driveDistance, double horizontalAngle, double verticalAngle) {

    super(new Drive(driveTrain, driveDistance), new PivotCannonHorizontal(cannonPivotHorizontal, horizontalAngle),
        new PivotCannonVertical(cannonPivotVertical, verticalAngle));
  }
}
