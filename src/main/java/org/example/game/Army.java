package org.example.game;

import java.util.*;
import java.util.function.Supplier;


public class Army implements Iterable<Warrior> {
    private Warlord warlord;
    private int size;

    public int getSize() {
        return size;
    }

    private class Node extends Warrior implements WarriorInArmy {
        Warrior warrior;
        Node next;

        public Node(Warrior warrior) {
            this.warrior = warrior;
            this.next = this;
        }

        @Override
        public WarriorInArmy getNextBehind() {
            return next == head ? null : next;
        }

        @Override
        public Army getArmy() {
            return Army.this;
        }

        @Override
        public Warrior getWrapped() {
            return warrior;
        }

        @Override
        public void hit(CanReceiveDamage opponent) {
            warrior.hit(opponent);
            processCommand(new HealCommand(), this);
        }

        @Override
        public int getStrength() {
            return warrior.getStrength();
        }

        @Override
        public int getHealth() {
            return warrior.getHealth();
        }

        @Override
        protected void setHealth(int health) {
            warrior.setHealth(health);
        }

        @Override
        public void receiveDamage(HasStrength damager) {
            warrior.receiveDamage(damager);
        }


    }

    private final Node head = new Node(null);
    private Node tail = head;

    public boolean isEmpty() {
        return tail == head;
    }

    public void removeDeadBodies() {
        var it = iterator();
        while (it.hasNext()) {
            if (!it.next().isAlive()) {
                it.remove();
            }
        }
    }

    private void addToTail(Warrior warrior) {
        var node = new Node(warrior);
        node.next = head;
        tail.next = node;
        tail = node;
        size++;
    }

    public Army addUnits(Supplier<Warrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            Warrior warrior = factory.get();
            if (warrior instanceof Warlord) {
                if (warlord == null) {
                    warlord = (Warlord) warrior;
                } else {
                    break;
                }
            }
            addToTail(warrior);
        }
        return this;
    }

    public void addSeducedUnit(Warrior warrior) {
        addToTail(warrior);
    }

    private void addSwappedUnits(Supplier<Warrior> factory) {
        addToTail(factory.get());
    }

    public Army equipWarriorAtPosition(int position, Weapon weapon) {
        int i = 0;
        Node pos = head.next;

        while (i < position) {
            pos = pos.next;
            i++;
        }

        pos.warrior.equipWeapon(weapon).prepareForBattle();

        return this;
    }

    public Warrior unitAtPosition(int position) {
        int i = 0;
        Node pos = head.next;

        while (i < position) {
            pos = pos.next;
            i++;
        }

        return pos.warrior;
    }

    public void moveUnits() {
        if (warlord != null && !warlord.isAlive()) {
            warlord = null;
        }
        if (warlord != null) {
            Iterable<Warrior> newArrangement = warlord.moveUnits(iterator());
            clear();
            for (var warrior : newArrangement) {
                addSwappedUnits(() -> warrior);
            }
        }
    }

    private void clear() {
        tail = head.next = head;
        size = 0;
    }

    public Iterator<Warrior> firstAlive() {
        return new FirstAliveIterator();
    }

    private class FirstAliveIterator implements Iterator<Warrior> {
        @Override
        public boolean hasNext(){
            while (!isEmpty() && !peek().isAlive()){
                removeFromHead();
            }
            return !isEmpty();
        }
        @Override
        public Warrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            var res = peek();
            return res == head ? null : res;
        }

        private Warrior peek() {
            return head.next;
        }

        private void removeFromHead() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            if (tail == head.next) {
                tail = head;
            }
            head.next = head.next.next;
            size--;
        }
    }

    @Override
    public Iterator<Warrior> iterator() {
        return new SimpleIterator();
    }

    private class SimpleIterator implements Iterator<Warrior> {
        Node cursor = head;
        Node previous = null;

        @Override
        public boolean hasNext() {
            return cursor.next != head;
        }

        @Override
        public Warrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            previous = cursor;
            cursor = cursor.next;
            return cursor.warrior;
        }

        @Override
        public void remove() {
            if (previous == null) {
                throw new IllegalStateException();
            }
            previous.next = cursor.next;
            cursor = previous;
            previous = null;
        }
    }
}
