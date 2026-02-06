package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.VelocityDutyCycle;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
    private final TalonFX shooter_A = new TalonFX(35); //change ID
    private final TalonFX shooter_B = new TalonFX(36); //change ID
    // private final TalonFX shooter_Trans = new TalonFX(37); //change ID

    private final VelocityDutyCycle shooterRequest = new VelocityDutyCycle(0);

    public Shooter() {
        var configA = shooter_A.getConfigurator();
        var configB = shooter_B.getConfigurator();

        Slot0Configs pid = new Slot0Configs();
        pid.kP = ShooterConstants.kP;
        pid.kI = ShooterConstants.kI;
        pid.kD = ShooterConstants.kD;
        
        configA.apply(pid);
        configB.apply(pid);
        
        // shooter_B.setInverted(true);
    }

    public void shooterRotate(double rps){
        shooter_A.setControl(shooterRequest.withVelocity(rps));
        shooter_B.setControl(shooterRequest.withVelocity(rps));
        // shooter_A.set(0.4);          
        // shooter_B.set(0.4);
    }

    public void shooterStop(){
        shooter_A.set(0);
        shooter_B.set(0);
    }
}