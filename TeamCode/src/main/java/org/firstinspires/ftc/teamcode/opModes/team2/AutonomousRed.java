package org.firstinspires.ftc.teamcode.opModes.team2;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Team 2 - Autonomous Red", group="2_Autonomous")
public class AutonomousRed extends AutonomousGeneric {
        @Override
        public void setup() {
                this.isSpinnerReversed = true;
        }
}
