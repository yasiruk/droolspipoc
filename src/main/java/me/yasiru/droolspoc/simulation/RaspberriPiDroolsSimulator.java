package me.yasiru.droolspoc.simulation;

import me.yasiru.droolspoc.PieGate;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by yasiru on 3/12/16.
 */
public class RaspberriPiDroolsSimulator {

    public static final void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        final KieContainer kContainer = ks.getKieClasspathContainer();

        KieSession kieSession = kContainer.newKieSession("drools_simulation");
        System.out.println("Setting both pins to HIGH");
        PieGate pieGate = new PieGate(PieGate.HIGH,PieGate.HIGH);
        kieSession.insert(pieGate);
        kieSession.fireAllRules();
        kieSession.destroy();

        kieSession = kContainer.newKieSession("drools_simulation");
        System.out.println("Setting both pins to LOW");
        pieGate = new PieGate(PieGate.LOW,PieGate.LOW);
        kieSession.insert(pieGate);
        kieSession.fireAllRules();
        kieSession.destroy();

        kieSession = kContainer.newKieSession("drools_simulation");
        System.out.println("Setting one pin to LOW and the other to HIGH");
        pieGate = new PieGate(PieGate.HIGH,PieGate.LOW);
        kieSession.insert(pieGate);
        kieSession.fireAllRules();
        kieSession.destroy();
    }

}
