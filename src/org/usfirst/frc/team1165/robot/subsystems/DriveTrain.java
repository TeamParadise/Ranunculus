package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.commands.DriveWithJoysticks;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem
{
    CANTalon frontLeft = new CANTalon(0);
    CANTalon rearLeft = new CANTalon(1);
    CANTalon frontRight = new CANTalon(2);
    CANTalon rearRight = new CANTalon(3);

    public RobotDrive robotDrive;

    public DriveTrain()
    {
	robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
	 // invert the left motors
	robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
	robotDrive.setInvertedMotor(MotorType.kRearLeft, true); 
    }

    public void initDefaultCommand()
    {
	// Set the default command for a subsystem here.
	setDefaultCommand(new DriveWithJoysticks());
    }
    
    public void driveCartesian(double x, double y, double twist, double gyroAngle)
    {
	robotDrive.mecanumDrive_Cartesian(x, y, twist, gyroAngle);
    }
}
