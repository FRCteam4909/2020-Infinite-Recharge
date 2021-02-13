package frc.team4909.robot.subsystems.drivetrain;

public class DriveTrainRamp {
    
    //Class Variables/attributes
    
        /*  
        *   Values we'll need
        *   lastValue
        *   joystickOutput
        *   acceleration threshold (could be its own function with related constants, or could be a constant)
        */

    //Class Variables :
   //The maximum rate of change of the "speedoutput" in the drivetrain arcade drive.  
    private static double accelThreshold = 0.01; //TODO test value
    private static double lastValue = 0; 

    public static double getRampedOutput(double speedOutput){

        //If the speed is trying to acellerate too fast
        if(Math.abs(speedOutput - lastValue) > accelThreshold){
            //Sets speedOutPut equal to its last value plus the limit 
            speedOutput = lastValue + Math.copySign(accelThreshold, speedOutput - lastValue); //CopySign handles accel and deccel
        }

        lastValue = speedOutput;
        return speedOutput;

    } 
}