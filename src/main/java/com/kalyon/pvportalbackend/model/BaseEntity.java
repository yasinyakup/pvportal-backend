package com.kalyon.pvportalbackend.model;

import java.util.Date;

public interface BaseEntity {
    Date created = new Date();
    Date updated = null;
    String updatedBy = null;
    String createdBy = null;
}
