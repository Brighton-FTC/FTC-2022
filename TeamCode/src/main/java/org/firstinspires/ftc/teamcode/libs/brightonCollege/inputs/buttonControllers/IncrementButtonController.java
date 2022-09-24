package org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.buttonControllers;

import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.RawButton;

/**
 * A pair of buttons used to manipulate a double value
 */
public class IncrementButtonController extends ButtonController<Double> {

    public double value;
    public double step;
    private final DebouncedButtonController incrementButton;
    private final DebouncedButtonController decrementButton;

    public IncrementButtonController(RawButton incrementButton, RawButton decrementButton, double initialValue, double step) {
        this.incrementButton = new DebouncedButtonController(incrementButton);
        this.decrementButton = new DebouncedButtonController(decrementButton);
        this.value = initialValue;
        this.step = step;
    }

    @Override
    protected Double processTick() {
        if (incrementButton.processTick()) value += step;
        if (decrementButton.processTick()) value -= step;
        return value;
    }
}
