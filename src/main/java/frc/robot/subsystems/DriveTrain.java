/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.commands.DriveWithController;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  public final int ticksPerFoot = 166;
  public Spark leftMotor = new Spark(Constants.DRIVETRAIN_LEFT_SPARK);
  public Spark rightMotor = new Spark(Constants.DRIVETRAIN_RIGHT_SPARK);
  private Encoder leftEncoder;
  private Encoder rightEncoder;

  public DriveTrain() {
    leftMotor.set(0);
    rightMotor.set(0);
  }

  //COME BACK TO THIS for PERIODIC (using)
  //
  public void driveTrain(){
    setDefaultCommand(new DriveWithController());
  }

  public void driveByTank(double LeftpercentThrottle, double RightpercentThrottle){
    leftMotor.set(LeftpercentThrottle);
    rightMotor.set(RightpercentThrottle);
  
  }

  public void driveByArcade( double percentThrottle, double percentRotationOutput){

    percentThrottle = valueAfterDeadzone(percentThrottle);
    percentRotationOutput = valueAfterDeadzone(percentRotationOutput);

    percentThrottle = scalingSpeed(percentThrottle);
    percentRotationOutput = scalingSpeed(percentRotationOutput);

    leftMotor.set(-percentThrottle - percentRotationOutput);
    rightMotor.set(percentThrottle - percentRotationOutput);

  }
  public double scalingSpeed(double joystickValue){
    double scalingCutoff = .8;
    double joystickValueToTheThird = Math.pow(joystickValue, 3);
    return scalingCutoff * joystickValueToTheThird + ((1 - scalingCutoff) * joystickValue);
     
  }
  public void reset(){
    driveByTank(0.0, 0.0);
    encoderReset();
  }
  public void encoderReset(){
    leftEncoder.reset();
    rightEncoder.reset();

  }
  private double valueAfterDeadzone(double currentValue){
    double deadzone = 0.2;
    if(Math.abs(currentValue) < deadzone){
      return 0;
    } 
    else{
      return currentValue;
    
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
}
