package org.example.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.function.Predicate.not;

public class Warlord extends Defender {
    public Warlord() {
        setInitialHealth(100);
        setHealth(100);
        setStrength(4);
        setArmor(2);
    }

    public Iterable<Warrior> moveUnits(Iterator<Warrior> iterator) {
        List<Warrior> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }

        var lancers = list.stream()
                .filter(Lancer.class::isInstance)
                .toList();
        var healers = list.stream()
                .filter(Healer.class::isInstance)
                .toList();
        var otherUnits = list.stream()
                .filter(not(Lancer.class::isInstance))
                .filter(not(Healer.class::isInstance))
                .filter(not(Warlord.class::isInstance))
                .toList();

        List<Warrior> out = new ArrayList<>();
        out.addAll(lancers);
        out.addAll(otherUnits);
        out.addAll(Math.min(out.size(), 1), healers);
        out.add(this);

        return out;
    }
}