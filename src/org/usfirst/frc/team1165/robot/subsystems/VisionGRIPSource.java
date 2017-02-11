package org.usfirst.frc.team1165.robot.subsystems;

import java.util.Date;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.commands.DisplayContourCenter;

import com.ctre.CANTalon;

import Util.GripContoursPipeline;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;
import edu.wpi.first.wpilibj.vision.VisionPipeline;
import edu.wpi.first.wpilibj.vision.VisionRunner;

public class VisionGRIPSource extends Subsystem
{
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	double center[];
	double average = 0;
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	public int centerX = 0;
	int i = 7;

	private VisionThread visionThread;
	private final Object imgLock = new Object();

	Date date;

	public VisionGRIPSource()
	{
		date = new Date();
	}
	
	public void findCenter()
	{
		try
		{
			visionThread = new VisionThread(Robot.usbCameras[0], Robot.pipeline, pipeline ->
			{
				if (!Robot.pipeline.filterContoursOutput().isEmpty())
					SmartDashboard.putBoolean("Filter Contours Empty", false);
				else
					SmartDashboard.putBoolean("Filter Contours Empty", true);
				computeAverageCenter();
				report();
			});
			visionThread.start();
		} catch (Exception e)
		{
			SmartDashboard.putBoolean("Camera Crashed", true);
		}
	}

	public void computeAverageCenter()
	{
		center = new double[Robot.pipeline.filterContoursOutput.size()];
		average = 0;
		SmartDashboard.putNumber("Length", center.length);
		for (int i = 0; i < center.length; i++)
		{
			if (!Robot.pipeline.filterContoursOutput().isEmpty())
			{
				Rect r = Imgproc.boundingRect(Robot.pipeline.filterContoursOutput().get(i));
				synchronized (imgLock)
				{
					centerX = r.x + (r.width / 2);
					center[i] = centerX;
				}
			}
			average += centerX;
		}
		average /= center.length;
	}
	
	public boolean filterContoursEmpty()
	{
		if(center.length == 0)
			return true;
		return false;
	}	

	public void report()
	{
		for (int i = 0; i < center.length; i++)
		{
			SmartDashboard.putNumber("Center" + (i + 1), center[i]);
		}
		SmartDashboard.putNumber("Number of Contours", center.length);
		SmartDashboard.putNumber("Average", average);
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new DisplayContourCenter());
	}
}