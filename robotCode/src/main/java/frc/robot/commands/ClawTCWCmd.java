package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSubsystem;

public class ClawTCWCmd extends CommandBase{
    
    private final ClawSubsystem clawSub;

    public ClawTCWCmd(ClawSubsystem clawSub){
        this.clawSub = clawSub;
    }

    @Override
    public void initialize(){}

    @Override
    public void execute(){
        clawSub.rotCWLimit();
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
