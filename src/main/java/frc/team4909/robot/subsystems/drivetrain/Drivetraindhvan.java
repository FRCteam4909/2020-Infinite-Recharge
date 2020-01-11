package frc.team4909.robot.subsystems.drivetrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetraindhvan extends SubsystemBase {
    // WPI_TalonFX frontRight, frontLeft, backRight, backLeft;
    CANSparkMax frontRight, frontLeft, backRight, backLeft;
    SpeedControllerGroup m_right, m_left;
    DifferentialDrive bionicDrive;

    public Drivetraindhvan() {
        //frontRight = new WPI_TalonFX(0);
        //backRight = new WPI_TalonFX(2);
        frontRight = new CANSparkMax(1, MotorType.kBrushless);
        backRight = new CANSparkMax(2, MotorType.kBrushless);
        m_right = new SpeedControllerGroup(frontRight, backRight);

        //frontLeft = new WPI_TalonFX(1);
        //backLeft = new WPI_TalonFX(3);
        frontLeft = new CANSparkMax(3, MotorType.kBrushless);
        backLeft = new CANSparkMax(4, MotorType.kBrushless);
        m_left = new SpeedControllerGroup(frontLeft, backLeft);

        bionicDrive = new DifferentialDrive(m_left, m_right);
    }

    public void arcadeDrive(final double leftSpeed, final double rightSpeed) {
        final double speedOutput = leftSpeed;
        final double turnOutput = rightSpeed;

        bionicDrive.arcadeDrive(speedOutput, turnOutput);
    }

    public void initialize(){
    }

    @Override
    public void periodic(){
        bionicDrive.feedWatchdog();
    } 
    

}