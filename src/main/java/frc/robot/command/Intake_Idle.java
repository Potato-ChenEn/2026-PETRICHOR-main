package frc.robot.command;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Intake;

public class Intake_Idle extends Command {
    private final Intake intake;

    public Intake_Idle (Intake intake){
        this.intake = intake;
        addRequirements(this.intake);
    }

    @Override
    public void execute(){
        intake.Intake_In();
        intake.intakeStop();

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
