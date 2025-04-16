package com.example.javacalculator;

public class Subnet {
    private String network;
    private String broadcast;
    private int mask;
    private int hostCount;

    public Subnet(String network, String broadcast, int mask, int hostCount) {
        this.network = network;
        this.broadcast = broadcast;
        this.mask = mask;
        this.hostCount = hostCount;
    }

    public String getNetwork() {
        return network;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public int getMask() {
        return mask;
    }

    public int getHostCount() {
        return hostCount;
    }

    @Override
    public String toString() {
        return "Subred " + network + "/" + mask + "\n" +
                "Broadcast: " + broadcast + "\n" +
                "Hosts disponibles: " + hostCount + "\n";
    }
}
