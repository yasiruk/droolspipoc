package me.yasiru.droolspoc.pie;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import me.yasiru.droolspoc.PieGate;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by yasiru on 3/14/16.
 */
public class RaspberriPiDrools {
    public static final void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        final KieContainer kContainer = ks.getKieClasspathContainer();

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        /* provision GPIO pins as follows
            GPIO2 : input pin 1 for logic gate
            GPIO3 : input pin 2 for logic gate
            GPIO1 : output pin of logic gate
         */

        final GpioPinDigitalInput pinA = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
        final GpioPinDigitalInput pinB = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03, PinPullResistance.PULL_DOWN);
        final GpioPinDigitalOutput pinF = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "OUTPUT", PinState.HIGH);

        //set the shutdown state for GPIO 01 to LOW
        pinF.setShutdownOptions(true, PinState.LOW);

        //interrupt listener for GPIO 02 will trigger rules when there is a state change on the GPIO pin
        pinA.addListener(new GpioPinListenerDigital() {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                final KieSession kSession = kContainer.newKieSession("drools_test_pie");
                //Read the state of the input pins and wrap them in PieGate instance
                PieGate pieGate = new PieGate(pinA.getState().getValue(),pinB.getState().getValue());

                kSession.insert(pieGate);

                kSession.fireAllRules();
                kSession.destroy();
            }
        });
        //interrupt listener for GPIO 03 will trigger rules when there is a state change on the GPIO pin

        pinB.addListener(new GpioPinListenerDigital() {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                final KieSession kSession = kContainer.newKieSession("drools_test_pie");
                //Read the state of the input pins and wrap them in PieGate instance
                PieGate pieGate = new PieGate(pinA.getState().getValue(),pinB.getState().getValue());

                kSession.insert(pieGate);

                kSession.fireAllRules();
                kSession.destroy();
            }
        });
    }

}
