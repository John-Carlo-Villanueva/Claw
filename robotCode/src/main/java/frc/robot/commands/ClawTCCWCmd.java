package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ClawSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawTCCWCmd extends CommandBase{
    
    // Variable 
    private final ClawSubsystem clawSub;

    // Conductor
    public ClawTCCWCmd(ClawSubsystem clawSub){
        this.clawSub = clawSub;
        addRequirements(clawSub);
    }

    @Override
    public void initialize(){}// First action of ClawTCCWCmd

    @Override
    public void execute(){
        //clawSub.turnCCW();
        SmartDashboard.putNumber("Wrist Encoder", clawSub.getWristEnc());
        clawSub.rotCCWLimit();
    }// Main action of ClawTCCWCmd

    @Override
    public void end(boolean interrupted){
        clawSub.stopWrist();
    }// end action of ClawTCCWCmd

    @Override
    public boolean isFinished(){
        if (clawSub.getWristEnc() == 100){
            return true;
        }
        return false;
    }// detects if ClawTCCWCmd is finished
}
