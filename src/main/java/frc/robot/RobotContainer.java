/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutonomousLeft;
import frc.robot.commands.AutonomousMiddle;
import frc.robot.commands.AutonomousRight;
import frc.robot.commands.Climb;
import frc.robot.commands.DriveWithController;
import frc.robot.commands.LoadPowerCells;
import frc.robot.commands.PivotCannonHorizontalWithController;
import frc.robot.commands.PivotCannonVerticalWithController;
import frc.robot.commands.RamCannon;
import frc.robot.commands.ShootAutonomous;
import frc.robot.commands.ShootTeleop;
import frc.robot.subsystems.CannonBarrel;
import frc.robot.subsystems.CannonPivotHorizontal;
import frc.robot.subsystems.CannonPivotVertical;
import frc.robot.subsystems.CannonRammer;
import frc.robot.subsystems.ClimbClaw;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.GroundLoader;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  private final CannonBarrel m_cannonBarrel = new CannonBarrel();
  private final CannonPivotHorizontal m_cannonPivotHorizontal = new CannonPivotHorizontal();
  private final CannonPivotVertical m_cannonPivotVertical = new CannonPivotVertical();
  private final CannonRammer m_cannonRammer = new CannonRammer();
  private final ClimbClaw m_climbClaw = new ClimbClaw();
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final GroundLoader m_groundLoader = new GroundLoader();
  private final AutonomousMiddle m_autoCommand = new AutonomousMiddle(m_driveTrain, m_cannonBarrel,m_cannonPivotHorizontal,
      m_cannonPivotVertical);

  private final XboxController driverControllerXbox = new XboxController(0);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    configureDefaultCommands();
    configureDashboard();

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    final Button buttonBumper = new JoystickButton(driverControllerXbox, XboxController.Button.kBumperRight.value);
    final Button buttonB = new JoystickButton(driverControllerXbox, XboxController.Button.kB.value);
    final Button triggerRight = new JoystickButton(driverControllerXbox, XboxController.Axis.kRightTrigger.value);

    buttonBumper.whenPressed(new LoadPowerCells(m_cannonPivotVertical, m_cannonPivotHorizontal, m_cannonRammer,
        m_cannonBarrel, m_groundLoader));
    buttonB.whenPressed(new Climb(m_climbClaw));
    triggerRight.whenPressed(new ShootTeleop(m_cannonBarrel));

  }

  private void configureDefaultCommands() {
    // default commands that will run whenever other commands have taken over
    // associated subsystems
    m_driveTrain.setDefaultCommand(
        new DriveWithController(m_driveTrain, () -> -1 * driverControllerXbox.getY(GenericHID.Hand.kLeft),
            () -> driverControllerXbox.getX(GenericHID.Hand.kLeft)));
    m_cannonPivotHorizontal.setDefaultCommand(new PivotCannonHorizontalWithController(m_cannonPivotHorizontal,
        () -> driverControllerXbox.getX(GenericHID.Hand.kRight)));
    m_cannonPivotVertical.setDefaultCommand(new PivotCannonVerticalWithController(m_cannonPivotVertical,
        () -> driverControllerXbox.getY(GenericHID.Hand.kRight)));
    m_cannonRammer.setDefaultCommand(new RamCannon(m_cannonRammer));
  }

  private void configureDashboard() {
    //subsystems
    SmartDashboard.putData(m_driveTrain);
    SmartDashboard.putData(m_cannonBarrel);
    SmartDashboard.putData(m_cannonPivotHorizontal);
    SmartDashboard.putData(m_cannonPivotVertical);
    SmartDashboard.putData(m_cannonRammer);
    SmartDashboard.putData(m_climbClaw);
    SmartDashboard.putData(m_driveTrain);
    
    //command groups
    SmartDashboard.putData("Autonomous Left",new AutonomousLeft(m_driveTrain, m_cannonBarrel, m_cannonPivotHorizontal, m_cannonPivotVertical));
    SmartDashboard.putData("Autonomous Middle",new AutonomousMiddle(m_driveTrain, m_cannonBarrel, m_cannonPivotHorizontal, m_cannonPivotVertical));
    SmartDashboard.putData("Autonomous Right",new AutonomousRight(m_driveTrain, m_cannonBarrel, m_cannonPivotHorizontal, m_cannonPivotVertical));
    SmartDashboard.putData("Load Power Cells",new LoadPowerCells(m_cannonPivotVertical, m_cannonPivotHorizontal, m_cannonRammer, m_cannonBarrel, m_groundLoader));
    SmartDashboard.putData("Shoot Autonomous Autonomous Constant",new ShootAutonomous( m_cannonBarrel, Constants.SHOOT_AUTONOMOUS_SECONDS));
    SmartDashboard.putData("Shoot Autonomous 5 seconds",new ShootAutonomous( m_cannonBarrel, 5));
    
    //commands




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
