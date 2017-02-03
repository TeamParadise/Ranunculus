package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.MoveServo;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ServoSystem extends Subsystem
{

    Servo servo;
    private boolean toggle = false;
    public ServoSystem()
    {
	servo = new Servo(RobotMap.rightServoPortNumber);
    }

    public void initDefaultCommand()
    {
	// Set the default command for a subsystem here.
	setDefaultCommand(new MoveServo(findServoAngle()));
    }
    
    public void setServoPosition(double angle)
    {
	servo.setAngle(angle);
    }
    
    public double findServoAngle()
    {
	return toggle?90:0;
    }
    
    public void toggleServo()
    {
	toggle = !toggle;
    }
    public void report()
    {
	SmartDashboard.putNumber("Servo Angle", servo.getAngle());
    }
}