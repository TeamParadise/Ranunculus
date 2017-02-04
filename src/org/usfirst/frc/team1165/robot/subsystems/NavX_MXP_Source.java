package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.commands.ReportNavXValues;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavX_MXP_Source extends Subsystem
{

	public AHRS ahrs;

	double max_collision = 0;

	double last_world_linear_accel_x;
	double last_world_linear_accel_y;
	double curr_world_linear_accel_x;
	double currentJerkX;
	double curr_world_linear_accel_y;
	double currentJerkY;
	int collisionDetected = 0;

	final static double kCollisionThreshold_DeltaG = 1.8f;

	public NavX_MXP_Source()
	{
		try
		{

			/*
			 * * Communicate w/navX-MXP via the MXP SPI Bus.
			 * 
			 * Alternatively: I2C.Port.kMXP, SerialPort.Port.kMXP or
			 * SerialPort.Port.kUSB
			 * 
			 * 
			 * See
			 * http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/
			 * for details.
			 */

			ahrs = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex)
		{
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
		}
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new ReportNavXValues());
	}

	public void reset()
	{
		ahrs.reset();
	}

	public double getHeading()
	{
		return ahrs.getAngle() % 360;
	}

	// Collision Detection Feature (Very Experimental)

	public void updateAccel()
	{
		curr_world_linear_accel_x = ahrs.getWorldLinearAccelX();
		currentJerkX = curr_world_linear_accel_x - last_world_linear_accel_x;
		last_world_linear_accel_x = curr_world_linear_accel_x;
		double curr_world_linear_accel_y = ahrs.getWorldLinearAccelY();
		double currentJerkY = curr_world_linear_accel_y - last_world_linear_accel_y;
		last_world_linear_accel_y = curr_world_linear_accel_y;

		SmartDashboard.putNumber("Curr Accel Y", curr_world_linear_accel_y);
		SmartDashboard.putNumber("Last Accel Y", last_world_linear_accel_y);

		if (Math.abs(currentJerkY) > max_collision)
		{
			max_collision = Math.abs(currentJerkY);
		}

		SmartDashboard.putNumber("Max", max_collision);
	}

	public boolean detectCollision()
	{
		if (Math.abs(currentJerkY) > kCollisionThreshold_DeltaG)
		{
			collisionDetected = 1;
			SmartDashboard.putString("Crash", "Bang");
			return true;
		}
		SmartDashboard.putNumber("CollisionDetected", collisionDetected);
		return false;
	}

	public double pidInput()
	{
		return ahrs.pidGet();
	}

	public void report()
	{
		SmartDashboard.putNumber("Gyro Angle", getHeading());
		SmartDashboard.putNumber("Acceleration Y", ahrs.getRawAccelX());
		SmartDashboard.putNumber("Raw Mag z", ahrs.getRawMagZ());
		SmartDashboard.putNumber("PID Get", ahrs.pidGet());
	}
}