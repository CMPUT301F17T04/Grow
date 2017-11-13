package com.grow.cmputf17team4.grow.Models;

import java.util.UUID;

/**
 * Created by qin7 on 2017/11/8.
 */

interface Identifiable {
    UUID getUid();
    UUID generateUid();
    String getIndex();
}
