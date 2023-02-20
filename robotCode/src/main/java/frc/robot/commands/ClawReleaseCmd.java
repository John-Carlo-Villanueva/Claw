package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSubsystem;

public class ClawReleaseCmd extends CommandBase{
    
    // Variables
    private final ClawSubsystem clawSub;

    // Conductor
    public ClawReleaseCmd(ClawSubsystem clawSub){
        this.clawSub = clawSub;
        addRequirements(clawSub);
    }

    @Override
    public void initialize(){
        clawSub.release();
    } // First action of ClawReleaseCmd 

    @Override
    public void execute(){} // Main action of ClawReleaseCmd

    @Override
    public void end(boolean interrupted){} // End action of ClawReleaseCmd

    @Override
    public boolean isFinished(){
        return true;
    } // detects if ClawRelease is finished
}
