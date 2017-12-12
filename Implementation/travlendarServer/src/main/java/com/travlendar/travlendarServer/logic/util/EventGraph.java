package com.travlendar.travlendarServer.logic.util;

import com.travlendar.travlendarServer.logic.modelInterface.EventLogic;

import java.util.*;
import java.util.stream.Collectors;

public class EventGraph {
    private List<EventLogic> nodes;
    private Map<EventLogic, List<EventLogic>> edges;

    public List<EventLogic> nodes() {
        return nodes;
    }

    public EventGraph(List<EventLogic> nodes, Map<EventLogic, List<EventLogic>> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public Map<EventLogic, List<EventLogic>> edges() {
        return edges;
    }

    public List<EventLogic> adjacentNodes(EventLogic u) {
        return edges.get(u);
    }

    public boolean contains(EventLogic u) {
        return nodes.contains(u);
    }

    public void addNode(EventLogic u){
        int i = 0;
        if(!nodes.contains(u)) {
            for (EventLogic event : nodes) {
                if (event.compareTo(u) > 0)
                    break;
                i++;
            }
            nodes.add(i, u);
        }
    }


    public List<Edge> edgesFrom(EventLogic u) {
        if(edges.containsKey(u))
            return edges.get(u).stream()
                .map(v -> new Edge(u, v))
                .collect(Collectors.toList());
        else
            return new ArrayList<>();
    }

    public void connect(EventLogic e1, EventLogic e2){
        if(!isAdjacent(e1, e2)){
            int i = 0;
            if(!edges.containsKey(e1))
                edges.put(e1, new ArrayList<>());
            for(EventLogic event: edges.get(e1)){
                if(event.compareTo(e2) > 0)
                    break;
            }
            edges.get(e1).add(i, e2);
        }
    }

    public boolean isAdjacent(EventLogic u, EventLogic v) {
        return edges.containsKey(u) && edges.get(u).contains(v);
    }

    public boolean pathExists(EventLogic u, EventLogic v) {
        return pathExists(u, v, true);
    }

    /**
     * Returns true if there exists a path from s to e in this graph.
     * If includeAdjacent is false, it returns true if there exists
     * another path from s to e of distance > 1
     */
    private boolean pathExists(EventLogic u, EventLogic v, boolean includeAdjacent) {
        if (!nodes.contains(u) || !nodes.contains(v)) {
            return false;
        }
        if (includeAdjacent && isAdjacent(u, v)) {
            return true;
        }
        Deque<EventLogic> stack = new LinkedList<>();
        Set<EventLogic> visited = new HashSet<>();
        stack.push(u);
        while (!stack.isEmpty()) {
            EventLogic node = stack.pop();
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
        final EventLogic s;
        final EventLogic e;
        Edge(EventLogic u, EventLogic v) {
            this.s = u;
            this.e = v;
        }

        public EventLogic getS() {
            return s;
        }

        public EventLogic getE() {
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
