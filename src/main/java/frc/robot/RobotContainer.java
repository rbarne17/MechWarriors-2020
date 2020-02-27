/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutonomousLeft;
import frc.robot.commands.AutonomousMiddle;
import frc.robot.commands.AutonomousRight;
import frc.robot.commands.Climb;
import frc.robot.commands.Drive;
import frc.robot.commands.DriveWithController;
import frc.robot.commands.LoadPowerCells;
import frc.robot.commands.LoadPowerCellsCannon;
import frc.robot.commands.LoadPowerCellsGround;
import frc.robot.commands.LoadPowerCellsRammer;
import frc.robot.commands.PivotCannonHorizontal;
import frc.robot.commands.PivotCannonHorizontalWithController;
import frc.robot.commands.PivotCannonVertical;
import frc.robot.commands.PivotCannonVerticalWithController;
import frc.robot.commands.PrepareCannonForShooting;
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
import frc.robot.vision.BallTargetVisionPipeline;
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
  private final AutonomousMiddle m_autoCommand = new AutonomousMiddle(m_driveTrain, m_cannonBarrel,
      m_cannonPivotHorizontal, m_cannonPivotVertical);

  private final XboxController driverControllerXbox = new XboxController(0);

  private static final int IMG_WIDTH = 160; // Vision image width(pixels)
  private static final int IMG_HEIGHT = 120; // Vision image height(pixels)

  private VisionThread visionThread; // Java Thread class housing vision processing callback (init below)
  private Point targetCenter = new Point(1024, 1024); // Stores the current center of target; (1024,1024) signifies "No
                                                      // Target"

  private final Object imgLock = new Object(); // Syncronizes read/write calls from/to targetCenter

  private CvSource aimVideoSource; // Custom video feed sent to dashboard (shows R/Y/G indication)

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    configureDefaultCommands();
    configureVisionAssist();
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

  private void configureVisionAssist() {
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(); // Starts capture from camera, streams to
    // dashboard and vision code
    camera.setResolution(IMG_WIDTH, IMG_HEIGHT); // Sets camera resolution

    camera.setExposureManual(20); // Sets camera exposure low for more reliable vision processing
    camera.setBrightness(20);
    camera.setConfigJson("{\"properties\": [{\"name\": \"raw_exposure_absolute\", \"value\": 20}]}");
    System.out.println(camera.getConfigJson());
    aimVideoSource = CameraServer.getInstance().putVideo("Aim Assistance", IMG_WIDTH, IMG_HEIGHT);

    final int crosshairSize = 10;

    visionThread = new VisionThread(camera, new BallTargetVisionPipeline(), pipeline -> { // Initialize visionthread
      boolean rumble = false;
      Mat aimMat = pipeline.resizeImageOutput(); // Mat object from camera for Aim Assistance
      if (!pipeline.filterContoursOutput().isEmpty()) { // Were any contours found by vision pipeline?
        Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0)); // If so, get the bounding Rect of the
                                                                               // first contour
        synchronized (imgLock) { // Syncronizes read/write calls from/to targetCenter
          targetCenter = new Point(r.x + (r.width / 2), r.y + (r.height / 2)); // Sets targetCenter to center position
                                                                               // of contour
          Imgproc.drawMarker(aimMat, new Point(IMG_WIDTH / 2, IMG_HEIGHT / 2), new Scalar(255, 255, 255),
              Imgproc.MARKER_CROSS, crosshairSize);
          final double centerPrecision = 10;

          if (targetCenter.x >= (IMG_WIDTH / 2) - centerPrecision && targetCenter.x <= (IMG_WIDTH / 2)
              + centerPrecision/*
                                * && targetCenter.y >= (IMG_HEIGHT/2)-centerPrecision && targetCenter.y <=
                                * (IMG_HEIGHT/2)+centerPrecision
                                */) {
            Imgproc.drawMarker(aimMat, targetCenter, new Scalar(0, 255, 0), Imgproc.MARKER_CROSS, crosshairSize);
            rumble = true;
          } else {
            Imgproc.drawMarker(aimMat, targetCenter, new Scalar(0, 255, 255), Imgproc.MARKER_CROSS, crosshairSize);
          }
        }
      } else { // No contours found
        synchronized (imgLock) { // Syncronizes read/write calls from/to targetCenter
          targetCenter = new Point(1024, 1024); // 1024 signifies "No Target"
          Imgproc.drawMarker(aimMat, new Point((IMG_WIDTH / 2), (IMG_HEIGHT / 2)), new Scalar(0, 0, 255),
              Imgproc.MARKER_CROSS, crosshairSize);
        }
      }
      aimVideoSource.putFrame(aimMat); // Publish Aim Assistance to dashboard
      if (rumble) {
        driverControllerXbox.setRumble(RumbleType.kLeftRumble, 1);
        driverControllerXbox.setRumble(RumbleType.kRightRumble, 1);
      } else {
        driverControllerXbox.setRumble(RumbleType.kLeftRumble, 0);
        driverControllerXbox.setRumble(RumbleType.kRightRumble, 0);
      }
    });
    visionThread.start();
  }

  private void configureDashboard() {
    // subsystems
    SmartDashboard.putData(m_driveTrain);
    SmartDashboard.putData(m_cannonBarrel);
    SmartDashboard.putData(m_cannonPivotHorizontal);
    SmartDashboard.putData(m_cannonPivotVertical);
    SmartDashboard.putData(m_cannonRammer);
    SmartDashboard.putData(m_climbClaw);
    SmartDashboard.putData(m_driveTrain);

    // command groups
    SmartDashboard.putData("Autonomous Left",
        new AutonomousLeft(m_driveTrain, m_cannonBarrel, m_cannonPivotHorizontal, m_cannonPivotVertical));
    SmartDashboard.putData("Autonomous Middle",
        new AutonomousMiddle(m_driveTrain, m_cannonBarrel, m_cannonPivotHorizontal, m_cannonPivotVertical));
    SmartDashboard.putData("Autonomous Right",
        new AutonomousRight(m_driveTrain, m_cannonBarrel, m_cannonPivotHorizontal, m_cannonPivotVertical));
    SmartDashboard.putData("Load Power Cells", new LoadPowerCells(m_cannonPivotVertical, m_cannonPivotHorizontal,
        m_cannonRammer, m_cannonBarrel, m_groundLoader));
    SmartDashboard.putData("Shoot Autonomous Autonomous Constant",
        new ShootAutonomous(m_cannonBarrel, Constants.SHOOT_AUTONOMOUS_SECONDS));
    SmartDashboard.putData("Shoot Autonomous 5 seconds", new ShootAutonomous(m_cannonBarrel, 5));
    SmartDashboard.putData(
        "Prepare Cannon for Shooting - Drive 2 Feet, Pivot Horizontal 30 Degrees, Pivot Vertical 30 Degrees",
        new PrepareCannonForShooting(m_driveTrain, m_cannonPivotHorizontal, m_cannonPivotVertical, 24, 30, 30));
    // commands
    SmartDashboard.putData("Climb", new Climb(m_climbClaw));
    SmartDashboard.putData("Drive Forward 5 Feet", new Drive(m_driveTrain, Constants.DRIVETRAIN_SPEED, 0, 60));
    SmartDashboard.putData("Load Power Cells Cannon", new LoadPowerCellsCannon(m_cannonBarrel));
    SmartDashboard.putData("Load Power Cells Ground", new LoadPowerCellsGround(m_groundLoader));
    SmartDashboard.putData("Load Power Cells Rammer", new LoadPowerCellsRammer(m_cannonRammer));
    SmartDashboard.putData("Pivot Cannon Horizontal 30 Degrees",
        new PivotCannonHorizontal(m_cannonPivotHorizontal, 30));
    SmartDashboard.putData("Pivot Cannon Vertical 30 Degrees", new PivotCannonVertical(m_cannonPivotVertical, 30));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // TODO: determine how to select the autonomous command without rewriting code

    // REPLY ^^^^ inside of FRC's 2014 documentation they make reference to a
    // "scheduler" in "RobotBuilder" that will
    // 'generate code automatically that runs the scheduler every driver station
    // update period'
    // we can look into this if this seems helpful(?) the link for the article is
    // below:
    // https://wpilib.screenstepslive.com/s/3120/m/7932/l/81109-choosing-an-autonomous-program-from-smartdashboard
    return m_autoCommand;
  }
}
