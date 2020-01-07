package frc.team4909.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrainSubsystem extends SubsystemBase{
    WPI_TalonFX frontRight, frontLeft, backRight, backLeft;
    SpeedControllerGroup m_right, m_left;
    DifferentialDrive bionicDrive;

    public DriveTrainSubsystem() {
        frontRight = new WPI_TalonFX(0);
        backRight = new WPI_TalonFX(2);
        m_right = new SpeedControllerGroup(frontRight, backRight);

        frontLeft = new WPI_TalonFX(1);
        backLeft = new WPI_TalonFX(3);
        m_left = new SpeedControllerGroup(frontLeft, backLeft);

        bionicDrive = new DifferentialDrive(m_left, m_right);
    }

    public void arcadeDrive(double leftSpeed, double rightSpeed) {
        double speedOutput = leftSpeed;
        double turnOutput = rightSpeed;

        bionicDrive.arcadeDrive(speedOutput, turnOutput);
    }

}