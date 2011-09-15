/*
 * Copyright 2011 - 2012
 * All rights reserved. License and terms according to LICENSE.txt file.
 * The LICENSE.txt file and this header must be included or referenced 
 * in each piece of code derived from this project.
 */
package com.metaos.datamgt;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import java.util.logging.Logger;


/**
 * Field from a source with actions to perform over a listener.
 */
public abstract class Field {
    /**
     * Qualifiers for a field.
     */
    public static enum Qualifier {
        BID, ASK, PRICE;
    }


    protected final Qualifier qualifier;
    protected final String field;

    // Private to avoid external extensions.
    private Field(final Qualifier qualifier, final String field) {
        this.qualifier = qualifier;
        this.field = field;
    }

    /**
     * Notifies to market the value of this field.
     */
    public void notify(final CacheWriteable listener, final Calendar moment,
            final String symbol, final double val) {
        switch(qualifier) {
            case BID:
                if(this.field==null) {
                    listener.setBid(moment, symbol, val);
                } else {
                    listener.setBid(moment, symbol + "-" + this.field, val);
                }
                break;
            case ASK:
                if(this.field==null) {
                    listener.setAsk(moment, symbol, val);
                } else {
                    listener.setAsk(moment, symbol + "-" + this.field, val);
                }
                break;
            case PRICE:
                if(this.field==null) {
                    listener.set(moment, symbol, val);
                } else {
                    listener.set(moment, symbol + "-" + this.field, val);
                }
                break;
        }
    }


    public static final class NONE extends Field {
        public NONE(final Qualifier qualifier) { super(qualifier, null); }
    }

    public static final class OPEN extends Field {
        public OPEN(final Qualifier qualifier) { super(qualifier, "OPEN"); }
        public OPEN() { this(Qualifier.PRICE); }
    }

    public static final class CLOSE extends Field {
        public CLOSE(final Qualifier qualifier) { super(qualifier, "CLOSE"); }
        public CLOSE() { this(Qualifier.PRICE); }
    }

    public static final class HIGH extends Field {
        public HIGH(final Qualifier qualifier) { super(qualifier, "HIGH"); }
        public HIGH() { this(Qualifier.PRICE); }
    }

    public static final class LOW extends Field {
        public LOW(final Qualifier qualifier) { super(qualifier, "LOW"); }
        public LOW() { this(Qualifier.PRICE); }
    }

    public static final class VOLUME extends Field {
        public VOLUME(final Qualifier qualifier) { super(qualifier, "VOLUME"); }
        public VOLUME() { this(Qualifier.PRICE); }
    }

    public static final class EXTENDED extends Field {
        public EXTENDED(final Qualifier qualifier, final String extension) {
            super(qualifier, extension);
        }
    }
}
