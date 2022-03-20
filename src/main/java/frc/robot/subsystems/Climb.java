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

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;


/**
 *
 */
public class Climb extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
 
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private AnalogInput leftHandLimitSwitch;
private AnalogInput leftElbowLimitSwitch;
private AnalogInput leftShoulderLimitSwitch;
private WPI_TalonSRX leftShoulder;
private WPI_TalonFX leftElbow;
private WPI_TalonSRX rightShoulder;
private WPI_TalonFX rightElbow;
private DoubleSolenoid leftHardStop;

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
private DutyCycleEncoder rightShoulderEncoder;
    /**
    *
    */
    public Climb() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
leftHandLimitSwitch = new AnalogInput(2);
 addChild("LeftHandLimitSwitch", leftHandLimitSwitch);
 

leftElbowLimitSwitch = new AnalogInput(1);
 addChild("LeftElbowLimitSwitch", leftElbowLimitSwitch);
 

leftShoulderLimitSwitch = new AnalogInput(0);
 addChild("LeftShoulderLimitSwitch", leftShoulderLimitSwitch);
 

leftShoulder = new WPI_TalonSRX(7);
 
 

leftElbow = new WPI_TalonFX(8);
 
 

rightShoulder = new WPI_TalonSRX(12);
 
 

rightElbow = new WPI_TalonFX(10);
 
 

