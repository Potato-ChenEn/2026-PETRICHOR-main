package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

// import com.ctre.phoenix6.configs.Slot0Configs;
// import com.ctre.phoenix6.controls.PositionDutyCycle;
// import com.ctre.phoenix6.controls.VelocityDutyCycle;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Roller extends SubsystemBase {
    
    private final TalonFX rollerMotor = new TalonFX(32); //change ID

    public void rollerRotate(){
        rollerMotor.set(0.4);      //change
    }

    // public void rollerReverseRotate(){
    //     rollerMotor.set(-0.4);
    // }

    public void rollerStop(){
        rollerMotor.set(0);
    }
}
