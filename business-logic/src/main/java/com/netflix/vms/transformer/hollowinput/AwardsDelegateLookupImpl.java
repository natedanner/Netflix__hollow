package com.netflix.vms.transformer.hollowinput;

import com.netflix.hollow.api.objects.delegate.HollowObjectAbstractDelegate;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class AwardsDelegateLookupImpl extends HollowObjectAbstractDelegate implements AwardsDelegate {

    private final AwardsTypeAPI typeAPI;

    public AwardsDelegateLookupImpl(AwardsTypeAPI typeAPI) {
        this.typeAPI = typeAPI;
    }

    public long getAwardId(int ordinal) {
        return typeAPI.getAwardId(ordinal);
    }

    public Long getAwardIdBoxed(int ordinal) {
        return typeAPI.getAwardIdBoxed(ordinal);
    }

    public int getAwardNameOrdinal(int ordinal) {
        return typeAPI.getAwardNameOrdinal(ordinal);
    }

    public int getAlternateNameOrdinal(int ordinal) {
        return typeAPI.getAlternateNameOrdinal(ordinal);
    }

    public int getDescriptionOrdinal(int ordinal) {
        return typeAPI.getDescriptionOrdinal(ordinal);
    }

    public AwardsTypeAPI getTypeAPI() {
        return typeAPI;
    }

    @Override
    public HollowObjectSchema getSchema() {
        return typeAPI.getTypeDataAccess().getSchema();
    }

    @Override
    public HollowObjectTypeDataAccess getTypeDataAccess() {
        return typeAPI.getTypeDataAccess();
    }

}