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
	double midWidth = Robot.usbCameraImageWidth / 2.0;
	double average = midWidth;
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
				/*if (visionThread.isInterrupted())
					{
						visionThread.stop();
					}
				else*/ if (!Robot.pipeline.filterContoursOutput().isEmpty())
				{
					SmartDashboard.putBoolean("Filter Contours Empty", false);
				}
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

	public void visionThreadStop()
	{
		if (visionThread.isAlive()) visionThread.stop(); //visionThread.interrupt();
	}
	
	public void computeAverageCenter()
	{
		center = new double[Robot.pipeline.filterContoursOutput.size()];
		SmartDashboard.putNumber("Length", center.length);
		if (center.length == 2) //go with two objects only <= 2)
		{
			//compute the average then transfer to make sure the PID doesn't check the average before it's complete
			double newAverage = 0;
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
				newAverage += centerX;
			}
			newAverage /= center.length;
			average = newAverage; //we know the new average, let's set it for the PID controller to access
		} else average = midWidth; //no correction

	}

	public boolean filterContoursEmpty()
	{
		if (center.length == 0)
			return true;
		return false;
	}

	public void report()
	{
		if (center.length <= 2)
		{
			for (int i = 0; i < center.length; i++)
			{
				SmartDashboard.putNumber("Center" + (i + 1), center[i]);
			}
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