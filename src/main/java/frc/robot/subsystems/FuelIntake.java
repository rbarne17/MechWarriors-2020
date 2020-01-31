/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//May have to install the pheonix ones
//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class FuelIntake extends SubsystemBase {
  private WPI_TalonSRX leftIntakeMotor = new WPI_TalonSRX(Constants.FUELINTAKE_LEFT_MOTOR);
  private WPI_TalonSRX rightIntakeMotor = new WPI_TalonSRX(Constants.FUELINTAKE_RIGHT_MOTOR);

  /**
   * Creates a new FuelIntake.
   */

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //change to CAN (?)
	public final int ticksPerFoot = 166;  
  
  public FuelIntake() {

    leftIntakeMotor.set(0.0);
		rightIntakeMotor.set(0.0);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
