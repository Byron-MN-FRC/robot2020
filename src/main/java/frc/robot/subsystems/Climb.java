// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;


// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


/**
 *
 */
public class Climb extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private DigitalInput lefthandLimitSwitch;
private DigitalInput leftElbowLimitSwitch;
private DigitalInput leftShoulderLimitSwitch;
private DigitalInput rightHandLimitSwitch;
private DigitalInput rightElbowLimitSwitch;
private DigitalInput rightShoulderLimitSwitch;
private WPI_TalonFX leftShoulder;
private WPI_TalonSRX leftElbow;
private WPI_TalonFX rightShoulder;
private WPI_TalonFX rightElbow;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private double BisepActuater;
private double ElbowAngle;
private double ElbowTarget;
private double ShoulderAngle;
private double ShoulderTarget;
private double ForearmActuater;
public boolean shoulderAngleHit;
public boolean elbowAngleHit;
private DutyCycleEncoder leftElbowEncoder;
private DutyCycleEncoder leftShoulderEncoder;
private DutyCycleEncoder rightElbowEncoder;
    /**
    *
    */
    public Climb() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
lefthandLimitSwitch = new DigitalInput(2);
 addChild("lefthandLimitSwitch", lefthandLimitSwitch);
 

leftElbowLimitSwitch = new DigitalInput(3);
 addChild("LeftElbowLimitSwitch", leftElbowLimitSwitch);
 

leftShoulderLimitSwitch = new DigitalInput(4);
 addChild("LeftShoulderLimitSwitch", leftShoulderLimitSwitch);
 

rightHandLimitSwitch = new DigitalInput(5);
 addChild("RightHandLimitSwitch", rightHandLimitSwitch);
 

rightElbowLimitSwitch = new DigitalInput(6);
 addChild("RightElbowLimitSwitch", rightElbowLimitSwitch);
 

rightShoulderLimitSwitch = new DigitalInput(7);
 addChild("RightShoulderLimitSwitch", rightShoulderLimitSwitch);
 

leftShoulder = new WPI_TalonFX(7);
 
 

leftElbow = new WPI_TalonSRX(8);
 
 

rightShoulder = new WPI_TalonFX(9);
 
 

rightElbow = new WPI_TalonFX(10);
 
 


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public boolean isLeftHand() {
        return lefthandLimitSwitch.get();
    }
    // public void leftArmMath(double BarX, double BarY) {
    //     ElbowAngle = leftElbowEncoder.get();
    //     //ShoulderAngle = shoulderPtLeft.get();
    //     // Calculates the angle that the elbow needs to be at
    //     ElbowTarget = Math.acos(((Constants.bicepLenght*Constants.bicepLenght)+(Constants.forearmLength*Constants.forearmLength)-(BarY*BarY)-(BarX*BarX))/(2*Constants.forearmLength*Constants.bicepLenght));
    //     // Calculates the angle that the shoulder needs to be at
    //     ShoulderTarget = 180-(Math.asin((Math.sin(ElbowTarget)*Constants.forearmLength)/(Math.pow(BarX*BarX*BarY*BarY, .5))))-Math.atan(BarY/BarX);
    // }
    public boolean leftArmElbowMath(double BarX, double BarY) {
        ElbowAngle = leftElbowEncoder.get()*Constants.encoderToDegree;
        ElbowTarget = Math.acos(((Constants.bicepLenght*Constants.bicepLenght)+(Constants.forearmLength*Constants.forearmLength)-(BarY*BarY)-(BarX*BarX))/(2*Constants.forearmLength*Constants.bicepLenght));
        if (ElbowAngle == ElbowTarget) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean leftArmShoulderMath(double BarX, double BarY) {
        ShoulderAngle = leftShoulderEncoder.get()*Constants.encoderToDegree;
        ShoulderTarget = 180-(Math.asin((Math.sin(ElbowTarget)*Constants.forearmLength)/(Math.pow(BarX*BarX*BarY*BarY, .5))))-Math.atan(BarY/BarX);
        if (ShoulderTarget == ShoulderAngle) {
            return true;
        }
        else {
            return false;
        }
    }
    public void startLeftElbow(double speed) {
        leftElbow.set(speed);
    }
    public void startLeftShoulder(double speed) {
        leftShoulder.set(speed);
    }
    public void stopLeftElbow() {
        leftElbow.stopMotor();
    }
    public void stopLeftShoulder() {
        leftShoulder.stopMotor();
    }
    public boolean isLeftShoulder() {
        return leftShoulderLimitSwitch.get();
    }
    public boolean isLeftElbow() {
    return leftElbowLimitSwitch.get();
    }
}
