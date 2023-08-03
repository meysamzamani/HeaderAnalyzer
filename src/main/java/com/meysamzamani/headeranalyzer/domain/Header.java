package com.meysamzamani.headeranalyzer.domain;

import java.io.Serializable;

/**
 * The Header class represents an HTTP request header. It holds information about the name and detail of the header.
 * This class is used to store and access header data in a structured manner.
 */
public class Header implements Serializable {

    /**
     * The name of the HTTP request header.
     */
    private String name;

    /**
     * The detail or value associated with the HTTP request header.
     */
    private String detail;

    /**
     * Constructs a new Header object with the given name and detail.
     *
     * @param name   the name of the HTTP request header.
     * @param detail the detail or value associated with the HTTP request header.
     */
    public Header(String name, String detail) {
        this.name = name;
        this.detail = detail;
    }

    /**
     * Gets the name of the HTTP request header.
     *
     * @return the name of the HTTP request header.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the detail or value associated with the HTTP request header.
     *
     * @return the detail or value associated with the HTTP request header.
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Returns a string representation of the Header object. The string contains the header name and detail in the
     * format: "name: detail<br>".
     *
     * @return a string representation of the Header object.
     */
    @Override
    public String toString() {
        return name + ": " + detail + "<br>";
    }
}
