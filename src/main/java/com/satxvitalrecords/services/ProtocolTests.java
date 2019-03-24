package com.satxvitalrecords.services;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;
import se.walkercrou.places.TypeParam;
import se.walkercrou.places.Types;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;
import static se.walkercrou.places.GooglePlaces.*;

/**
 * Tests written to mirror the official API guides found at:
 *
 * https://developers.google.com/places/web-service/search
 */
public class ProtocolTests {
    private static final String API_KEY_FILE_NAME = "places_api.key";
    private static final String UVM_NAME = "University of Vermont";
    private static final double UVM_LAT = 29.4106;
    private static final double UVM_LNG = -98.4128;
    private GooglePlaces google;


    @Before
    public void setUp() {
        System.out.println("---- Initializing tests ----");
        System.out.println(API_KEY_FILE_NAME);
        try {
            InputStream in = ProtocolTests.class.getResourceAsStream("/" + API_KEY_FILE_NAME);
            if (in == null)
                fail("API key not found");
            google = new GooglePlaces(IOUtils.toString(in));
            google.setDebugModeEnabled(true);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testNearbySearch() {
        System.out.println("---- Nearby Search ---");
        List<Place> places = google.getNearbyPlaces(UVM_LAT, UVM_LNG, MAXIMUM_RADIUS, TypeParam.name("types").value(Types.TYPE_LOCAL_GOVERNMENT_OFFICE));

        assertContainsPlace(places, UVM_NAME);
        testTextSearch();
    }

    public void testTextSearch() {
        System.out.println("---- Text Search ----");
        List<Place> places = google.getPlacesByQuery("Notary", GooglePlaces.MAXIMUM_RESULTS);
        assertContainsPlace(places, "Notary");

    }

    private static void assertContainsPlace(List<Place> places, String name) {
        for (Place place : places)
            if (place.getName().equals(name))
                return;
        fail("Place \"" + name + "\" not found!");
    }








}
