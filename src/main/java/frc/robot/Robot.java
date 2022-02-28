// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Robot.

package frc.robot;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    private Command m_autonomousCommand;
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private RobotContainer m_robotContainer;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
        // autonomous chooser on the dashboard.
        m_robotContainer = RobotContainer.getInstance();
        HAL.report(tResourceType.kResourceType_Framework, tInstances.kFramework_RobotBuilder);

        // server = CameraServer.getInstance();
        // server.startAutomaticCapture("forward",0);
        LimelightUtility.EnableDriverCamera(true);
        LimelightUtility.StreamingMode(LimelightUtility.StreamMode.PIPMain);
        //LimelightUtility.WriteDouble("ledMode", 1); // 3 = Limelight O


        // 2022 - The robot needs to konw the current alliance color.   The following will read the alliance
        // color from the driver station which gets it from the Field Management System.   We use the color to 
        // set the initial pixy color tracking to the color of the alliance we are currently on. 
        if (DriverStation.getAlliance() == Alliance.Blue)
            m_robotContainer.m_drive.chaseColor = 1;
        else
            m_robotContainer.m_drive.chaseColor = 2;
        
        SmartDashboard.putString("DriverDashboard/AllianceColor", DriverStation.getAlliance().name());
        LimelightUtility.Stream();
        RobotContainer.getInstance().m_ballShooter.setMasterShootVelocity(0);
    }

    /**
    * This function is called every robot packet, no matter the mode. Use this for items like
    * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
    *
    * <p>This runs after the mode specific periodic functions, but before
    * LiveWindow and SmartDashboard integrated updating.
    */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
        //double IR = m_colorSensor.getIR();

        // SmartDashboard.putNumber("Test/IR", IR);
        // SmartDashboard.putBoolean("Test/Black Line", IR <= 6);
        
    }


    /**
    * This function is called once each time the robot enters Disabled mode.
    */
    @Override
    public void disabledInit() {
        LimelightUtility.EnableDriverCamera(false);
        RobotContainer.getInstance().m_ballIndexer.reinitializeIndexer();
        
        // RobotContainer.getInstance().m_ballShooter.setMasterShootVelocity(0);
    }

    @Override
    public void disabledPeriodic() {
    }

    /**
    * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
    */
    @Override
    public void autonomousInit() {
        m_autonomousCommand = m_robotContainer.getAutonomousCommand();
        RobotContainer.getInstance().m_ballIndexer.reinitializeIndexer();
        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
    }

    /**
    * This function is called periodically during autonomous.
    */
    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
        RobotContainer.getInstance().m_ballShooter.setMasterShootVelocity(0);
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }

    /**
    * This function is called periodically during test mode.
    */
    @Override
    public void testPeriodic() {
    }

}
