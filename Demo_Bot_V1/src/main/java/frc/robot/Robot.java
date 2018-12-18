/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DecreaseShooterPowerCommand;
import frc.robot.commands.DefaultMecanumDriveCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IncreaseShooterPowerCommand;
import frc.robot.commands.StopShooterCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // Subsystems! Yay!

  public static ShooterSubsystem Shooter;
  public static DriveTrain DriveTrainObject;

  Solenoid PistonTestOne = new Solenoid(RobotMap.TOP_SOLENOID_ID);	
	Solenoid PistonTestTwo = new Solenoid(RobotMap.BOTTOM_SOLENOID_ID);
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    Shooter = new ShooterSubsystem();
    DriveTrainObject = new DriveTrain();
    
    ////////////// Various
    DriveTrainObject.disableSafety();
    // This is an "okay" name for a method, you may change it if- and only if for that matter- your know what it does!!!
    DriveTrainObject.CorrectMotorDirectionForMecanumDrive();
    ////////////// Various
    m_chooser.addDefault("Default Auto", new ExampleCommand());
    // chooser.addObject("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);

    PistonTestOne.set(true);
		PistonTestTwo.set(true);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.

    // YOU CAN DELETE THE BELOW!! TODO
   //Shooter.leftShooterMotor.set(-0.1);
  //  Shooter.rightShooterMotor.set(0.1);

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */

  static boolean onOffOne = false;
  static boolean onOffTwo = false;
  static int Number = 0;
  @Override
  public void teleopPeriodic() {
  
    Scheduler.getInstance().run();
    // Please make sure this is working
   // new DefaultMecanumDriveCommand().start();
    // Please!!! TODO
    
 /*
    if(Number == 0) {
       onOffOne = false;
       onOffTwo = false;
       Number = 1;
     }
     */

    if(OI.ZeroSlotController.getRawButtonPressed(RobotMap.Y_BUTTON_ID)) {
      Shooter.leftShooterMotor.set(-0.1 + Shooter.leftShooterMotor.get());
      Shooter.rightShooterMotor.set(0.1 + Shooter.rightShooterMotor.get());
    }

    if(OI.ZeroSlotController.getRawButtonPressed(RobotMap.X_BUTTON_ID)) {
      Shooter.leftShooterMotor.set(0.1 + Shooter.leftShooterMotor.get());
      Shooter.rightShooterMotor.set(-0.1 + Shooter.rightShooterMotor.get());
    }
  
    if (OI.ZeroSlotController.getRawButtonPressed(RobotMap.B_BUTTON_ID)) {
      Shooter.leftShooterMotor.set(0);
      Shooter.rightShooterMotor.set(0);
    }

    if(OI.ZeroSlotController.getRawButtonPressed(RobotMap.A_BUTTON_ID)) {
      Shooter.leftShooterMotor.set(0.4);
      Shooter.rightShooterMotor.set(0.4);
    }
    
 
    if(OI.ZeroSlotController.getRawButtonPressed(RobotMap.LEFT_BUMPER_ID)) {
     
      if(!PistonTestOne.get()) {
      PistonTestOne.set(true);
      }
      
      if(PistonTestOne.get()) {
        PistonTestOne.set(false);
      }


    }

      if(OI.ZeroSlotController.getRawButtonPressed(RobotMap.RIGHT_BUMPER_ID)) {
    
        if(!PistonTestTwo.get()) {
          PistonTestTwo.set(true);
          }
          
          if(PistonTestTwo.get()) {
            PistonTestTwo.set(false);
          }
          }
          
     SmartDashboard.putData(PistonTestOne);   
     SmartDashboard.putData(PistonTestTwo);

      
  

    
    new DefaultMecanumDriveCommand().start();
    
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();

    SmartDashboard.putData(DriveTrainObject.MecDrive);

    SmartDashboard.putData(PistonTestOne);
    SmartDashboard.putData(PistonTestTwo);

    SmartDashboard.putData(DriveTrainObject.MecDrive);

   //  SmartDashboard.putData("Mecanum", new DefaultMecanumDriveCommand());
    // SmartDashboard.putData("Stop Shooter", new StopShooterCommand());
   //  SmartDashboard.putData("Increase Shooter Speed", new IncreaseShooterPowerCommand());
    // SmartDashboard.putData("Decrease Shotter Speed", new DecreaseShooterPowerCommand() );

  }
}
