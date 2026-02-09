package frc.robot;

import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.MetersPerSecond;

import java.util.Optional;

import com.pathplanner.lib.config.PIDConstants;
import com.pathplanner.lib.config.RobotConfig;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import frc.robot.generated.TunerConstants;

public class Constants {
    public static class autoPathConstants {
        public static final PIDConstants translationConstants = new PIDConstants(5.0, 0.0, 0.0);
        public static final PIDConstants rotationConstants = new PIDConstants(5.0, 0.0, 0.0);
    }

    public static class driveConstants {
        public static final double maxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
        public static final double maxAngularRate = Units.RotationsPerSecond.of(0.75).in(Units.RadiansPerSecond); // 3/4 of a rotation per second max angular velocity
        
        //wpiblue view
        // public static final Pose3d redHubPose = new Pose3d(Units.Meters.of(11.91), Units.Meters.of(4.03), Units.Meters.of(1.83), new Rotation3d());
        // public static final Pose3d blueHubPose = new Pose3d(Units.Meters.of(3.88), Units.Meters.of(4.03),  Units.Meter.of(1.83), new Rotation3d());
        public static final Pose3d redHubPose = new Pose3d(Units.Inches.of(468.56), Units.Inches.of(158.32), Units.Inches.of(72.0), new Rotation3d());
        public static final Pose3d blueHubPose = new Pose3d(Units.Inches.of(152.56), Units.Inches.of(158.32),  Units.Inches.of(72.0), new Rotation3d());

        public static final Angle epsilonAngleToGoal = Degree.of(0.1); //robot will stop if it's in range of 5deg

        public static final PIDController rotationController = getRotationController();

        private static final PIDController getRotationController() {
            PIDController controller = new PIDController(1.5, 0.0, 0.00514); 
            controller.enableContinuousInput(-Math.PI, Math.PI);
            return controller;
        }

        //decide blue or red hub pose by driverstation
        public static final Pose3d getHubPose() {
            Pose3d hubPose = DriverStation.getAlliance().equals(Optional.of(Alliance.Blue)) ? blueHubPose : redHubPose;
            return hubPose;
        }
    }

    public static class IntakeConstants { //這裏的PID是給ARM用的，不是roller （MotionMagic）

        public static final double MAX_ACCEL = 500;  //這裏要調整
        public static final double MAX_VELOCITY = 200;

        public static final double Intake_Out_kP = 0.3;
        public static final double Intake_Out_kI = 0;
        public static final double Intake_Out_kD = 0;

        public static final double Intake_In_kP = 0.3;
        public static final double Intake_In_kI = 0;
        public static final double Intake_In_kD = 0;

        public static final double Intake_Zero = 0; //定位歸零？ig
        public static final double Intake_Out = 3;
        public static final double Intake_In = 0;

    }

    public static class ShooterConstants { //這個才是shooter的roller
    
        public static final double Shooter_kP = 0.5;
        public static final double Shooter_kI = 0;
        public static final double Shooter_kD = 0;
        public static final double Shooter_kV = 0.05; // Feedforward 前饋

    }
    

    public static class swerveDriveConstants {
        public static final double maxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
        public static final double maxAngularRate = Units.RotationsPerSecond.of(0.75).in(Units.RadiansPerSecond); // 3/4 of a rotation per second max angular velocity
    }

    public static class visionConstants {
        public static final String NAME1 = "limelight-one";
        public static final Transform3d ll1_to_ROBOT = new Transform3d();
        public static final String NAME2 = "limelight-two";
        public static final Transform3d ll2_to_ROBOT = new Transform3d();
    }
}