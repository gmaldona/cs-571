import java.util.*;

public class AutomatonImpl implements Automaton {

    class StateLabelPair {
        int state;
        char label;
        public StateLabelPair(int state_, char label_) { state = state_; label = label_; }

        @Override
        public int hashCode() {
            return Objects.hash((Integer) state, (Character) label);
        }

        @Override
        public boolean equals(Object o) {
            StateLabelPair o1 = (StateLabelPair) o;
            return (state == o1.state) && (label == o1.label);
        }
    }

    HashSet<Integer> start_states;
    HashSet<Integer> accept_states;
    HashSet<Integer> current_states;
    HashMap<StateLabelPair, HashSet<Integer>> transitions;

    public AutomatonImpl() {
        start_states = new HashSet<Integer>();
        accept_states = new HashSet<Integer>();
        transitions = new HashMap<StateLabelPair, HashSet<Integer>>();
    }

    @Override
    public void addState(int s, boolean is_start, boolean is_accept) {
        if (is_start) {
            start_states.add(s);
        }
        if (is_accept) {
            accept_states.add(s);
        }
    }

    @Override
    public void addTransition(int s_initial, char label, int s_final) {
        StateLabelPair p = new StateLabelPair(s_initial, label);
        for (StateLabelPair P : transitions.keySet()) {
            if (P.equals(p)) {
                transitions.get(p).add(s_final);
                return;
            }
        }
        HashSet<Integer> s = new HashSet<>();
        s.add(s_final);
        transitions.put(p, s);
    }

    @Override
    public void reset() {
        this.current_states = start_states;
    }

    @Override
    public void apply(char input) {
        HashSet<Integer> new_states = new HashSet<>();
        for (Integer state : current_states) {
            StateLabelPair pair = new StateLabelPair(state, input);
            for (StateLabelPair p : transitions.keySet()) {
                if (pair.equals(p)) {
                    new_states.addAll(transitions.get(p));
                }
            }
        }
        current_states = new_states;
    }

    @Override
    public boolean accepts() {
        for (Integer state : current_states) {
            if (accept_states.contains(state)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasTransitions(char label) {
        for (Integer state : current_states) {
            StateLabelPair pair = new StateLabelPair(state, label);
            for (StateLabelPair transition_pair : transitions.keySet()) {
                if (pair.equals(transition_pair)) {
                    return true;
                }

            }
        }
        return false;
    }
}
