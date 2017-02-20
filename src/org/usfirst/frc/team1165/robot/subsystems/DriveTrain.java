package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.DriveWithJoystick;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem
{
	CANTalon frontLeft = new CANTalon(RobotMap.CANTalonDriveFrontLeft);
	CANTalon rearLeft = new CANTalon(RobotMap.CANTalonDriveRearLeft);
	CANTalon frontRight = new CANTalon(RobotMap.CANTalonDriveFrontRight);
	CANTalon rearRight = new CANTalon(RobotMap.CANTalonDriveRearRight);
	
	private int rearLeftVal = 0;
	private int rearRightVal = 1;

/*	Encoder leftBackEncoder;
	Encoder rightBackEncoder;*/

	/*PIDController leftBackPID;
	PIDController rightBackPID;*/

	private static final double Kp = 0.3;
	private static final double Ki = 0.0;
	private static final double Kd = 0.0;
	
	private static final int kMaxNumberOfMotors = 2;
	
	private static final double maxOutput = 0.85;

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
		return averageEncoderDistance() > distance && distance > -1;
	}
	public double distancePower(double distance, double forwardSpeed)
	{
		if(Math.abs(averageEncoderDistance() - distance) < 30 && distance > -1)
		{
			if( Math.abs(averageEncoderDistance() - distance) / 30.0 * forwardSpeed < 0.1)
				return 0.15;
			else
				return Math.abs(averageEncoderDistance() - distance) / 30.0;
		}
		return forwardSpeed;
	}

	public void driveCartesian(double x, double y, double twist, double gyroAngle)
	{
		isRunning = x >= 0.2 && y >= 0.2 && twist >= 0.2 ? true : false;
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
		return  Math.abs(ticksToDistance(frontLeft.getEncPosition()));
	}
	
	public double rightEncoderDistance()
	{
		return  Math.abs(ticksToDistance(frontRight.getEncPosition()));
	}
	
	public void report()
	{
		SmartDashboard.putNumber("Left Encoder", ticksToDistance(frontLeft.getEncPosition()));
		SmartDashboard.putNumber("Right Encoder", ticksToDistance(frontRight.getEncPosition()));
	}
}