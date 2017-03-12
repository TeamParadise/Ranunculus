package org.usfirst.frc.team1165.robot;

import org.usfirst.frc.team1165.robot.commands.Climb;
import org.usfirst.frc.team1165.robot.commands.DriveShooter;
import org.usfirst.frc.team1165.robot.commands.DriveStraightNavX;
import org.usfirst.frc.team1165.robot.commands.EnableUltrasonicSetpoint;
import org.usfirst.frc.team1165.robot.commands.LineWithVisionTape;
import org.usfirst.frc.team1165.robot.commands.RotateToHeading;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	public Joystick stick = new Joystick(RobotMap.joystickPort);

	JoystickButton shooterRPMButton = new JoystickButton(stick, RobotMap.shooterRPMPortNumber);//1

	JoystickButton ultrasonicPIDButton = new JoystickButton(stick, RobotMap.enableUltrasonicPID);//10
	
	//JoystickButton visionPIDButton = new JoystickButton(stick, RobotMap.enableVisionPID);//12
	
	JoystickButton placeGearVisionButton = new JoystickButton(stick, RobotMap.placeGearButton);//12
	
	JoystickButton driveStraightButton = new JoystickButton(stick, 7);
	
	JoystickButton visionSonar = new JoystickButton(stick, 8);
	
	JoystickButton driveStraight = new JoystickButton(stick, 11);

	JoystickButton climb = new JoystickButton(stick, RobotMap.climbButton);//2
	JoystickButton rotateN180 = new JoystickButton(stick, RobotMap.rotateRobotN180);//3
	JoystickButton rotate180 = new JoystickButton(stick, RobotMap.rotateRobot180);//4
	JoystickButton rotateN90 = new JoystickButton(stick, RobotMap.rotateRobotN90);//5
	JoystickButton rotate90 = new JoystickButton(stick, RobotMap.rotateRobot90);//6
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:
	public OI()
	{
		shooterRPMButton.whenPressed(new DriveShooter());
		ultrasonicPIDButton.whenPressed(new EnableUltrasonicSetpoint());
		climb.whenPressed(new Climb());
		
		placeGearVisionButton.whenPressed(new LineWithVisionTape());
		visionSonar.whenPressed(new LineWithVisionTape());
		// Rotate To Heading
		rotateN180.whenPressed(new RotateToHeading(-180));
		rotate180.whenPressed(new RotateToHeading(180));
		rotateN90.whenPressed(new RotateToHeading(-90));
		rotate90.whenPressed(new RotateToHeading(90));
		
		driveStraight.whenPressed(new DriveStraightNavX(0.4, true));
		driveStraightButton.whenPressed(new DriveStraightNavX(0.4, -40, true));		
	}
}