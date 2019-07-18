package com.netflix.vms.transformer.input.api.gen.showCountryLabel;

import com.netflix.hollow.api.custom.HollowListTypeAPI;
import com.netflix.hollow.api.objects.delegate.HollowListLookupDelegate;
import com.netflix.hollow.core.read.dataaccess.HollowListTypeDataAccess;

@SuppressWarnings("all")
public class ISOCountryListTypeAPI extends HollowListTypeAPI {

    private final HollowListLookupDelegate delegateLookupImpl;

    public ISOCountryListTypeAPI(ShowCountryLabelAPI api, HollowListTypeDataAccess dataAccess) {
        super(api, dataAccess);
        this.delegateLookupImpl = new HollowListLookupDelegate(this);
    }

    public ISOCountryTypeAPI getElementAPI() {
        return getAPI().getISOCountryTypeAPI();
    }

    public HollowListLookupDelegate getDelegateLookupImpl() {
        return delegateLookupImpl;
    }

    public ShowCountryLabelAPI getAPI() {
        return (ShowCountryLabelAPI)api;
    }

}