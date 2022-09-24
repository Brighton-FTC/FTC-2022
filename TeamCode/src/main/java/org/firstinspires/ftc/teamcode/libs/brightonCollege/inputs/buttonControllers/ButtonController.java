package org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.buttonControllers;

import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.Inputs;

/**
 * A button class that prevents querying the same data on the same tick (and causing bugs)
 */
public abstract class ButtonController<T> {
    public int lastTick;
    public T lastValue;

    public ButtonController(){
        lastTick = Inputs.getTick();
    }

    public T getValue() {
        int currentTick = Inputs.getTick();
        if (currentTick == lastTick) return lastValue;
        lastValue = processTick();
        lastTick = currentTick;
        return lastValue;
    }

    /**
     * This function may be called up to once per tick. Determines whether the button is activated
     */
    protected abstract T processTick();
}
