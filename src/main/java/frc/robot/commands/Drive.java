/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Drive extends CommandBase {
  /**
   * Creates a new Drive.
   */
  private DriveTrain m_DriveTrain;
  private double m_driveSpeed;
  private double m_driveAngle;
  private double m_driveDistance;

  public Drive(DriveTrain driveTrain, double driveSpeed, double driveAngle, double driveDistance) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_DriveTrain = driveTrain;
    m_driveSpeed = driveSpeed;
    m_driveAngle = driveAngle;
    m_driveDistance = driveDistance;
    addRequirements(m_DriveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }
  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_DriveTrain.driveByArcade(m_driveSpeed,m_driveAngle);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_DriveTrain.getDistance() >= m_driveDistance;
  }
}
