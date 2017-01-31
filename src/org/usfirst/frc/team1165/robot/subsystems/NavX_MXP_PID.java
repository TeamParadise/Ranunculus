package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.commands.ReportNavXValues;

//import org.usfirst.frc.team1165.robot.commands.ReportNavXValues;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavX_MXP_PID extends PIDSubsystem
{
    AHRS ahrs;
    // proportional speed constant
    private static final double kP = 7.0;

    // integral speed constant
    private static final double kI = 0.018;

    // derivative speed constant
    private static final double kD = 1.5;
    
    public double rotateToAngleRate = 0;

    double max_collision = 0;

    double last_world_linear_accel_x;
    double last_world_linear_accel_y;
    double curr_world_linear_accel_x;
    double currentJerkX;
    double curr_world_linear_accel_y;
    double currentJerkY;
    int collisionDetected = 0;

    final static double kCollisionThreshold_DeltaG = 1.8f;
    /*
     * This tuning parameter indicates how close to "on target" the PID
     * Controller will attempt to get.
     */
    static final double kToleranceDegrees = 2.0f;

    // Initialize your subsystem here
    public NavX_MXP_PID()
    {
	super(kP, kI, kD);
	try
	{
	     /*Communicate w/navX-MXP via the MXP SPI Bus. 
	    
	     * Alternatively: I2C.Port.kMXP, SerialPort.Port.kMXP or
	     * SerialPort.Port.kUSB
	     
	    
	     * See
	     * http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/
	     * for details.
	    */ 
	    ahrs = new AHRS(SPI.Port.kMXP);
	} catch (RuntimeException ex)
	{
	    DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
	}
	setInputRange(-180, 180);
	setOutputRange(-1, 1);
	setAbsoluteTolerance(kToleranceDegrees);
	getPIDController().setContinuous();
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
	return ahrs.getAngle();
    }
    
    //Collision Detection Feature (Very Experimental)

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
    
    public void setSetpoint(double setpoint)
    {
	setSetpoint(setpoint);
    }

    protected double returnPIDInput()
    {
	// Return your input value for the PID loop
	// e.g. a sensor, like a potentiometer:
	// yourPot.getAverageVoltage() / kYourMaxVoltage;
	return ahrs.getAngle();
    }

    protected void usePIDOutput(double output)
    {
	// Use output to drive your system, like a motor
	rotateToAngleRate = output;
    }
    
    public void report()
    {
	SmartDashboard.putNumber("Heading", ahrs.getAngle());
    }
}