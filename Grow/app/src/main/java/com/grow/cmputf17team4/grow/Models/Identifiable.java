package com.grow.cmputf17team4.grow.Models;

import java.util.UUID;

/**
 * Interface allows to get the id and index
 * @author Qin Zhang
 * @since 1.0
 */

public interface Identifiable {
    UUID getUid();
    UUID generateUid();
    String getIndex();
}
