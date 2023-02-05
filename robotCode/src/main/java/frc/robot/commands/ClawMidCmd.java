package frc.robot.commands;

import frc.robot.subsystems.ClawSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawMidCmd extends CommandBase{

     // Variable 
    private final ClawSubsystem clawSub;

    // Conductor
    public ClawMidCmd(ClawSubsystem clawSub){
        this.clawSub = clawSub;
    }

    @Override
    public void initialize(){}// First action of ClawMidCmd

    @Override
    public void execute(){
        clawSub.outputMotor(65);
        //clawSub.rotMid();
    }// Main action of ClawMidCmd

    @Override
    public void end(boolean interrupted){
        clawSub.stopWrist();
    }// end action of ClawMidCmd

    @Override
    public boolean isFinished(){
        if(clawSub.getWristEnc() == 65){
            return true;
        }
        return false;
    }// detects if ClawMidCmd is finished
}
