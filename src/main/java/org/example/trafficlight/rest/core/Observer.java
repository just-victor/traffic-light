package org.example.trafficlight.rest.core;

public interface Observer {
    void notifyObserver(Road road, String carName);
}
