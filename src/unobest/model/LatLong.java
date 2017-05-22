/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unobest.model;

/**
 *
 * @author comqdhb
 */
public class LatLong {

    double lat = 0.0, lng = 0.0;

    public double distanceTo(LatLong other) {
        double R = 6371; // km
        double lat1 = lat;
        double lat2 = other.lat;
        double lon1 = lng;
        double lon2 = other.lng;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d;
    }

    public double bearingTo(LatLong other) {
        double lat1 = Math.toRadians(lat);
        double lat2 = Math.toRadians(other.lat);
        double long1 = Math.toRadians(lng);
        double long2 = Math.toRadians(other.lng);
        double y = Math.sin(lat2 - lat1) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2)
                - Math.sin(lat1) * Math.cos(lat2) * Math.cos(long2 - long1);
        double brng = Math.toDegrees(Math.atan2(y, x));
        return brng;
    }

}
