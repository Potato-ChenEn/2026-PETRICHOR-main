package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeCopy extends SubsystemBase {
    private final TalonFX intake_Roller = new TalonFX(32);
    private final TalonFX intake_Deploy = new TalonFX(33);    // id change


    public void intakeRotate(){
        intake_Roller.set(0.3);
    }


    public void intakeReverseRotate(){
        intake_Roller.set(-0.3);
    }

    public void intakeStop(){
        intake_Roller.set(0);
    }

    public void deployIntake(){
        intake_Deploy.set(0);
    }

}
