// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.Custom.*;
import static frc.robot.Constants.LEFT_SHOOT;
import static frc.robot.Constants.RIGHT_SHOOT;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TestShooter extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  TalonFX shooterMotor1;
  TalonFX shooterMotor2;

  public TestShooter() {

   

     
    this.shooterMotor1 = new TalonFX(LEFT_SHOOT);
    this.shooterMotor1.setInverted(true);
    this.shooterMotor2 = new TalonFX(RIGHT_SHOOT);

    shooterMotor1.configFactoryDefault();
    shooterMotor2.configFactoryDefault();

    /* Config sensor used for Primary PID [Velocity] */
    shooterMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constant.kPIDLoopIdx,
        Constant.kTimeoutMs);
    shooterMotor2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constant.kPIDLoopIdx,
        Constant.kTimeoutMs);
    /**
     * Phase sensor accordingly. Positive Sensor Reading should match Green
     * (blinking) Leds on Talon
     */
    shooterMotor1.setSensorPhase(true);
    shooterMotor2.setSensorPhase(true);
    /* Config the peak and nominal outputs */
    shooterMotor1.configNominalOutputForward(0, Constant.kTimeoutMs);
    shooterMotor1.configNominalOutputReverse(0, Constant.kTimeoutMs);
    shooterMotor1.configPeakOutputForward(1, Constant.kTimeoutMs);
    shooterMotor1.configPeakOutputReverse(-1, Constant.kTimeoutMs);

    shooterMotor2.configNominalOutputForward(0, Constant.kTimeoutMs);
    shooterMotor2.configNominalOutputReverse(0, Constant.kTimeoutMs);
    shooterMotor2.configPeakOutputForward(1, Constant.kTimeoutMs);
    shooterMotor2.configPeakOutputReverse(-1, Constant.kTimeoutMs);

    /* Config the Velocity closed loop gains in slot0 */
    shooterMotor1.config_kF(Constant.kPIDLoopIdx, Constant.kGains_Velocit.kF, Constant.kTimeoutMs);
    shooterMotor1.config_kP(Constant.kPIDLoopIdx, Constant.kGains_Velocit.kP, Constant.kTimeoutMs);
    shooterMotor1.config_kI(Constant.kPIDLoopIdx, Constant.kGains_Velocit.kI, Constant.kTimeoutMs);
    shooterMotor1.config_kD(Constant.kPIDLoopIdx, Constant.kGains_Velocit.kD, Constant.kTimeoutMs);

    shooterMotor2.config_kF(Constant.kPIDLoopIdx, Constant.kGains_Velocit.kF, Constant.kTimeoutMs);
    shooterMotor2.config_kP(Constant.kPIDLoopIdx, Constant.kGains_Velocit.kP, Constant.kTimeoutMs);
    shooterMotor2.config_kI(Constant.kPIDLoopIdx, Constant.kGains_Velocit.kI, Constant.kTimeoutMs);
    shooterMotor2.config_kD(Constant.kPIDLoopIdx, Constant.kGains_Velocit.kD, Constant.kTimeoutMs);

  }

  public void setShooterVelocity() {

    ShuffleboardTab tab = Shuffleboard.getTab("Shoot");
    NetworkTableEntry ShootSpeed =
       tab.add("Shoot Speed", 6380)
          .getEntry();

    double velocity = ShootSpeed.getDouble(6380);
    shooterMotor1.set(ControlMode.Velocity, velocity);
    shooterMotor2.set(ControlMode.Velocity, velocity);
  }

  public void shooterStop() {
    shooterMotor1.set(ControlMode.Velocity, 0);
    shooterMotor2.set(ControlMode.Velocity, 0);
  }

}
