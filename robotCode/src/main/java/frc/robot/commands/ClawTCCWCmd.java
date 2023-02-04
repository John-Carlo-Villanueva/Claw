package frc.robot.commands;

import frc.robot.subsystems.ClawSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawTCCWCmd extends CommandBase{
    
    // Variable 
    private final ClawSubsystem clawSub;

    // Conductor
    public ClawTCCWCmd(ClawSubsystem clawSub){
        this.clawSub = clawSub;
    }

    @Override
    public void initialize(){}// First action of ClawTCCWCmd

    @Override
    public void execute(){
        clawSub.outputMotor(130);
        //clawSub.turnCCW();
        //clawSub.rotCCWLimit();
    }// Main action of ClawTCCWCmd

    @Override
    public void end(boolean interrupted){
        
        clawSub.stopWrist();
    }// end action of ClawTCCWCmd

    @Override
    public boolean isFinished(){
        return false;
    }// detects if ClawTCCWCmd is finished
}
