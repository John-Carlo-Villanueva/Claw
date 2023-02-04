package frc.robot.subsystems;

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
    private final DigitalInput digitalInput;
    private final DoubleSolenoid solenoid;
    //private final CANSparkMax wristMotor;
    private final WPI_TalonSRX talon;
    private SingleChannelEncoder singleChannelEnc;
    //private RelativeEncoder wristEnc;

    // Conductor
    public ClawSubsystem(){
        digitalInput = new DigitalInput(5);
        talon = new WPI_TalonSRX(5);
        solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 1);
        singleChannelEnc = new SingleChannelEncoder(talon, digitalInput);
        //wristMotor = new CANSparkMax(3, MotorType.kBrushless);
        //wristEnc = wristMotor.getEncoder();
    }

    // Encoder methods
    public void resetWristEnc(){
        singleChannelEnc.reset();
    } //resets wristEnc
    public double getWristEnc(){
        return singleChannelEnc.get();
    } // gets wristEnc

    //Turning Methods
    public void stopWrist(){
        talon.stopMotor();
    } //Stops the wrist from turning
    public void turnCW(){
        talon.set(-.5);;
    } // Turns the wrist Clockwise
    public void turnCCW(){
        talon.set(.5);;
    }// Turns the wrist Counter Clockwise

    //Clamping Methods
    public void release(){
        solenoid.set(Value.kReverse);
    } // Opens the claw
    public void clamp(){
        solenoid.set(Value.kForward);
    }// Closes the claw

    // Angle Limiter Methods
    public void rotCWLimit(){
        if(!(getWristEnc() > 130)){
            turnCW();
        }
        stopWrist();
    } // Stops Wrist at a certain encoder when turning Clockwise

    public void rotMid(){
        if (getWristEnc() < 60){
            turnCW();
        } else if (getWristEnc() > 60){
            turnCCW();
        } 
        stopWrist();
    }
    public void rotCCWLimit(){
        if(!(getWristEnc() < 0)){
            turnCCW();
        }
        stopWrist();
    } // Stops Wrist at a certain encoder when turning Counter Clockwise

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Wrist Encoder", getWristEnc());
    } // Prints Encoder in SmartDashBoard
}