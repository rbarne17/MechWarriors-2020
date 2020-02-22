/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import java.util.function.DoubleSupplier;

// TODO: see https://github.com/wpilibsuite/Gearsbot/blob/master/src/main/java/frc/robot/commands/TeleopDriveCommand.java and https://github.com/rbarne17/MechWarriors-2018/blob/master/src/main/java/org/usfirst/frc/team4764/robot/commands/DriveWithController.java for good example how to build this

public class DriveWithController extends CommandBase {
  private final DoubleSupplier m_xSpeed;
  private final DoubleSupplier m_zRotation;
  /**
   * Creates a new DriveWithController.
   */

  private DriveTrain m_driveTrain;

  public DriveWithController(DriveTrain driveTrain, DoubleSupplier xSpeed, DoubleSupplier zRotation) {
    this.m_driveTrain = driveTrain;
    this.m_xSpeed = xSpeed;
    this.m_zRotation = zRotation;
    addRequirements(m_driveTrain);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.driveByArcade(m_xSpeed.getAsDouble(), m_zRotation.getAsDouble());

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
