package com.netflix.vms.transformer.hollowinput;

import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.index.AbstractHollowUniqueKeyIndex;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class ShowMemberTypePrimaryKeyIndex extends AbstractHollowUniqueKeyIndex<VMSHollowInputAPI, ShowMemberTypeHollow> {

    public ShowMemberTypePrimaryKeyIndex(HollowConsumer consumer) {
        this(consumer, true);    }

    public ShowMemberTypePrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh) {
        this(consumer, isListenToDataRefresh, ((HollowObjectSchema)consumer.getStateEngine().getSchema("ShowMemberType")).getPrimaryKey().getFieldPaths());
    }

    public ShowMemberTypePrimaryKeyIndex(HollowConsumer consumer, String... fieldPaths) {
        this(consumer, true, fieldPaths);
    }

    public ShowMemberTypePrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh, String... fieldPaths) {
        super(consumer, "ShowMemberType", isListenToDataRefresh, fieldPaths);
    }

    public ShowMemberTypeHollow findMatch(Object... keys) {
        int ordinal = idx.getMatchingOrdinal(keys);
        if(ordinal == -1)
            return null;
        return api.getShowMemberTypeHollow(ordinal);
    }

}