/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.CannonBarrel;

  /**
   * Creates a new ShootAutonomous.
   */
public class ShootAutonomous extends SequentialCommandGroup {
    public ShootAutonomous(CannonBarrel cannonBarrel, int shootSeconds){
        super(new ShootCannonBarrel(cannonBarrel, shootSeconds));
    }
}
