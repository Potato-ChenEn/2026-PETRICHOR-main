package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    private final TalonFX shooterMotor = new TalonFX(32); //change ID

    public void shooterRotate(){
        shooterMotor.set(0.4);          //change ts
    }

    // public void shooterReverseRotate(){
    //     shooterMotor.set(-0.4);
    // }

    public void shooterStop(){
        shooterMotor.set(0);
    }
}
