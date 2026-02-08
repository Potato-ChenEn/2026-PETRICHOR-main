package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.controls.MotionMagicDutyCycle;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.Slot1Configs;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
    private final TalonFX intake_Roller = new TalonFX(IntakeConstants.intake_Roller_ID, "rio");
    private final TalonFX intake_Ctrl = new TalonFX(IntakeConstants.intake_Ctrl_ID, "rio");

    public Intake() { //PID get 給ARM (intake_Ctrl)做MotionMagic
        var intake_Ctrl_Config = intake_Ctrl.getConfigurator();
        
        intake_Ctrl_Config.apply(new FeedbackConfigs()
                .withFeedbackSensorSource(FeedbackSensorSourceValue.RotorSensor));
        intake_Ctrl_Config.apply(new MotionMagicConfigs()
                .withMotionMagicAcceleration(IntakeConstants.MAX_ACCEL)
                .withMotionMagicCruiseVelocity(IntakeConstants.MAX_VELOCITY));
        
        Slot0Configs intake_Out_PIDConfigs = new Slot0Configs();
        intake_Out_PIDConfigs.kP = IntakeConstants.Intake_Out_kP;
        intake_Out_PIDConfigs.kI = IntakeConstants.Intake_Out_kI;
        intake_Out_PIDConfigs.kD = IntakeConstants.Intake_Out_kD;
        intake_Ctrl_Config.apply(intake_Out_PIDConfigs);

        Slot1Configs intake_In_PIDConfigs = new Slot1Configs();
        intake_In_PIDConfigs.kP = IntakeConstants.Intake_In_kP;
        intake_In_PIDConfigs.kI = IntakeConstants.Intake_In_kI;
        intake_In_PIDConfigs.kD = IntakeConstants.Intake_In_kD;
        intake_Ctrl_Config.apply(intake_In_PIDConfigs);

        intake_Ctrl.setPosition(0);
    }

    public double getPosition(){
        return intake_Ctrl.getPosition().getValueAsDouble();
    }

    public void Intake_Zero(){
        intake_Ctrl.setControl(new MotionMagicDutyCycle(IntakeConstants.Intake_Zero));
    }

    public void Intake_Out(){ //應該要跟intakeRotate綁在一起 吧 :p
        intake_Ctrl.setControl(new MotionMagicDutyCycle(IntakeConstants.Intake_Out));
    }

    public void Intake_In(){
        intake_Ctrl.setControl(new MotionMagicDutyCycle(IntakeConstants.Intake_In).withSlot(1));
    }

    public void intakeRotate(){
        intake_Roller.set(0.3);
    }

    public void intakeReverseRotate(){
        intake_Roller.set(-0.3);
    }

    public void intakeStop(){
        intake_Roller.set(0);
    }
}
