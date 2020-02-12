/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
 
package frc.robot.commands;
 
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
 
public class PositionShooter extends CommandBase {
  private static final double shooterDegree = 0;
  /**
   * Creates a new PositionShooter.
   */
  private double shooterTicks;
 
  public PositionShooter(double shooterDegree) { 
    this.shooterTicks = shooterDegree * Constants.SHOOTER_TICKS_PER_DEGREE;
    // Use addRequirements() here to declare subsystem dependencies.
  }
 
  public PositionShooter() {
    // Use addRequirements() here to declare subsystem dependencies.
  }
 
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }
 
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }
 
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
 
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (shooterDegree <= 0.0 ){return true;} else {return false;}
 
  }
}