package com.netflix.vms.transformer.hollowinput;

import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.index.AbstractHollowUniqueKeyIndex;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class StoriesSynopsesHookPrimaryKeyIndex extends AbstractHollowUniqueKeyIndex<VMSHollowInputAPI, StoriesSynopsesHookHollow> {

    public StoriesSynopsesHookPrimaryKeyIndex(HollowConsumer consumer) {
        this(consumer, ((HollowObjectSchema)consumer.getStateEngine().getSchema("StoriesSynopsesHook")).getPrimaryKey().getFieldPaths());
    }

    public StoriesSynopsesHookPrimaryKeyIndex(HollowConsumer consumer, String... fieldPaths) {
        this(consumer, true, fieldPaths);
    }

    public StoriesSynopsesHookPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefreah, String... fieldPaths) {
        super(consumer, "StoriesSynopsesHook", isListenToDataRefreah, fieldPaths);
    }

    public StoriesSynopsesHookHollow findMatch(Object... keys) {
        int ordinal = idx.getMatchingOrdinal(keys);
        if(ordinal == -1)
            return null;
        return api.getStoriesSynopsesHookHollow(ordinal);
    }

}