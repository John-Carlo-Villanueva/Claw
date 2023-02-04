package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    public void initialize(){}// First action of ClawTCCWCmd

    @Override
    public void execute(){
        SmartDashboard.putNumber("Wrist Encoder", clawSub.getWristEnc());
        clawSub.rotMid();
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
