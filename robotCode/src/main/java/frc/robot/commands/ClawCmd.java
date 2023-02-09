package frc.robot.commands;

import frc.robot.subsystems.ClawSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawCmd extends CommandBase{

     // Variable 
    private final ClawSubsystem clawSub;
    private DoubleSupplier claw;

    // Conductor
    public ClawCmd(ClawSubsystem clawSub, DoubleSupplier claw){
        this.clawSub = clawSub;
        this.claw = claw;
    }

    @Override
    public void initialize(){}// First action of ClawCmd

    @Override
    public void execute(){
        double clawSpeed = claw.getAsDouble();
        clawSub.setWristMotor(clawSpeed);
    }// Main action of ClawCmd

    @Override
    public void end(boolean interrupted){
        clawSub.stopWrist();
    }// end action of ClawCmd

    @Override
    public boolean isFinished(){
        return false;
    }// detects if ClawCmd is finished
}
