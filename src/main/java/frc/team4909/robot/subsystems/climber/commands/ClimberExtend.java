package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimberExtend extends CommandBase {

    double pos;

    public ClimberExtend(){
        super();
        addRequirements(Robot.climberSubsystem);
    }

    @Override
    public void initialize() {
        Robot.leds.setRed();
        Robot.climberSubsystem.setClimberSpeed(-1);
        Robot.climberSubsystem.setRatchetSpeed(0);

    }

    @Override
    public void end(boolean interrupted) {
        Robot.climberSubsystem.setClimberSpeed(0);
    }

    // @Override
    // public boolean isFinished() {
    //     return true;
    //     // if(Math.abs(Robot.climberSubsystem.getClimbPos()-pos) <= 10){
    //     //     return true;
    //     // }
    //     // return false;
    // }
}