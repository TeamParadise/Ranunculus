package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;
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
	
	public static final double kPShooter = 0.75;
	public static final double kIShooter = 0.01;
	public static final double kDShooter = 0.75;
	
	public static final double kPFeeder = 2;
	public static final double kIFeeder = 0.01;
	public static final double kDFeeder = 3;

	public Shooter()
	{
		shooterWheel = new CANTalon(RobotMap.CANTalonShooter);
		feederWheel = new CANTalon(RobotMap.CANTalonShooterFeeder);

		// Intentionally set different from one another
		shooterWheel.setInverted(true);
		feederWheel.setInverted(true);

		shooterWheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooterWheel.configEncoderCodesPerRev(20);

		feederWheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		feederWheel.configEncoderCodesPerRev(20);

		shooterWheel.setP(kPShooter);
		shooterWheel.setI(kIShooter);
		shooterWheel.setD(kDShooter);
		shooterWheel.setCloseLoopRampRate(0.01);

		feederWheel.setP(kPFeeder);
		feederWheel.setI(kIFeeder);
		feederWheel.setD(kDFeeder);
		feederWheel.setCloseLoopRampRate(0.01);

		shooterWheel.enable();
		feederWheel.enable();
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new ReportShooterValues());
	}
	
	public boolean isRunning()
	{
		SmartDashboard.putBoolean("Shooter Running", Robot.oi.stick.getRawButton(RobotMap.shooterRPMPortNumber));
		return Robot.oi.stick.getRawButton(RobotMap.shooterRPMPortNumber);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public void driveShooterWheelAtRPM(double rpm)
	{
		SmartDashboard.putNumber("RPM in Drive Function", rpm);
		shooterWheel.setControlMode(TalonControlMode.Speed.getValue());
		shooterWheel.set(rpm);
	}

	public void setShooterWheelPower(double speed)
	{
		shooterWheel.changeControlMode(TalonControlMode.PercentVbus);
		shooterWheel.set(speed);
	}

	public void setFeederWheelPower(double speed)
	{
		feederWheel.changeControlMode(TalonControlMode.PercentVbus);
		feederWheel.set(speed);
	}

	public void driveFeederWheelAtRPM(double rpm)
	{
		feederWheel.setControlMode(TalonControlMode.Speed.getValue());
		feederWheel.set(rpm);
	}

	public double getShooterWheelRPM()
	{
		return shooterWheel.getSpeed();
	}

	public double getFeederWheelRPM()
	{
		return feederWheel.getSpeed();
	}
	
	public double getShooterWheelSetpoint()
	{
		return SmartDashboard.getNumber(RobotMap.getShooterWheelString);
	}
	
	public double getFeederWheelSetpoint()
	{
		return SmartDashboard.getNumber(RobotMap.getFeederWheelString);
	}

	public void report()
	{
		SmartDashboard.putNumber(RobotMap.displayShooterWheelString, shooterWheel.getSpeed());
		SmartDashboard.putNumber(RobotMap.displayFeederWheelString, feederWheel.getSpeed());
		SmartDashboard.putNumber(RobotMap.displayShooterWheelValue, shooterWheel.getSpeed());
		SmartDashboard.putNumber(RobotMap.displayFeederWheelValue, feederWheel.getSpeed());
	}
}