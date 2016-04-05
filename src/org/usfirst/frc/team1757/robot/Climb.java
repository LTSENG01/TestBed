package org.usfirst.frc.team1757.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climb {
	
	private static CANTalon talon;
	private float climbMotorSpeed;
	
	public Climb() {
		talon = new CANTalon(4);
		talon.set(0);
		talon.setInverted(true);
	}
	
	public void downClimb() {
		climbMotorSpeed -= 0.01;
		climbMotorSpeed = Math.max(-1, climbMotorSpeed);
		System.out.println("Decrementing climbMotorSpeed..." + climbMotorSpeed);
	}
	
	public void upClimb() {
		climbMotorSpeed += 0.01;
		climbMotorSpeed = Math.min(1, climbMotorSpeed);
		System.out.println("Incrementing climbMotorSpeed..." + climbMotorSpeed);
	}
	
	public void stopClimb() {
		talon.set(0);
		SmartDashboard.putNumber("climb-climbMotorSpeed", climbMotorSpeed);
	}
	
	public void resetClimb() {
		climbMotorSpeed = 0;
		System.out.println("climbMotorSpeed: " + climbMotorSpeed);
		talon.set(climbMotorSpeed);
		SmartDashboard.putNumber("climb-climbMotorSpeed", climbMotorSpeed);
		Timer.delay(.5);
	}
	
	public void doClimb() {
		talon.set(climbMotorSpeed);
		System.out.println("Climbing. Current climbMotorSpeed: " + climbMotorSpeed);
		SmartDashboard.putNumber("climb-climbMotorSpeed", climbMotorSpeed);
	}
}
