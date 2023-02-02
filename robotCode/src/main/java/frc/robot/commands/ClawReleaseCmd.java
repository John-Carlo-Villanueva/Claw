package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSubsystem;

public class ClawReleaseCmd extends CommandBase{
    
    private final ClawSubsystem clawSub;

    public ClawReleaseCmd(ClawSubsystem clawSub){
        this.clawSub = clawSub;
    }

    @Override
    public void initialize(){}

    @Override
    public void execute(){
        clawSub.release();
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}
