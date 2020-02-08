/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //CHANGED PORTS
    public static final int DRIVETRAIN_LEFT_SPARK = 0;
    public static final int DRIVETRAIN_RIGHT_SPARK = 1;

    //CAN PORTS
    public static final int FUELINTAKE_LEFT_MOTOR = 1;
    public static final int FUELINTAKE_RIGHT_MOTOR = 2;
    public static final int TURNTABLE_LEFT_MOTOR = 3;
    public static final int TURNTABLE_RIGHT_MOTOR = 4;
    public static final int SHOOTER_LEFT_MOTOR = 5;
    public static final int SHOOTER_RIGHT_MOTOR = 6;

    public static final int HOPPER_LEFT_MOTOR = 7;
    public static final int HOPPER_RIGHT_MOTOR = 8;
    public static final int CLIMB_CLAW_MOTOR = 9;
    public static final int FUEL_INTAKE_MOTOR = 10;
    public static final double TURNTABLE_TICKS_PER_DEGREE = 0;
    public static final double SHOOTER_TICKS_PER_DEGREE = 0;

    
}
