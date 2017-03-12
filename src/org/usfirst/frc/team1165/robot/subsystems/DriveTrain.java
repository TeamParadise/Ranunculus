package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.DriveWithJoystick;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem
{
	CANTalon frontLeft = new CANTalon(RobotMap.CANTalonDriveFrontLeft);
	CANTalon rearLeft = new CANTalon(RobotMap.CANTalonDriveRearLeft);
	CANTalon frontRight = new CANTalon(RobotMap.CANTalonDriveFrontRight);
	CANTalon rearRight = new CANTalon(RobotMap.CANTalonDriveRearRight);
	

	public RobotDrive robotDrive;
	
	private boolean isRunning = false;
	
    protected final int m_invertedMotors[] = {1,1};

	public DriveTrain()
	{
		robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		
		frontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		
		frontLeft.setEncPosition(0);
		frontLeft.configEncoderCodesPerRev(4085);
		
		frontRight.setEncPosition(0);
		frontRight.configEncoderCodesPerRev(4085);
		// invert the right motors
		robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
		robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveWithJoystick());
	}

	public boolean atDistance(double distance)
	{
		return Math.abs(averageEncoderDistance()) > Math.abs(distance) && distance != -1;
	}

	public void driveCartesian(double x, double y, double twist, double gyroAngle)
	{
		isRunning = Math.abs(x) >= 0.2 || Math.abs(y) >= 0.2 || Math.abs(twist) >= 0.2;
		robotDrive.mecanumDrive_Cartesian(x, y, twist, gyroAngle);
		// SmartDashboard.putNumber("Heading", Robot.navXSource.getHeading());
	}
	
	public boolean isRunning()
	{
		return isRunning;
	}
	
	public void reset()
	{
		frontRight.setEncPosition(0);
		frontLeft.setEncPosition(0);
	}
	
	public void stop()
	{
		driveCartesian(0, 0, 0, 0);
	}
	
	public double averageEncoderDistance()
	{
		return (leftEncoderDistance() + rightEncoderDistance())/2.0;
	}
	
	public double ticksToDistance(double ticks)
	{
		//convert To Inches
		return ticks*Math.PI*6.0/4085;
	}
	
	public double leftEncoderDistance()
	{
		return  -ticksToDistance(frontLeft.getEncPosition());
	}
	
	public double rightEncoderDistance()
	{
		return  ticksToDistance(frontRight.getEncPosition());
	}
	
	public void report()
	{
		SmartDashboard.putNumber("Left Encoder", leftEncoderDistance());
		SmartDashboard.putNumber("Right Encoder", rightEncoderDistance());
		SmartDashboard.putNumber("Avg Distance",  averageEncoderDistance());
	}
}