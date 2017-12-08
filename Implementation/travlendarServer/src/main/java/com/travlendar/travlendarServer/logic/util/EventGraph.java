package com.travlendar.travlendarServer.logic.util;

import com.travlendar.travlendarServer.model.domain.Event;

import java.util.*;
import java.util.stream.Collectors;

public class EventGraph {
    private List<Event> nodes;
    private Map<Event, List<Event>> edges;

    public List<Event> nodes() {
        return nodes;
    }

    public EventGraph(List<Event> nodes, Map<Event, List<Event>> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public Map<Event, List<Event>> edges() {
        return edges;
    }

    public List<Event> adjacentNodes(Event u) {
        return edges.get(u);
    }

    public boolean contains(Event u) {
        return nodes.contains(u);
    }

    public void addNode(Event u){
        int i = 0;
        if(!nodes.contains(u)) {
            for (Event event : nodes) {
                if (event.compareTo(u) > 0)
                    break;
                i++;
            }
            nodes.add(i, u);
        }
    }


    public List<Edge> edgesFrom(Event u) {
        return edges.get(u).stream()
                .map(v -> new Edge(u, v))
                .collect(Collectors.toList());
    }

    public void connect(Event e1, Event e2){
        if(!isAdjacent(e1, e2)){
            int i = 0;
            for(Event event: edges.get(e1)){
                if(event.compareTo(e2) > 0)
                    break;
            }
            edges.get(e1).add(i, e2);
        }
    }

    public boolean isAdjacent(Event u, Event v) {
        return edges.containsKey(u) && edges.get(u).contains(v);
    }

    public boolean pathExists(Event u, Event v) {
        return pathExists(u, v, true);
    }

    /**
     * Returns true if there exists a path from s to e in this graph.
     * If includeAdjacent is false, it returns true if there exists
     * another path from s to e of distance > 1
     */
    private boolean pathExists(Event u, Event v, boolean includeAdjacent) {
        if (!nodes.contains(u) || !nodes.contains(v)) {
            return false;
        }
        if (includeAdjacent && isAdjacent(u, v)) {
            return true;
        }
        Deque<Event> stack = new LinkedList<>();
        Set<Event> visited = new HashSet<>();
        stack.push(u);
        while (!stack.isEmpty()) {
            Event node = stack.pop();
            if (node.equals(v)) {
                return true;
            }
            if (!visited.contains(node)) {
                visited.add(node);
                edges.get(node).stream()
                        .filter(e -> includeAdjacent || !node.equals(u) || !e.equals(v))
                        .forEach(stack::push);
            }
        }
        assert !visited.contains(v);
        return false;
    }

    public static class Edge {
        final Event s;
        final Event e;
        Edge(Event u, Event v) {
            this.s = u;
            this.e = v;
        }

        public Event getS() {
            return s;
        }

        public Event getE() {
            return e;
        }

        @Override
        public String toString() {
            return String.format("%s -> %s", s, e);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || !(o instanceof Edge))
                return false;

            @SuppressWarnings("unchecked")
            Edge edge = (Edge) o;

            return s.equals(edge.s) && e.equals(edge.e);
        }

        @Override
        public int hashCode() {
            int result = s.hashCode();
            result = 31 * result + e.hashCode();
            return result;
        }
    }
}
