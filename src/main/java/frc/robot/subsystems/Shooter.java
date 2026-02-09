package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.controls.VelocityDutyCycle;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.VoltageConfigs;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.controls.NeutralOut;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/* 需要的： 
 * 1.FeedForward (PIDF)
 * 2.Voltage 控制
 */

public class Shooter extends SubsystemBase {
    private final TalonFX shooter_Motor = new TalonFX(35); //主控制馬達
    private final TalonFX shooter_MotorF = new TalonFX(36); //Follow主控制馬達
    // private final TalonFX shooter_3 = new TalonFX(36);

    private final VelocityVoltage shooterRequest = new VelocityVoltage(0).withSlot(0);
    private final NeutralOut neutralOut = new NeutralOut();

    public Shooter() {

        var shooterMotor_cfg = shooter_Motor.getConfigurator();
        shooterMotor_cfg.apply(new VoltageConfigs()
                .withPeakForwardVoltage(12)
                .withPeakReverseVoltage(-12)
);
        shooterMotor_cfg.apply(new FeedbackConfigs().withFeedbackSensorSource(FeedbackSensorSourceValue.RotorSensor));
        shooterMotor_cfg.apply(new MotorOutputConfigs().withInverted(InvertedValue.Clockwise_Positive).withNeutralMode(NeutralModeValue.Coast));

        Slot0Configs shooter_pid = new Slot0Configs();
        shooter_pid.kP = ShooterConstants.Shooter_kP;
        shooter_pid.kI = ShooterConstants.Shooter_kI;
        shooter_pid.kD = ShooterConstants.Shooter_kD;
        shooter_pid.kV = ShooterConstants.Shooter_kV;
        shooterMotor_cfg.apply(shooter_pid);

        shooter_MotorF.setControl(new Follower(shooter_Motor.getDeviceID(), MotorAlignmentValue.Opposed));

    }

    public double getCurrentRPS(){
        
        return shooter_Motor.getVelocity().getValueAsDouble();
    }

    public boolean isReadyToShoot(double targetRPS){
        return getCurrentRPS() > 80;
    }

    public void shooterRotate(double rps){
        shooter_Motor.setControl(shooterRequest.withVelocity(rps));
    }

    public void shooterStop(){
        shooter_Motor.setControl(neutralOut);
        // shooter_Motor.set(0);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Shooter_RPS", getCurrentRPS());
    }
}