/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.lb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Weighted round robin load balance
 */
public class LoadBalance {
    private final List<Node> nodes;

    public LoadBalance(Map<Upstream, Integer> upstreamWeight) {
        if (upstreamWeight != null && !upstreamWeight.isEmpty()) {
            nodes = new ArrayList<>(upstreamWeight.size());

            upstreamWeight.forEach((upstream, weight) -> {
                nodes.add(new Node(upstream, weight));
            });
        } else {
            nodes = null;
        }
    }

    /**
     * Weighted round robin load balance
     *
     * @return the selected upstream
     */
    public Optional<Upstream> weightedRoundRobin() {
        if (!hasNodes()) {
            return Optional.empty();
        }

        int totalWeight = 0;
        Node nodeHasMaxWeight = null;
        for (Node node : nodes) {
            totalWeight += node.effectiveWeight;

            node.currentWeight += node.effectiveWeight;

            if (nodeHasMaxWeight == null) {
                nodeHasMaxWeight = node;
            } else {
                nodeHasMaxWeight = nodeHasMaxWeight.compareTo(node) > 0 ? nodeHasMaxWeight : node;
            }
        }

        if(nodeHasMaxWeight != null) {
            nodeHasMaxWeight.currentWeight -= totalWeight;
            return Optional.ofNullable(nodeHasMaxWeight.upstream);
        }

        return Optional.empty();
    }

    public void onSuccess(Upstream upstream) {
        if (hasNodes()) {
            Optional<Node> optional = nodes.stream().filter(node -> node.upstream.id().equals(upstream.id())).findFirst();
            optional.ifPresent(Node::onSuccess);
        }
    }

    public void onFailure(Upstream upstream) {
        if (hasNodes()) {
            Optional<Node> optional = nodes.stream().filter(node -> node.upstream.id().equals(upstream.id())).findFirst();
            optional.ifPresent(Node::onFailure);
        }
    }

    public String getCurrentWeight(boolean beforeSelect) {
        if (hasNodes()) {
            final StringBuilder sb = new StringBuilder("{");
            nodes.forEach(node -> sb.append(node.upstream.id())
                    .append("=")
                    .append(beforeSelect ? node.currentWeight + node.effectiveWeight : node.currentWeight)
                    .append(" ")
            );
            sb.append("}");
            return sb.toString();
        }

        return "";
    }

    private boolean hasNodes() {
        return nodes != null && nodes.size() > 0;
    }

    private static class Node implements Comparable<Node> {
        private final Upstream upstream;
        private final int weight;

        private int effectiveWeight;
        private int currentWeight;

        Node(Upstream upstream, int weight) {
            this.upstream = upstream;
            this.weight = weight;
            effectiveWeight = weight;
            currentWeight = 0;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(currentWeight, o.currentWeight);
        }

        public void onSuccess() {
            if (effectiveWeight < weight) {
                effectiveWeight++;
            }
        }

        public void onFailure() {
            effectiveWeight--;
        }
    }
}