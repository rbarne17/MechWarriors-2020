/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.CannonBarrel;
import frc.robot.subsystems.CannonPivotHorizontal;
import frc.robot.subsystems.CannonPivotVertical;
import frc.robot.subsystems.CannonRammer;
import frc.robot.subsystems.GroundLoader;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class LoadPowerCells extends SequentialCommandGroup {

/**
   * Creates a new LoadPowerCells.
   */

   //composed of a sequence of 2 parallel commands: 1 pivoting the Cannon into load position, with CannonRammer going into load position, 2 loading the power cells between GroundLoader and CannonBarrel
  public LoadPowerCells(CannonPivotVertical cannonPivotVertical, CannonPivotHorizontal cannonPivotHorizontal, CannonRammer cannonRammer,CannonBarrel cannonBarrel, GroundLoader groundLoader) {
    super(new ParallelCommandGroup(new PivotCannonVertical(cannonPivotVertical, 0), new PivotCannonHorizontal(cannonPivotHorizontal, 0), new LoadPowerCellsRammer(cannonRammer)), new ParallelCommandGroup(new LoadPowerCellsCannon(cannonBarrel), new LoadPowerCellsGround(groundLoader)));
  }
}

