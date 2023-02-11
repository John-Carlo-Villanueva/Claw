package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.ClawCmd;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.commands.ClawClampCmd;
import frc.robot.commands.ClawMidCmd;
import frc.robot.commands.ClawReleaseCmd;
import frc.robot.commands.ClawTCCWCmd;
import frc.robot.commands.ClawTCWCmd;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  private final ClawSubsystem clawSubsystem = new ClawSubsystem();
  private final Joystick stickA = new Joystick(0);
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {

    new JoystickButton(stickA, 1).onTrue(new ClawClampCmd(clawSubsystem)); //Button for Clamping arm
    new JoystickButton(stickA, 2).onTrue(new ClawReleaseCmd(clawSubsystem)); // Button for Releasing arm
    new JoystickButton(stickA, 3).onTrue(new ClawTCCWCmd(clawSubsystem)); // Button for turning the wrist Counter Clockwise
    new JoystickButton(stickA, 4).onTrue(new ClawTCWCmd(clawSubsystem)); // Button for turning the wrist Clockwise
    new JoystickButton(stickA, 5).onTrue(new ClawMidCmd(clawSubsystem)); // Button for default position of Claw
    new JoystickButton(stickA, 6).onTrue(new InstantCommand(()-> clawSubsystem.resetWristEnc())); // Button to reset Enc
    new JoystickButton(stickA, 7).toggleOnTrue(new ClawCmd(clawSubsystem,() ->stickA.getZ()));
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
