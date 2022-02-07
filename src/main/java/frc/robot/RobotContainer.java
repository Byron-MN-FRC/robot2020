// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: RobotContainer.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.acquire;
import frc.robot.commands.alexaFindBall;
import frc.robot.commands.cmdDriverCamera;
import frc.robot.commands.deployAcquisition;
import frc.robot.commands.driveWithJoystick;
import frc.robot.commands.dummyOne;
import frc.robot.commands.dummyTwo;
import frc.robot.commands.enableClimb;
import frc.robot.commands.enableLimeLight;
import frc.robot.commands.manualMagazineDown;
import frc.robot.commands.manualMagazineUp;
import frc.robot.commands.retractAcquisition;
import frc.robot.commands.reverseAcquire;
import frc.robot.commands.shoot;
import frc.robot.commands.togglePixyColor;
import frc.robot.subsystems.Drive;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot
 * (including subsystems, commands, and button mappings) should be declared
 * here.
 */
public class RobotContainer {

    private static RobotContainer m_robotContainer = new RobotContainer();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
// The robot's subsystems
    public final Drive m_drive = new Drive();

// Joysticks
private final Joystick operatorTwo = new Joystick(1);
private final Joystick operatorOne = new Joystick(0);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // A chooser for autonomous commands
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    private RobotContainer() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Smartdashboard Subsystems


    // SmartDashboard Buttons
    SmartDashboard.putData("enableLimeLight", new enableLimeLight( m_drive ));
    SmartDashboard.putData("togglePixyColor", new togglePixyColor( m_drive ));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
        // Configure the button bindings
        configureButtonBindings();

        // Configure default commands
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND
    m_drive.setDefaultCommand(new driveWithJoystick( m_drive ) );


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

        // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    m_chooser.addOption("dummyOne", new dummyOne( m_drive ));
    m_chooser.addOption("dummyTwo", new dummyTwo( m_drive ));
    m_chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        SmartDashboard.putData("Auto Mode", m_chooser);
    }

    public static RobotContainer getInstance() {
        return m_robotContainer;
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
     * it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
// Create some buttons
final JoystickButton btnEnableClimbTwo = new JoystickButton(operatorTwo, 7);        
btnEnableClimbTwo.whenPressed(new enableClimb() ,true);
    SmartDashboard.putData("btnEnableClimbTwo",new enableClimb() );

final JoystickButton btnDeployIntakeTwo = new JoystickButton(operatorTwo, 6);        
btnDeployIntakeTwo.whenPressed(new deployAcquisition() ,true);
    SmartDashboard.putData("btnDeployIntakeTwo",new deployAcquisition() );

final JoystickButton btnRetractIntakeTwo = new JoystickButton(operatorTwo, 4);        
btnRetractIntakeTwo.whenPressed(new retractAcquisition() ,true);
    SmartDashboard.putData("btnRetractIntakeTwo",new retractAcquisition() );

final JoystickButton btnMagazineUp = new JoystickButton(operatorTwo, 3);        
btnMagazineUp.whileHeld(new manualMagazineUp() ,true);
    SmartDashboard.putData("btnMagazineUp",new manualMagazineUp() );

final JoystickButton btnMagazineDown = new JoystickButton(operatorTwo, 5);        
btnMagazineDown.whileHeld(new manualMagazineDown() ,true);
    SmartDashboard.putData("btnMagazineDown",new manualMagazineDown() );

final JoystickButton btnEnableLimelight = new JoystickButton(operatorTwo, 2);        
btnEnableLimelight.whileHeld(new enableLimeLight( m_drive ) ,true);
    SmartDashboard.putData("btnEnableLimelight",new enableLimeLight( m_drive ) );

final JoystickButton btnShoot = new JoystickButton(operatorTwo, 1);        
btnShoot.whileHeld(new shoot() ,true);
    SmartDashboard.putData("btnShoot",new shoot() );

final JoystickButton btnDriverCamera = new JoystickButton(operatorOne, 2);        
btnDriverCamera.whileHeld(new cmdDriverCamera() ,true);
    SmartDashboard.putData("btnDriverCamera",new cmdDriverCamera() );

final JoystickButton btnTogglePixyColor = new JoystickButton(operatorOne, 12);        
btnTogglePixyColor.whenPressed(new togglePixyColor( m_drive ) ,true);
    SmartDashboard.putData("btnTogglePixyColor",new togglePixyColor( m_drive ) );

final JoystickButton btnEnableClimb = new JoystickButton(operatorOne, 7);        
btnEnableClimb.whenPressed(new enableClimb() ,true);
    SmartDashboard.putData("btnEnableClimb",new enableClimb() );

final JoystickButton btnDeployIntake = new JoystickButton(operatorOne, 6);        
btnDeployIntake.whenPressed(new deployAcquisition() ,true);
    SmartDashboard.putData("btnDeployIntake",new deployAcquisition() );

final JoystickButton btnRetractIntake = new JoystickButton(operatorOne, 4);        
btnRetractIntake.whenPressed(new retractAcquisition() ,true);
    SmartDashboard.putData("btnRetractIntake",new retractAcquisition() );

final JoystickButton btnAcquire = new JoystickButton(operatorOne, 5);        
btnAcquire.whileHeld(new acquire( m_drive ) ,false);
    SmartDashboard.putData("btnAcquire",new acquire( m_drive ) );

final JoystickButton btnReverseAcquire = new JoystickButton(operatorOne, 3);        
btnReverseAcquire.whileHeld(new reverseAcquire( m_drive ) ,true);
    SmartDashboard.putData("btnReverseAcquire",new reverseAcquire( m_drive ) );

final JoystickButton btnFindBall = new JoystickButton(operatorOne, 1);        
btnFindBall.whileHeld(new alexaFindBall( m_drive ) ,true);
    SmartDashboard.putData("btnFindBall",new alexaFindBall( m_drive ) );



    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
        // final JoystickButton btnTurn2LimeLight = new JoystickButton(joystick, 2);
        // btnTurn2LimeLight.whenPressed(new turn2LimeLight( m_drive ).withTimeout(5.0)
        // ,true);
        // SmartDashboard.putData("btnTurn2LimeLight",new turn2LimeLight( m_driveTrain
        // ).withTimeout(5.0) );

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public Joystick getoperatorOne() {
        return operatorOne;
    }

public Joystick getoperatorTwo() {
        return operatorTwo;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // The selected command will be run in autonomous
        return m_chooser.getSelected();
    }

}
