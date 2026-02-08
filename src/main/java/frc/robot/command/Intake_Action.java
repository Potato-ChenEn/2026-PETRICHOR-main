package frc.robot.command;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Spindexer; //如果有要配合 洗衣機 去用

public class Intake_Action extends Command {
    private final Intake intake;

    public Intake_Action (Intake intake){
        this.intake = intake;
        addRequirements(this.intake);
    }

    @Override
    public void execute(){
        intake.Intake_Out();
        intake.intakeRotate();

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
