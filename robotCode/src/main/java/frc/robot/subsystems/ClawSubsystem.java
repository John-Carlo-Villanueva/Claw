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
    }
    public double getWristEnc(){
        return singleChannelEnc.get();
    }

    //Turning Methods
    public void stopWrist(){
        talon.stopMotor();
    }
    public void turnCW(){
        talon.set(.5);;
    }
    public void turnCCW(){
        talon.set(-.5);;
    }

    //Clamping Methods
    public void release(){
        solenoid.set(Value.kReverse);
    }
    public void clamp(){
        solenoid.set(Value.kForward);
    }

    // Angle Limiter Methods
    public void rotCWLimit(){
        if(getWristEnc() > 50){
            stopWrist();
        }
        turnCW();
    } 
    public void rotCCWLimit(){
        if(getWristEnc() < -50){
            stopWrist();
        }
        turnCCW();
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Wrist Encoder", getWristEnc());
    }
}