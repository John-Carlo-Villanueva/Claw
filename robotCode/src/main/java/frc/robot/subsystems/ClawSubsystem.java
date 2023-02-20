package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import edu.wpi.first.wpilibj.DigitalInput;

public class ClawSubsystem extends SubsystemBase{
    
    // Variables
    private final PIDController wristPID;
    private final DoubleSolenoid solenoid;
    private final CANSparkMax wristMotor;
    private double previousError;
    private RelativeEncoder wristEnc;
    //private final DigitalInput digitalInput;
    //private final WPI_TalonSRX talon;
    //private SingleChannelEncoder singleChannelEnc;

    // Constructor
    public ClawSubsystem(){
        previousError = 0;
        wristPID = new PIDController(0.05, 0.1, 0.001);
        solenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 8);
        wristMotor = new CANSparkMax(3, MotorType.kBrushless);
        wristEnc = wristMotor.getEncoder();
        wristPID.setTolerance(1);
        //digitalInput = new DigitalInput(5);
        //talon = new WPI_TalonSRX(5);
        //singleChannelEnc = new SingleChannelEncoder(talon, digitalInput);
        //singleChannelEnc.reset();
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Wrist Encoder", getWristEnc());
        SmartDashboard.putBoolean("FwdChannel", solenoid.isFwdSolenoidDisabled());
        SmartDashboard.putBoolean("RevChannel", solenoid.isRevSolenoidDisabled());
    } // Prints values in SmartDashBoard

    // Deadzone
    public double deadzone(double speed){
        if (Math.abs(speed) < 0.1){
            return 0;
        }
        return speed;
    }

    // Encoder methods
    public void resetWristEnc(){
        wristEnc.setPosition(0);
        //singleChannelEnc.reset();
    } //resets wristEnc

    public double getWristEnc(){
        return wristEnc.getPosition();
        //return singleChannelEnc.get();
    } // gets wristEnc

    //Turning Methods
    public void stopWrist(){
        wristMotor.set(0);
        //talon.stopMotor();
    } //Stops the wrist from turning

    public void turnCW(){
        wristMotor.set(.5);
        //talon.set(-.5);
    } // Turns the wrist Clockwise

    public void turnCCW(){
        wristMotor.set(-.5);
        //talon.set(.5);
    }// Turns the wrist Counter Clockwise

    public void setWristMotor(double speed){
        wristMotor.set(deadzone(speed));
        //speed = .1;
        //talon.set(deadzone(speed));
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
        if (getWristEnc() > 100){
            turnCCW();
        } else if (getWristEnc() < 100){
            turnCW();
        }
        outputMotor(-1);
    } // Stops Wrist at encoder -1

    public void defaultRot(){
        if (getWristEnc() > 0){
            turnCCW();
        } else if (getWristEnc() < 0){
            turnCW();
        }
        outputMotor(0);
    } // Stops Wrist at encoder 0

    // PID Methods
    public double proportional(double setpoint){
        double speed = wristPID.calculate(getWristEnc(), setpoint);
        if (wristPID.atSetpoint()){
            return 0;
        } else if (speed > 1){
            return 1;
        } else if (speed < -1){
            return -1;
        }
        return speed;
    }// The Proportional of PID

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
    }// The Integral of PID

    public void outputMotor(double setpoint){
        double point = proportional(setpoint);
        SmartDashboard.putNumber("Setpoint", setpoint);
        SmartDashboard.putNumber("Error", wristPID.calculate(getWristEnc(), setpoint));
        wristMotor.set(point);
        integral();
        //talon.set(point);
    }
}