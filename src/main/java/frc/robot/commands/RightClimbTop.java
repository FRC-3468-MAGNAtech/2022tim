// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RightArm;

public class RightClimbTop extends CommandBase {
  private RightArm m_subsystem;
  /** Creates a new BackClimbAscention. */
  public RightClimbTop(RightArm subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_subsystem = subsystem;

    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.topRightArmPID();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.stopRightArm();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_subsystem.isAtSetPoint();
  }
}