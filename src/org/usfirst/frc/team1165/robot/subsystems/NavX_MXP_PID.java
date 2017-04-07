package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.commands.ReportNavXValues;

//import org.usfirst.frc.team1165.robot.commands.ReportNavXValues;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavX_MXP_PID implements PIDOutput

{
	// proportional speed constant
	private static final double kP = 2.75;

	// integral speed constant
	private static final double kI = 0.00;

	// derivative speed constant
	private static final double kD = 4.00;

	public double rotateToAngleRate = 0;

	/*
	 * * This tuning parameter indicates how close to "on target" the PID
	 * Controller will attempt to get.
	 */

	public static final double kToleranceDegrees = 2.0f;

	public PIDController navXController;

	// Initialize your subsystem here
	public NavX_MXP_PID()
	{
		navXController = new PIDController(kP, kI, kD, Robot.navXSource.ahrs, this);
		navXController.setInputRange(-180.0f, 180.0f);
		//Reversed to account for direction of turn
		//navXController.setOutputRange(-0.85, 0.85);
		navXController.setOutputRange(-0.70, 0.70);
		navXController.setAbsoluteTolerance(kToleranceDegrees);
		navXController.setContinuous(true);
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
	}
	
	public double getTargetDifference()
	{
		double difference = Robot.navXSource.ahrs.pidGet() - navXController.getSetpoint();
		SmartDashboard.putNumber("NavX Target Difference", difference);
		return difference;

	}

	@Override
	public void pidWrite(double output)
	{
		// TODO Auto-generated method stub
		if(Math.abs(getTargetDifference()) < 20)
			output /= 2;
		//If robot turns wrong way and never finishes, invert output(You're welcome, future programmers)
		SmartDashboard.putNumber("NavX PID Output", -output);
		rotateToAngleRate = -output;
	}
}