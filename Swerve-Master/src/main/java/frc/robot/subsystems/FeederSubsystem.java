// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import static frc.robot.Constants.FEEDER;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FeederSubsystem extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  TalonFX FeederMotor;


  public FeederSubsystem() {

   

     
    this.FeederMotor = new TalonFX(FEEDER);
    this.FeederMotor.configFactoryDefault();
  }

  public void Feed() {
    this.FeederMotor.set(ControlMode.PercentOutput, .2);
  }

  public void Feedstop() {
      this.FeederMotor.set(ControlMode.PercentOutput, 0);
  }
}