leftHardStop = new DoubleSolenoid(10, PneumaticsModuleType.CTREPCM, 5, 4);
 addChild("leftHardStop", leftHardStop);
 


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    leftElbowEncoder = new DutyCycleEncoder(6);
    addChild("leftElbowEncoder", leftElbowEncoder);
    leftShoulderEncoder = new DutyCycleEncoder(8);
    addChild("leftShoulderEncoder", leftShoulderEncoder);
    }
    public boolean climbMode = false;
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        //SmartDashboard.putBoolean("ClimbHardStop", leftHardStop.get());
        SmartDashboard.putBoolean("Left Hand", isLeftHand());
        SmartDashboard.putBoolean("Left Elbow", isLeftElbow());
        SmartDashboard.putBoolean("Left Shoulder", isLeftShoulder());
        SmartDashboard.putNumber("Left Shoulder Encoder", leftShoulderEncoder.get());
        SmartDashboard.putNumber("Left Elbow Encoder", leftElbowEncoder.get());
        SmartDashboard.putBoolean("Climb Mode", climbMode);
        SmartDashboard.putNumber("Pigeon Pitch", RobotContainer.getInstance().m_drive.getPigeonPitch());


    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }
    public void shoulderMove(){
       
            startLeftShoulder(.25);
      

    }
    public void elbowMove(){
        
            startLeftElbow(.25);
      
       
    }
    public boolean shoulderEncodeTarget(double Encoders, boolean direction){
        if (direction == true) {
            if (Encoders >= leftShoulderEncoder.get()){
                return true;
            }else{
                return false;
            }
        } else {
            if (Encoders <= leftShoulderEncoder.get()){
                return true;
            }else{
                return false;
            }
        }
    }
    public boolean elbowEncodeTarget(double Encoders, boolean direction){
        if (direction == true) {
            if (Encoders >= leftElbowEncoder.get()){
                return true;
            }else{
                return false;
            }
        } else {
            if (Encoders <= leftElbowEncoder.get()){
                return true;
            }else{
                return false;
            }
        }
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public boolean isLeftHand() {
        if (leftHandLimitSwitch.getValue() > 10) {
            return true;
        } else {
            return false;
        }
        
// We're no strangers to love
//You know the rules and so do I
//A full commitment's what I'm thinking of
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
        System.out.println(" leftArmElbowMath ElbowAngle=" + ElbowAngle);
        ElbowTarget = Math.acos(((Constants.bicepLenght*Constants.bicepLenght)+(Constants.forearmLength*Constants.forearmLength)-(BarY*BarY)-(BarX*BarX))/(2*Constants.forearmLength*Constants.bicepLenght));
        System.out.println("leftArmElbowMath ElbowTarget=" + ElbowTarget);
        ShoulderAngle = leftShoulderEncoder.get()*Constants.encoderToDegree;
        System.out.println("leftArmElbowMath ShoulderAngle=" + ShoulderAngle);
        ShoulderTarget = 180-(Math.asin((Math.sin(ElbowTarget)*Constants.forearmLength)/(Math.pow(BarX*BarX*BarY*BarY, .5))))-Math.atan(BarY/BarX);
        System.out.println("leftArmElbowMath ShoulderTarget=" + ShoulderTarget);
        if (ElbowAngle >= ElbowTarget) {
            System.out.println("leftArmElbowMath ElbowAngle > ElbowTarget");
            return true;
        }
        else {
            return false;
        }
    }
    public double leftElbowTarget(double BarX, double BarY) {
        ElbowAngle = leftElbowEncoder.get()*Constants.encoderToDegree;
        System.out.println("ElbowAngle=" + ElbowAngle);
        ElbowTarget = Math.acos(((Constants.bicepLenght*Constants.bicepLenght)+(Constants.forearmLength*Constants.forearmLength)-(BarY*BarY)-(BarX*BarX))/(2*Constants.forearmLength*Constants.bicepLenght));
        System.out.println("ElbowTarget=" + ElbowTarget);
        return ElbowTarget;
    }
    public boolean leftArmShoulderMath(double BarX, double BarY) {
        ElbowAngle = leftElbowEncoder.get()*Constants.encoderToDegree;
        System.out.println("leftArmShoulderMath ElbowAngle=" + ElbowAngle);
        ElbowTarget = Math.acos(((Constants.bicepLenght*Constants.bicepLenght)+
                     (Constants.forearmLength*Constants.forearmLength)-
                     (BarY*BarY)-(BarX*BarX)) /
                     (2*Constants.forearmLength*Constants.bicepLenght));
        System.out.println("leftArmShoulderMath ElbowTarget=" + ElbowTarget);
        ShoulderAngle = leftShoulderEncoder.get()*Constants.encoderToDegree;
        System.out.println("leftArmShoulderMath ShoulderAngle=" + ShoulderAngle);
        ShoulderTarget = 180-(Math.asin((Math.sin(ElbowTarget)*Constants.forearmLength)/(Math.pow(BarX*BarX*BarY*BarY, .5))))-Math.atan(BarY/BarX);
        System.out.println("leftArmShoulderMath ShoulderTarget=" + ShoulderTarget);
        if (ShoulderAngle >= ShoulderTarget) {
            System.out.println("leftArmShoulderMath ShoulderTarget > ShoulderTarget");
            return true;
        } else {
             return false;
         }

    }
    public boolean RightArmShoulderMath(double BarX, double BarY) {
        ShoulderAngle = rightShoulderEncoder.get()*Constants.encoderToDegree;
        ShoulderTarget = 180-(Math.asin((Math.sin(ElbowTarget)*Constants.forearmLength)/(Math.pow(BarX*BarX*BarY*BarY, .5))))-Math.atan(BarY/BarX);
        return ShoulderTarget == ShoulderAngle;
        // if (ShoulderTarget == ShoulderAngle) {
        //     return true;
        // }
        // else {
        //     return false;
        // }
    }
    public double leftShoulderTarget(double BarX, double BarY) {
        ShoulderAngle = rightShoulderEncoder.get()*Constants.encoderToDegree;
        return 180-(Math.asin((Math.sin(ElbowTarget)*Constants.forearmLength)/(Math.pow(BarX*BarX*BarY*BarY, .5))))-Math.atan(BarY/BarX);
    }
    public double shoulderEncoder(){
        return leftShoulderEncoder.get();
    }
    public double elbowEncoder(){
        return leftElbowEncoder.get();
    }
    public void startLeftElbow(double speed) {
        leftElbow.set(speed);
    }
    public void startLeftShoulder(double speed) {
        leftShoulder.set(speed);
    }
    public void startRightElbow(double speed) {
        rightElbow.set(speed);
    }
    public void startRightShoulder(double speed){
        rightShoulder.set(speed);
    }
    public void stopLeftElbow() {
        leftElbow.stopMotor();
    }
    public void stopLeftShoulder() {
        leftShoulder.stopMotor();
    }
    public void stopRightElbow(){
        rightElbow.stopMotor();
    }
    public void stopRightShoulder(){
        rightShoulder.stopMotor();
    }
    public boolean isLeftShoulder() {
        if (leftShoulderLimitSwitch.getValue() > 10) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isLeftElbow() {
        if (leftElbowLimitSwitch.getValue() > 10) {
            return true;
        } else {
            return false;
        }
    }

    public void zeroLeftElbowEncoder() {
        leftElbowEncoder.reset();
    }
    public void zeroLeftShoulderEncoder() {
        leftShoulderEncoder.reset();
    }
    public void zeroRightElbowEncoder() {
        rightElbowEncoder.reset();
    }
    public void zeroRightShoulderEncoder() {
        rightShoulderEncoder.reset();
    }    
    public void activateHardStop() {
        leftHardStop.set(Value.kForward);
    }
    public void retractHardStop() {
        leftHardStop.set(Value.kReverse);
    }

    public void toggleStop() {
        leftHardStop.toggle();
    }
    public void manualClimber(Joystick joystickP1){
        
        if (climbMode){
        // If moving toward limit switch & it is tripped stop,
        // but allow to go other direction
        double x = joystickP1.getX();
        // if ((x > 0 && isLeftShoulder())||x<.02) { x = 0; }
        if ((x > 0 && isLeftShoulder())) { x = 0; }
        // If moving toward limit switch & it is tripped stop,
        // but allow to go other direction
        double y = -joystickP1.getY();
        if (y > 0 && isLeftElbow()) { y = 0; }
        //stops elbow if it opens too far
        if (((y < 0) && (elbowEncoder()) <= -0.411)){ y=0;} 
        // Adjust speed allow drivers to use 
        // if (x > .9) {x=.9;}
        // if(x< -.9) {x=-.9;}
        // if(y> .4) {y=.4;}
        // if(y< -.4) {y=-.4;}
        leftShoulder.set(x);
        leftElbow.set(y);
        }
    }
    public void enableClimbMode(){

    }
    public void balanceWithPigeon(){
        startLeftShoulder(RobotContainer.getInstance().m_drive.getPigeonPitch() / 90);
    }
}
    
