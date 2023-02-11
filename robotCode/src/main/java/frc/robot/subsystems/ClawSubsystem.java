package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClawSubsystem extends SubsystemBase{
    
    // Variables
    private final PIDController wristPID;
    private final DigitalInput digitalInput;
    private final DoubleSolenoid solenoid;
    //private final CANSparkMax wristMotor;
    private final WPI_TalonSRX talon;
    private SingleChannelEncoder singleChannelEnc;
    private double previousError;
    //private RelativeEncoder wristEnc;

    // Conductor
    public ClawSubsystem(){
        previousError = 0;
        wristPID = new PIDController(0.05, 0.05, 0.1);
        digitalInput = new DigitalInput(5);
        talon = new WPI_TalonSRX(5);
        solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 1);
        singleChannelEnc = new SingleChannelEncoder(talon, digitalInput);
        wristPID.setTolerance(.1);
        //wristMotor = new CANSparkMax(3, MotorType.kBrushless);
        //wristEnc = wristMotor.getEncoder();
    }

    // Encoder methods
    public void resetWristEnc(){
        singleChannelEnc.reset();
    } //resets wristEnc
    public double getWristEnc(){
        return singleChannelEnc.get();
        //return wristEnc.get();
    } // gets wristEnc

    //Turning Methods
    public void stopWrist(){
        if (digitalInput.get()){
            talon.stopMotor();
            outputMotor(0);
            resetWristEnc();
        }
        talon.stopMotor();
    } //Stops the wrist from turning
    public void turnCW(){
        talon.set(-.5);
    } // Turns the wrist Clockwise
    public void turnCCW(){
        talon.set(.5);
    }// Turns the wrist Counter Clockwise
    public void setWristMotor(double speed){
        //speed = .1;
        talon.set(speed);
        wristPID.getSetpoint();
        SmartDashboard.putNumber("Setpoint", wristPID.getSetpoint());
    }

    //Clamping Methods
    public void release(){
        solenoid.set(Value.kReverse);
    } // Opens the claw
    public void clamp(){
        solenoid.set(Value.kForward);
    }// Closes the claw

    // Angle Limiter Methods
    public void rotCWLimit(){
        outputMotor(0);
        /*if(getWristEnc() > 0){
            turnCW();
        }else if (getWristEnc() < 0){
            turnCCW();
        } else {
            stopWrist();
        }*/
    } // Stops Wrist at encoder 0
    public void rotMidLimit(){
        outputMotor(65);
        /*if (getWristEnc() > 65){
            turnCW();
        } else if (getWristEnc() < 65){
            turnCCW();
        }else {
            stopWrist();
        }*/
    } // Stops Wrist at encoder 65
    public void rotCCWLimit(){
        outputMotor(130);
        /*if(getWristEnc() < 130){
            turnCCW();
        }else if (getWristEnc() > 130){
            turnCW();
        }else {
            stopWrist();
        }*/
    } // Stops Wrist at encoder 130

    // PID Methods
    public double proportionality(double setpoint){
        double speed = wristPID.calculate(getWristEnc(), setpoint);
        if (wristPID.atSetpoint()){
            return 0;
        } else if (speed > 1){
            return 1;
        } else if (speed < -1){
            return -1;
        } else {
            return speed;
        }
    }
    public void integral(){
        double errorSum = wristPID.getPositionError();
        if (errorSum > 0 && previousError < 0){
            wristPID.reset();
        } else if(errorSum < 0 && previousError > 0){
            wristPID.reset();
        }
        previousError = wristPID.getPositionError();
        SmartDashboard.putNumber("Current Error", errorSum);
        SmartDashboard.putNumber("Previous Error", previousError);
    }

    public void outputMotor(double setpoint){
        double point = proportionality(setpoint);
        SmartDashboard.putNumber("Setpoint", setpoint);
        SmartDashboard.putNumber("Elev Enc", getWristEnc());
        SmartDashboard.putNumber("Error", wristPID.calculate(setpoint));
        integral();
        talon.set(point);
        //wristMotor.set(point);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Wrist Encoder", getWristEnc());
        SmartDashboard.putBoolean("Digital Input", digitalInput.get());
    } // Prints Encoder in SmartDashBoard
}