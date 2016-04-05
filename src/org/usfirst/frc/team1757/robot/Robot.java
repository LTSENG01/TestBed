
package org.usfirst.frc.team1757.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

/**
 * For motor: 
 * positive = UP
 * negative = DOWN
 * 
 * Use BUTTON_START to switch between motor modes. If there are more talons, change the talon IDs in init.
 * 
 * @author Larry Tseng
 *
 */



public class Robot extends IterativeRobot {

	Joystick gamepad;
	MotorTypes motorTypes;
	
	Breach breach;
	Climb climb;

	public static int 
	BUTTON_A, BUTTON_B, BUTTON_X, 
	BUTTON_Y, BUTTON_LB, BUTTON_RB, 
	BUTTON_START, BUTTON_BACK,  
	BUTTON_LS, BUTTON_RS,  
	AXIS_X, AXIS_Y, AXIS_RSX, AXIS_RSY, 
	AXIS_LT, AXIS_RT; 
	
	public enum MotorTypes {
		Breach, Climb;
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	public void robotInit() {
	
		breach = new Breach();
		climb = new Climb();
		gamepad = new Joystick(0);

		BUTTON_A = 1;
		BUTTON_B = 2;
		BUTTON_X = 3;
		BUTTON_Y = 4;
		BUTTON_LB = 5;
		BUTTON_RB = 6;
		BUTTON_START = 8;
		BUTTON_BACK = 7;
		BUTTON_LS = 9;
		BUTTON_RS = 10;
		
		AXIS_X = 0;
		AXIS_Y = 1;
		AXIS_RSX = 2;
		AXIS_RSY = 5;
		AXIS_LT = 2;
		AXIS_RT = 3; 

		motorTypes = MotorTypes.Breach;

	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
	public void autonomousInit() {

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		checkMotorMode();
		doMotorTest();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

	public void checkMotorMode() {
		if (gamepad.getRawButton(BUTTON_START)) {
			switch (motorTypes) {
			case Breach:
				motorTypes = MotorTypes.Climb;
				System.out.println("Switched MotorType: " + motorTypes);
				Timer.delay(.5);
				break;
			case Climb:
				motorTypes = MotorTypes.Breach;
				System.out.println("Switched MotorType: " + motorTypes);
				Timer.delay(.5);
				break;
			}
		}
	}

	public void doMotorTest() {
		switch (motorTypes) {
		case Breach:
			doBreach();
			break;
		case Climb:
			doClimb();
			break;
		default: 
			System.out.println("Error: No MotorType");
		}
	}
	
	public void doBreach() {
		if (gamepad.getRawAxis(AXIS_LT) > .2) {
			breach.downBreach();
		} else if (gamepad.getRawAxis(AXIS_RT) > .2) {
			breach.upBreach();
		} 

		if (gamepad.getRawButton(BUTTON_A)) {
			breach.doBreach();
		} else if (gamepad.getRawButton(BUTTON_B)) {
			breach.resetBreach();
		} else {
			breach.stopBreach();
		}
	}
	
	public void doClimb() {
		if (gamepad.getRawAxis(AXIS_LT) > .2) {
			climb.downClimb();
		} else if (gamepad.getRawAxis(AXIS_RT) > .2) {
			climb.upClimb();
		} 

		if (gamepad.getRawButton(BUTTON_A)) {
			climb.doClimb();
		} else if (gamepad.getRawButton(BUTTON_B)) {
			climb.resetClimb();
		} else {
			climb.stopClimb();
		}
	}
}
