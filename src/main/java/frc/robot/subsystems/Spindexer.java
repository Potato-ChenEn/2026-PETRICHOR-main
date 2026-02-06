package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.VelocityDutyCycle;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RollerConstants;

public class Spindexer extends SubsystemBase {
    private final TalonFX spindexer_Roller = new TalonFX(34); //change ID

    private final VelocityDutyCycle spindexerRequest = new VelocityDutyCycle(0);

    public Spindexer() {
        var m_config = spindexer_Roller.getConfigurator();

        Slot0Configs pid = new Slot0Configs();
        pid.kP = RollerConstants.kP;
        pid.kI = RollerConstants.kI;
        pid.kD = RollerConstants.kD;
        
        m_config.apply(pid);
    }

    public void spindexerRotate(double rps){
        spindexer_Roller.setControl(spindexerRequest.withVelocity(rps));
        // spindexer_Roller.set(0.5);
    }

    public void spindexerStop(){
        spindexer_Roller.set(0);
    }
}
