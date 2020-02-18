/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutonomousMiddle;
import frc.robot.commands.DriveWithController;
import frc.robot.subsystems.CannonPivotHorizontal;
import frc.robot.subsystems.CannonPivotVertical;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  // TODO: add every subsystem here in the manner of m_drivetrain
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final CannonPivotHorizontal m_cannonPivotHorizontal = new CannonPivotHorizontal();
  private final CannonPivotVertical m_cannonPivotVertical = new CannonPivotVertical();

  private final AutonomousMiddle m_autoCommand = new AutonomousMiddle(m_driveTrain, m_cannonPivotHorizontal, m_cannonPivotVertical);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_driveTrain.setDefaultCommand(new DriveWithController(m_driveTrain));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // TODO: work with Miles to get what buttons he wants to use for what commands
    // TODO: what button/stick will Shoot()
    // TODO: what button/stick will LoadPowerCells()
    // TODO: what button/stick will PivotCannonHorizontal()
    // TODO: what button/stick will PivotCannonVertical()
    // TODO: what button/stick will trigger Climb()

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // TODO: determine how to select the autonomous command without rewriting code
    return m_autoCommand;
  }
}
