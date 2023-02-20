package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSubsystem;

public class ClawClampCmd extends CommandBase{
    
    // Variables
    private final ClawSubsystem clawSub;

    // Conductor
    public ClawClampCmd(ClawSubsystem clawSub){
        this.clawSub = clawSub;
        addRequirements(clawSub);
    }

    @Override
    public void initialize(){
        clawSub.clamp();        
    } // First Action of ClawClampCmd

    @Override
    public void execute(){} // Main Action of ClawClampCmd

    @Override
    public void end(boolean interrupted){} // End Action of ClawClampCmd

    @Override
    public boolean isFinished(){
        return true;
    } // detects if ClawClampCmd is Finished
}
