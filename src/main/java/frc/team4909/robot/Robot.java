package frc.team4909.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.operator.generic.BionicAxis;
import frc.team4909.robot.subsystems.drivetrain.Drive;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterDown;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterUp;
import frc.team4909.robot.subsystems.indexer.IndexerSubsystem;
import frc.team4909.robot.subsystems.indexer.commands.IndexerUp;
import frc.team4909.robot.subsystems.indexer.commands.SorterOn;
import frc.team4909.robot.subsystems.intake.IntakeIn;
import frc.team4909.robot.subsystems.intake.IntakeSubsystem;
import frc.team4909.robot.subsystems.indexer.SorterSubsystem;
import frc.team4909.robot.subsystems.leds.LEDs;
import frc.team4909.robot.subsystems.shooter.*;
import frc.team4909.robot.subsystems.shooter.commands.FollowTarget;
import frc.team4909.robot.subsystems.shooter.commands.HoodDown;
import frc.team4909.robot.subsystems.shooter.commands.HoodUp;
import frc.team4909.robot.subsystems.shooter.commands.SetHoodPosition;
import frc.team4909.robot.subsystems.shooter.commands.SetShooterVelocity;
import frc.team4909.robot.subsystems.shooter.commands.ShootBalls;
import frc.team4909.robot.subsystems.shooter.commands.TurretSpeed;
import frc.team4909.robot.subsystems.shooter.commands.ZeroTurret;

public class Robot extends TimedRobot {
  public static DriveTrainSubsystem drivetrainsubsystem;
  public static ShooterSubsystem shootersubsystem;
  public static IndexerSubsystem indexerSubsystem;
  public static SorterSubsystem sorterSubsystem;
  public static HoodSubsystem hoodSubsystem;
  public static IntakeSubsystem intakeSubsystem;
  public static LEDs leds;
  public static Vision vision;
  public static BionicF310 driverGamepad, manipulatorGamepad;

  public static ParallelCommandGroup shooterLimelightAssist;

  @Override
  public void robotInit() {
    
    drivetrainsubsystem = new DriveTrainSubsystem();
    drivetrainsubsystem.setDefaultCommand(new Drive(drivetrainsubsystem));

    vision = new Vision();

    shootersubsystem = new ShooterSubsystem();
    // shootersubsystem.setDefaultCommand(new FollowTarget(shootersubsystem, vision)); //(new FollowTarget(shootersubsystem, vision));

    indexerSubsystem = new IndexerSubsystem();
    // indexerSubsystem.setDefaultCommand(new IndexerOI(indexerSubsystem));

    sorterSubsystem = new SorterSubsystem();

    hoodSubsystem = new HoodSubsystem();

    intakeSubsystem = new IntakeSubsystem();

    // leds = new LEDs();

    driverGamepad = new BionicF310(0, // Port
        0.6, // Deadzone
        0.1 // Gamepad sensitivity
    );

    manipulatorGamepad = new BionicF310(0, // Port
        0.6, // Deadzone
        0.1 // Gamepad sensitivity
    );

    driverGamepad.buttonPressed(BionicF310.A, new FollowTarget(shootersubsystem, vision));
    driverGamepad.buttonToggled(BionicF310.B, new ShootBalls(2500), true);
    // driverGamepad.buttonPressed(BionicF310.Start, new SetHoodPosition(shootersubsystem, 100));
    driverGamepad.buttonHeld(BionicF310.X, new IndexerAndSorterUp());
    driverGamepad.buttonHeld(BionicF310.Y, new IntakeIn());
    // driverGamepad.buttonHeld(BionicF310.Y, new IndexerAndSorterDown());
    driverGamepad.buttonHeld(BionicF310.LB, new HoodDown());
    driverGamepad.buttonHeld(BionicF310.RB, new HoodUp());
    // driverGamepad.buttonHeld(BionicF310., new IndexerUp(indexerSubsystem));

    
}

  @Override   
  public void robotPeriodic() {
    //System.out.print("test");
    Scheduler.getInstance().run();
    CommandScheduler.getInstance().run();
     
    SmartDashboard.putNumber("X Offset", vision.getXOffset());
    SmartDashboard.putNumber("Shooter Distance", Robot.vision.calculateDistanceFromCameraHeight(RobotConstants.powerPortHeight, RobotConstants.limelightHeight, RobotConstants.limelightAngle));
    SmartDashboard.putBoolean("Upper Has Ball", indexerSubsystem.hasBallUpper());
    SmartDashboard.putBoolean("Lower Has Ball", indexerSubsystem.hasBallLower());
  }

  @Override
  public void disabledInit() {
    
  }

  @Override
  public void disabledPeriodic() {
    //leds.setAllianceColor();  
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    // CommandScheduler.getInstance().schedule(new ZeroTurret());
    hoodSubsystem.zeroHood();
    shootersubsystem.setSpeed(0);
  }

  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("X  - Offset", vision.getXOffset());
    SmartDashboard.putNumber("Shooter  - Distance", Robot.vision.calculateDistanceFromCameraHeight(RobotConstants.powerPortHeight, RobotConstants.limelightHeight, RobotConstants.limelightAngle));
  }

  @Override
  public void testPeriodic() {
  }
}
