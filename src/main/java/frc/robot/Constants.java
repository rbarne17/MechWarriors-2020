/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    // PWM PORTS
    public static final int DRIVETRAIN_LEFT_SPARK = 0;
    public static final int DRIVETRAIN_RIGHT_SPARK = 1;

    // DIO PORTS
    public static final int CANNON_PIVOT_HORIZONTAL_LIMIT_ZERO = 0;
    public static final int CANNON_PIVOT_VERTICAL_LIMIT_ZERO = 1;
    public static final int CANNON_RAMMER_LIMIT_HOME = 3;
    public static final int CANNON_RAMMER_BEAM_SENSOR_BALL_IN_POSITION = 4;

    // ANALOG PORTS
    public static final int DRIVETRAIN_ULTRASONIC = 0;

    // CAN PORTS
    /*public static final int GROUND_LOADER_MOTOR = 9;
    public static final int CANNON_PIVOT_HORIZONTAL_MOTOR = 8;
    public static final int CANNON_PIVOT_VERTICAL_MOTOR = 7;
    public static final int CANNON_BARREL_LEFT_MOTOR = 5;
    public static final int CANNON_BARREL_RIGHT_MOTOR = 6;
    public static final int CANNON_RAMMER_MOTOR = 4;
    public static final int CLIMB_CLAW_MOTOR = 12;
    public static final int CANNON_BARREL_FEEDER_LEFT_MOTOR = 2;
    public static final int CANNON_BARREL_FEEDER_RIGHT_MOTOR = 3;*/
  
    // sensor/motor translation constants
    // TODO: get real values for these
    public static final double CANNON_PIVOT_VERTICAL_TICKS_PER_DEGREE = 0;
    public static final double CANNON_PIVOT_HORIZONTAL_TICKS_PER_DEGREE = 0;
    public static final double DRIVETRAIN_ULTRASONIC_VALUE_TO_INCHES = 0.125;

    // preferred speeds for subsystems
    // TODO: get real speeds for these motors
    public static final double CANNON_PIVOT_HORIZONTAL_SPEED = 0.5;
    public static final double CANNON_PIVOT_VERTICAL_SPEED = 0.5;
    public static final double CANNON_BARREL_LOAD_SPEED = 0.5;
    public static final double CANNON_BARREL_SHOOT_SPEED = 0.5;
    public static final double CANNON_BARREL_FEEDER_SPEED = 0.5;
    public static final double CANNON_RAMMER_SPEED = 0.5;
    public static final double CLIMB_CLAW_SPEED = .5;
    public static final double GROUND_LOADER_SPEED = 0.5;
    public static final double DRIVETRAIN_SPEED = 0.5;

    // AUTONOMOUS MODE VALUES
    // TODO: get real values
    public static final double DRIVE_AUTONOMOUS_SHOOTING_DISTANCE = 0.5;
    public static final double DRIVE_AUTONOMOUS_CROSS_LINE_DISTANCE = 0.5;
    public static final double CANNON_PIVOT_HORIZONTAL_AUTONOMOUS_LEFT_ANGLE = 0.5;
    public static final double CANNON_PIVOT_VERTICAL_AUTONOMOUS_LEFT_ANGLE = 0.5;
    public static final double CANNON_PIVOT_HORIZONTAL_AUTONOMOUS_MIDDLE_ANGLE = 0.5;
    public static final double CANNON_PIVOT_VERTICAL_AUTONOMOUS_MIDDLE_ANGLE = 0.5;
    public static final double CANNON_PIVOT_HORIZONTAL_AUTONOMOUS_RIGHT_ANGLE = 0.5;
    public static final double CANNON_PIVOT_VERTICAL_AUTONOMOUS_RIGHT_ANGLE = 0.5;

    // OTHER CONSTANTS
    // TODO: get real values
    public static final int SHOOT_AUTONOMOUS_SECONDS = 3;

}
