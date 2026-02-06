package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.VelocityDutyCycle;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
    private final TalonFX intake_Roller = new TalonFX(32);
    // private final TalonFX intake_Deploy = new TalonFX(33);    // id change

    private final VelocityDutyCycle intakeRequest = new VelocityDutyCycle(0);

    public Intake() {
        var m_config = intake_Roller.getConfigurator();
        
        Slot0Configs pid = new Slot0Configs();
        pid.kP = IntakeConstants.kP;
        pid.kI = IntakeConstants.kI;
        pid.kD = IntakeConstants.kD;
        
        m_config.apply(pid);
    }

    public void intakeRotate(double rps){
        intake_Roller.setControl(intakeRequest.withVelocity(rps));
        //intake_Roller.set(0.3);
    }


    public void intakeReverseRotate(){
        intake_Roller.set(-0.3);
    }

    public void intakeStop(){
        intake_Roller.set(0);
    }

    // public void deployIntake(){
    //     intake_Deploy.set(0);
    // }

}
