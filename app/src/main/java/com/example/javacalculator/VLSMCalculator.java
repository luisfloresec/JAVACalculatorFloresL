package com.example.javacalculator;

import java.util.ArrayList;
import java.util.List;

public class VLSMCalculator {

    public static List<Subnet> calculateSubnets(String ip, int mainMask, int subnetCount) throws Exception {
        // Determinar la cantidad de bits requeridos para generar el número solicitado de subredes
        int requiredBits = (int) Math.ceil(Math.log(subnetCount) / Math.log(2));
        if (mainMask + requiredBits > 32) {
            throw new Exception("La cantidad de subredes solicitadas excede la capacidad de la red");
        }

        // Nuevo prefijo para las subredes
        int newMask = mainMask + requiredBits;
        // Tamaño del bloque para cada subred
        int blockSize = (int) Math.pow(2, 32 - newMask);

        // Convierte la IP a entero para facilitar los cálculos
        int ipInt = ipToInt(ip);
        List<Subnet> subnets = new ArrayList<>();

        for (int i = 0; i < subnetCount; i++) {
            int networkAddressInt = ipInt + i * blockSize;
            String networkAddress = intToIp(networkAddressInt);
            String broadcastAddress = intToIp(networkAddressInt + blockSize - 1);
            int hostCount = blockSize - 2;  // Resta la dirección de red y la de broadcast
            Subnet subnet = new Subnet(networkAddress, broadcastAddress, newMask, hostCount);
            subnets.add(subnet);
        }
        return subnets;
    }

    private static int ipToInt(String ipAddress) throws Exception {
        String[] parts = ipAddress.split("\\.");
        if (parts.length != 4) {
            throw new Exception("Formato de IP inválido");
        }
        int ip = 0;
        for (int i = 0; i < 4; i++) {
            int part = Integer.parseInt(parts[i]);
            if (part < 0 || part > 255) {
                throw new Exception("Cada parte de la IP debe estar entre 0 y 255");
            }
            ip = (ip << 8) | part;
        }
        return ip;
    }

    private static String intToIp(int ipInt) {
        return ((ipInt >> 24) & 0xFF) + "." +
                ((ipInt >> 16) & 0xFF) + "." +
                ((ipInt >> 8) & 0xFF) + "." +
                (ipInt & 0xFF);
    }
}