package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Roller2 extends SubsystemBase {

    private final TalonFX rollerMotor = new TalonFX(32); // change ID

    private final VelocityVoltage velocityRequest = new VelocityVoltage(0);

    public Roller2() {
        Slot0Configs slot0 = new Slot0Configs();

        slot0.kP = 0;
        slot0.kI = 0;
        slot0.kD = 0;
        slot0.kV = 0;

        rollerMotor.getConfigurator().apply(slot0);
    }

    public void rollerRotate(double targetVelocity) {
        // targetVelocity is in rotations per second
        rollerMotor.setControl(velocityRequest.withVelocity(targetVelocity));
    }

    public void rollerStop() {
        rollerMotor.set(0);
    }
}