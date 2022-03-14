// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxLimitSwitch.Type;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RightArmConstants;

public class RightArm extends SubsystemBase {

  private CANSparkMax m_rightArmNEO;
  private SparkMaxPIDController m_backPIDController;
  private SparkMaxLimitSwitch m_reverseLimitSwitch;
  private RelativeEncoder m_rightEncoder;

  /** Creates a new BackArm. */
  public RightArm() {
    m_rightArmNEO = new CANSparkMax(RightArmConstants.rightSparkMaxID,MotorType.kBrushless);

    m_rightEncoder = m_rightArmNEO.getEncoder();

    m_reverseLimitSwitch = m_rightArmNEO.getReverseLimitSwitch(Type.kNormallyOpen);
    m_reverseLimitSwitch.enableLimitSwitch(true);

    m_backPIDController = m_rightArmNEO.getPIDController(); 

    m_backPIDController.setP(RightArmConstants.rightArmP);
    m_backPIDController.setI(RightArmConstants.rightArmI);
    m_backPIDController.setD(RightArmConstants.rightArmD);
    m_backPIDController.setIZone(RightArmConstants.rightArmIZone);
    m_backPIDController.setFF(RightArmConstants.rightArmFF);
    m_backPIDController.setOutputRange(RightArmConstants.rightArmMin,RightArmConstants.rightArmMax);

    m_rightArmNEO.setIdleMode(IdleMode.kBrake);

    m_rightArmNEO.burnFlash();
  }

  public void ascendBackArm(){
    m_rightArmNEO.set(RightArmConstants.ascensionSpeed);
    m_backPIDController.setReference(RightArmConstants.upPIDReference, ControlType.kPosition);
  }

  public void descendBackArm(){
    m_rightArmNEO.set(RightArmConstants.descensionSpeed);
    m_backPIDController.setReference(RightArmConstants.downPIDReference, ControlType.kPosition);
  }

  public void stopBackArm(){
    m_rightArmNEO.set(RightArmConstants.stopSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
