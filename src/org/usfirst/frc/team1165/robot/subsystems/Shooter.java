package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.ReportShooterValues;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem
{
    public CANTalon shooterWheel;
    public CANTalon feederWheel;

    public static final double kP = 0.75;
    public static final double kI = 0.01;
    public static final double kD = 0.75;

    public Shooter()
    {
	shooterWheel = new CANTalon(4);
	feederWheel = new CANTalon(5);

	shooterWheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
	shooterWheel.configEncoderCodesPerRev(20);

	feederWheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
	feederWheel.configEncoderCodesPerRev(20);

	shooterWheel.setP(kP);
	shooterWheel.setI(kI);
	shooterWheel.setD(kD);
	shooterWheel.setCloseLoopRampRate(0.01);

	feederWheel.setP(kP);
	feederWheel.setI(kI);
	feederWheel.setD(kD);
	feederWheel.setCloseLoopRampRate(0.01);
    }

    public void initDefaultCommand()
    {
	// Set the default command for a subsystem here.
	setDefaultCommand(new ReportShooterValues());
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void driveShooterWheelAtRPM(double rpm)
    {
	shooterWheel.setControlMode(TalonControlMode.Speed.getValue());
	shooterWheel.set(rpm);
    }

    public void driveFeederWheelAtRPM(double rpm)
    {
	feederWheel.setControlMode(TalonControlMode.Speed.getValue());
	feederWheel.set(rpm);
    }

    public double getShooterWheelRPM()
    {
	return SmartDashboard.getNumber(RobotMap.getShooterWheelString);
    }

    public double getFeederWheelRPM()
    {
	return SmartDashboard.getNumber(RobotMap.getFeederWheelString);
    }

    public void report()
    {
	SmartDashboard.putNumber(RobotMap.displayShooterWheelString, shooterWheel.getSpeed());
	SmartDashboard.putNumber(RobotMap.displayFeederWheelString, feederWheel.getSpeed());
    }
}