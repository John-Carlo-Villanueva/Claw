package frc.robot.commands;

import frc.robot.subsystems.ClawSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawTCCWCmd extends CommandBase{
    
    private final ClawSubsystem clawSub;

    public ClawTCCWCmd(ClawSubsystem clawSub){
        this.clawSub = clawSub;
    }

    @Override
    public void initialize(){}

    @Override
    public void execute(){
        clawSub.rotCCWLimit();
    }

    @Override
    public void end(boolean interrupted){
        clawSub.stopWrist();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
