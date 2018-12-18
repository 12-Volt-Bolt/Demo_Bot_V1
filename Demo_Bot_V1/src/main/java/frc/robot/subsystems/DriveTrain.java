/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.OI;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public WPI_TalonSRX FrontRight = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_MOTOR_ID);
	public WPI_TalonSRX RearLeft = new WPI_TalonSRX(RobotMap.REAR_LEFT_MOTOR_ID);
	public WPI_TalonSRX RearRight = new WPI_TalonSRX(RobotMap.REAR_RIGHT_MOTOR_ID);
	public WPI_TalonSRX FrontLeft = new WPI_TalonSRX(RobotMap.FRONT_LEFT_MOTOR_ID);
  
  public MecanumDrive MecDrive = new MecanumDrive(FrontLeft, RearLeft, FrontRight, RearRight);
  
  public void executeMecanumDrive() {
        MecDrive.setSafetyEnabled(false);
        // Look at this and fix it!!!!!!!!!!!!!!!
        MecDrive.driveCartesian(OI.ZeroSlotController.getRawAxis(RobotMap.LEFT_X_AXIS_ID) * 0.5, OI.ZeroSlotController.getRawAxis(RobotMap.LEFT_Y_AXIS_ID) * -0.5, OI.ZeroSlotController.getRawAxis(RobotMap.RIGHT_X_AXIS_ID) * 0.5);
   }
    
   // This method is used in the robot init method in the Robot class
   public void CorrectMotorDirectionForMecanumDrive() {
          // Please do not edit this unless you know the purpose of it.
  
          FrontRight.setInverted(true);
           FrontLeft.setInverted(true);
           RearLeft.setInverted(true);
           RearRight.setInverted(true);


   }

   public void StopThePresses() {
       MecDrive.driveCartesian(RobotMap.MOTOR_OFF, RobotMap.MOTOR_OFF, RobotMap.MOTOR_OFF);
   }

   public void disableSafety() {
     MecDrive.setSafetyEnabled(false);
   }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

  }
}
