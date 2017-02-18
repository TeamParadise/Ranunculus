package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.DriveWithJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem
{
	CANTalon frontLeft = new CANTalon(0);
	CANTalon rearLeft = new CANTalon(1);
	CANTalon frontRight = new CANTalon(2);
	CANTalon rearRight = new CANTalon(3);
	
	private int rearLeftVal = 0;
	private int rearRightVal = 1;

	Encoder leftBackEncoder;
	Encoder rightBackEncoder;

	PIDController leftBackPID;
	PIDController rightBackPID;

	private static final double Kp = 0.3;
	private static final double Ki = 0.0;
	private static final double Kd = 0.0;
	
	private static final int kMaxNumberOfMotors = 2;
	
	private static final double maxOutput = 0.85;

	public RobotDrive robotDrive;
	
    protected final int m_invertedMotors[] = {1,1};

	public DriveTrain()
	{
		robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		// invert the right motors
		leftBackEncoder = new Encoder(RobotMap.backLeftEncoderPort1, RobotMap.backLeftEncoderPort2, true,
				Encoder.EncodingType.k2X);
		rightBackEncoder = new Encoder(RobotMap.backRightEncoderPort1, RobotMap.backRightEncoderPort2, true,
				Encoder.EncodingType.k2X);

		leftBackEncoder.setDistancePerPulse(2 * 3 * 3.14 / 360 / 12);
		rightBackEncoder.setDistancePerPulse(2 * 3 * 3.14 / 360 / 12);

		leftBackEncoder.setSamplesToAverage(100);
		rightBackEncoder.setSamplesToAverage(100);

		leftBackEncoder.reset();
		rightBackEncoder.reset();

		leftBackPID = new PIDController(Kp, Ki, Kd, leftBackEncoder, rearLeft);
		rightBackPID = new PIDController(Kp, Ki, Kd, rightBackEncoder, rearRight);

		leftBackPID.enable();
		rightBackPID.enable();

		robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
		robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveWithJoystick());
	}

	public void driveCartesian(double x, double y, double twist, double gyroAngle)
	{
		robotDrive.mecanumDrive_Cartesian(x, y, twist, gyroAngle);
		// SmartDashboard.putNumber("Heading", Robot.navXSource.getHeading());

	}
	
	public void driveCartesianEncoders(double x, double y, double twist, double gyroAngle)
	{
		double xIn = x;
        double yIn = y;
        // Negate y for the joystick.
        yIn = -yIn;

        double wheelSpeeds[] = new double[kMaxNumberOfMotors];
        
        wheelSpeeds[rearLeftVal] = -xIn + yIn + twist;
        wheelSpeeds[rearRightVal] = xIn + yIn - twist;
        
        normalize(wheelSpeeds);

        rearLeft.set(wheelSpeeds[rearLeftVal] * m_invertedMotors[rearLeftVal] * maxOutput);
        rearRight.set(wheelSpeeds[rearRightVal] * m_invertedMotors[rearRightVal] * maxOutput);
	}

	protected static void normalize(double wheelSpeeds[])
	{
		double maxMagnitude = Math.abs(wheelSpeeds[0]);
		int i;
		for (i = 1; i < kMaxNumberOfMotors; i++)
		{
			double temp = Math.abs(wheelSpeeds[i]);
			if (maxMagnitude < temp)
				maxMagnitude = temp;
		}
		if (maxMagnitude > 1.0)
		{
			for (i = 0; i < kMaxNumberOfMotors; i++)
			{
				wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
			}
		}
	}
	
	public void reset()
	{
		leftBackEncoder.reset();
		rightBackEncoder.reset();
	}
	
	public double averageEncoderDistance()
	{
		return (leftBackEncoder.getDistance() + rightBackEncoder.getDistance())/2.0;
	}
}