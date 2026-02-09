package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Spindexer extends SubsystemBase {
    
    private final TalonFX spindexer_Roller = new TalonFX(34);

    public void spindexerRotate(){
        spindexer_Roller.set(0.5);
    }

    public void spindexerStop(){
        spindexer_Roller.set(0);
    }
}
