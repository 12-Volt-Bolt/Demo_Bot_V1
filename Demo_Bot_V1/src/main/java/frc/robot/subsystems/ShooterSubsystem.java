/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class ShooterSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // You may need to change the following. You might be using SPARK motor controllers instead of the usual TAlONS
    public static WPI_TalonSRX leftShooterMotor = new WPI_TalonSRX(RobotMap.LEFT_BALL_LAUNCHER_MOTOR_ID); // TODO
    public static WPI_TalonSRX rightShooterMotor = new WPI_TalonSRX(RobotMap.RIGHT_BALL_LAUNCHER_MOTOR_ID);
    private final static int MOTOR_OFF = 0;
/*
  TODO: WIP
    public void BasicShooterMethod() {
        int leftSpeed = 0;
        int rightSpeed = 0;

        leftShooterMotor.set(leftSpeed);
        rightShooterMotor.set(rightSpeed);
    }

    */

    public void increaseSpeed(double increasePercentagePer) {
        // TODO: You should have an exception thrown the parameter is not positive
         leftShooterMotor.set(increasePercentagePer + leftShooterMotor.get());
         rightShooterMotor.set(increasePercentagePer - rightShooterMotor.get());
    }
    public void decreaseSpeed(double decreasePercentagePer) {
        // TODO: You should have an exception thrown the parameter is not negative
        leftShooterMotor.set(decreasePercentagePer + leftShooterMotor.get());
        rightShooterMotor.set(decreasePercentagePer - rightShooterMotor.get());
    }

    public void defaultSpeed() {
        leftShooterMotor.set(0.4);
        leftShooterMotor.set(0.4);
    }

    public void StopShooter() {
         leftShooterMotor.set(MOTOR_OFF);
         rightShooterMotor.set(MOTOR_OFF);
    }
      
      public double getLeftShooterSpeed() {
            return leftShooterMotor.get();
      }

      public double getRightShooterSpeed() {
              return rightShooterMotor.get();
      }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

  }
}