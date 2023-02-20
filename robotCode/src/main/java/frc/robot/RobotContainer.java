package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.ClawCmd;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.commands.ClawClampCmd;
import frc.robot.commands.ClawMidCmd;
import frc.robot.commands.ClawReleaseCmd;
import frc.robot.commands.ClawTCWCmd;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  private final ClawSubsystem clawSubsystem = new ClawSubsystem();
  //private final Joystick stickA = new Joystick(0);
  private final XboxController xController = new XboxController(1);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(xController, 1).onTrue(new ClawClampCmd(clawSubsystem)); //Button for Clamping arm
    new JoystickButton(xController, 2).onTrue(new ClawReleaseCmd(clawSubsystem)); // Button for Releasing arm
    new JoystickButton(xController, 3).onTrue(new ClawTCWCmd(clawSubsystem)); // Button for turning the wrist Clockwise
    new JoystickButton(xController, 4).onTrue(new ClawMidCmd(clawSubsystem)); // Button for default position of Claw
    new JoystickButton(xController, 5).onTrue(new InstantCommand(()-> clawSubsystem.resetWristEnc())); // Button to reset Enc
    new JoystickButton(xController, 6).toggleOnTrue(new ClawCmd(clawSubsystem,() ->xController.getLeftY())); // Button that toggle manual encoders
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
