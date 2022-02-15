// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Command.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PixyCamera;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.Drive;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class AutoPixyDrive extends CommandBase {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
        private final Drive m_drive;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    final double scrnAreaConst = 6000; //represents used screen area
    final double maxDrivePower = 0.8; //sets max speed
    final double minDrivePower = 0.2; //sets min speed
    Block currentBlock = null;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS


    public AutoPixyDrive(Drive subsystem) {


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        m_drive = subsystem;
        addRequirements(m_drive);


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        Block b = PixyCamera.getBiggestBlock(m_drive.chaseColor);
        currentBlock = b;
        SmartDashboard.putBoolean("DriverDashboard/Target Found", b != null);
        if (b != null) {
            double targetPOS = 0;
            double mid_x = b.getX() + (b.getWidth() / 2.0); //finds midpoint of target
            double steerCorrect;
            double area = b.getWidth() * b.getHeight(); //finds area of target
            double speed = 1 - (area / scrnAreaConst); //calculates speed needed for how far away target is
            //finds turn direction
            if (mid_x > 160) {
                targetPOS = 1.0 * (mid_x - 160.0);
            } else {
                targetPOS = -1.0 * (160.0 - mid_x);
            }
            steerCorrect = targetPOS / 160;
            //checks if speed is greater than max
            if (speed > maxDrivePower) { 
                speed = maxDrivePower;
            }
            //checks if speed is less than min
            if (speed < minDrivePower) {
                speed = minDrivePower;
            }
            SmartDashboard.putNumber("DriverDashboard/Speed", speed);
            SmartDashboard.putNumber("DriverDashboard/Turn", steerCorrect);
            m_drive.autoPixyChase(speed, steerCorrect);
            System.out.println("working");
        }

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {

        return currentBlock == null;

    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED

    }
}
