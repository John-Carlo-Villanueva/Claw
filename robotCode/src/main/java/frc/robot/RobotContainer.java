package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.commands.ClawClampCmd;
import frc.robot.commands.ClawReleaseCmd;
import frc.robot.commands.ClawTCCWCmd;
import frc.robot.commands.ClawTCWCmd;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
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

    new JoystickButton(stickA, 2).onTrue(new ClawClampCmd(clawSubsystem));
    new JoystickButton(stickA, 3).onTrue(new ClawReleaseCmd(clawSubsystem));
    new JoystickButton(stickA, 1).onTrue(new ClawTCCWCmd(clawSubsystem));
    new JoystickButton(stickA, 4).onTrue(new ClawTCWCmd(clawSubsystem));
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
