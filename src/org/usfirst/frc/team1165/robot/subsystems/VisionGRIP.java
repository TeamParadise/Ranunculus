package org.usfirst.frc.team1165.robot.subsystems;

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

public class VisionGRIP extends Subsystem
{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private static final int IMG_WIDTH = 320;
    private static final int IMG_HEIGHT = 240;
    public int centerX = 0;

    private VisionThread visionThread;
    private final Object imgLock = new Object();

    public VisionGRIP()
    {

    }

    public void findCenter()
    {
	try
	{
	    visionThread = new VisionThread(Robot.usbCameras[0], new GripContoursPipeline(), pipeline ->
	    {
		int i = 0;
		if (!pipeline.filterContoursOutput().isEmpty())
		{
		    Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
		    synchronized (imgLock)
		    {
			centerX = r.x + (r.width / 2);
			SmartDashboard.putNumber("CenterX", i++);
		    }
		}
		SmartDashboard.putNumber("CenterX", i++);
	    });
	    visionThread.start();
	} catch (Exception e)
	{
	    SmartDashboard.putBoolean("Camera Crashed", true);
	}
    }

    public void displayCenter()
    {
	SmartDashboard.putNumber("Center X", centerX);
    }

    public void initDefaultCommand()
    {
	// Set the default command for a subsystem here.
	setDefaultCommand(new DisplayContourCenter());
    }
}