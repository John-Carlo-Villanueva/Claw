package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSubsystem;

public class ClawTCWCmd extends CommandBase{
    
    // Variable
    private final ClawSubsystem clawSub;

    //Conductor
    public ClawTCWCmd(ClawSubsystem clawSub){
        this.clawSub = clawSub;
        addRequirements(clawSub);
    }

    @Override
    public void initialize(){}// first action of ClawTCWCmd

    @Override
    public void execute(){
        //clawSub.turnCW();
        clawSub.rotCWLimit();
    } // main action of ClawTCWCmd

    @Override
    public void end(boolean interrupted){
        clawSub.stopWrist();
    }// end action of ClawTCWCmd

    @Override
    public boolean isFinished(){
        if(clawSub.getWristEnc() == 0){
            return true;
        }
        return false;
    }// detects if ClawTCWCmd is finished
}
