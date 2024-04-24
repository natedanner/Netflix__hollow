/*
 *  Copyright 2016-2019 Netflix, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
package com.netflix.hollow.api.consumer.data;

import com.netflix.hollow.api.objects.delegate.HollowObjectGenericDelegate;
import com.netflix.hollow.api.objects.generic.GenericHollowObject;
import com.netflix.hollow.core.read.engine.object.HollowObjectTypeReadState;
import com.netflix.hollow.core.type.accessor.IntegerDataAccessor;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class IntegerDataAccessorTest extends AbstractPrimitiveTypeDataAccessorTest<Integer> {

    @Override
    protected Class<Integer> getDataModelTestClass() {
        return Integer.class;
    }

    @Override
    protected Integer getData(HollowObjectTypeReadState readState, int ordinal) {
        GenericHollowObject obj = new GenericHollowObject(new HollowObjectGenericDelegate(readState), ordinal);
        return obj.getInt("value");
    }

    @Test
    public void test() throws IOException {
        addRecord(Integer.valueOf(1));
        addRecord(Integer.valueOf(2));
        addRecord(Integer.valueOf(3));

        roundTripSnapshot();
        {
            IntegerDataAccessor dAccessor = new IntegerDataAccessor(readStateEngine, new PrimitiveTypeTestAPI(readStateEngine));
            Assert.assertEquals(3, dAccessor.getAddedRecords().size());
            assertList(dAccessor.getAddedRecords(), Arrays.<Integer>asList(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)));
            Assert.assertTrue(dAccessor.getRemovedRecords().isEmpty());
            Assert.assertTrue(dAccessor.getUpdatedRecords().isEmpty());
        }

        writeStateEngine.prepareForNextCycle(); /// not necessary to call, but needs to be a no-op.

        addRecord(Integer.valueOf(1));
        // addRecord(new Integer(2)); // removed
        addRecord(Integer.valueOf(3));
        addRecord(Integer.valueOf(1000)); // added
        addRecord(Integer.valueOf(0)); // added

        roundTripDelta();
        {
            IntegerDataAccessor dAccessor = new IntegerDataAccessor(readStateEngine, new PrimitiveTypeTestAPI(readStateEngine));
            Assert.assertEquals(2, dAccessor.getAddedRecords().size());
            assertList(dAccessor.getAddedRecords(), Arrays.asList(Integer.valueOf(1000), Integer.valueOf(0)));
            Assert.assertEquals(1, dAccessor.getRemovedRecords().size());
            assertList(dAccessor.getRemovedRecords(), Arrays.asList(Integer.valueOf(2)));
            Assert.assertEquals(0, dAccessor.getUpdatedRecords().size());
        }

        HollowObjectTypeReadState typeState = (HollowObjectTypeReadState) readStateEngine.getTypeState("Integer");
        Assert.assertEquals(4, typeState.maxOrdinal());

        assertObject(typeState, 0, Integer.valueOf(1));
        assertObject(typeState, 1, Integer.valueOf(2)); /// this was "removed", but the data hangs around as a "ghost" until the following cycle.
        assertObject(typeState, 2, Integer.valueOf(3));
        assertObject(typeState, 3, Integer.valueOf(1000));
        assertObject(typeState, 4, Integer.valueOf(0));

        roundTripDelta(); // remove everything
        {
            IntegerDataAccessor dAccessor = new IntegerDataAccessor(readStateEngine, new PrimitiveTypeTestAPI(readStateEngine));
            Assert.assertEquals(0, dAccessor.getAddedRecords().size());
            Assert.assertEquals(4, dAccessor.getRemovedRecords().size());
            assertList(dAccessor.getRemovedRecords(), Arrays.asList(Integer.valueOf(1), Integer.valueOf(3), Integer.valueOf(1000), Integer.valueOf(0)));
            Assert.assertEquals(0, dAccessor.getUpdatedRecords().size());
        }

        assertObject(typeState, 0, Integer.valueOf(1)); /// all records were "removed", but again hang around until the following cycle.
        // assertObject(typeState, 1, new Integer(2)); /// this record should now be disappeared.
        assertObject(typeState, 2, Integer.valueOf(3)); /// "ghost"
        assertObject(typeState, 3, Integer.valueOf(1000)); /// "ghost"
        assertObject(typeState, 4, Integer.valueOf(0)); /// "ghost"

        Assert.assertEquals(4, typeState.maxOrdinal());

        addRecord(Integer.valueOf(634));
        addRecord(Integer.valueOf(0));

        roundTripDelta();
        {
            IntegerDataAccessor dAccessor = new IntegerDataAccessor(readStateEngine, new PrimitiveTypeTestAPI(readStateEngine));
            Assert.assertEquals(2, dAccessor.getAddedRecords().size());
            assertList(dAccessor.getAddedRecords(), Arrays.asList(Integer.valueOf(634), Integer.valueOf(0)));
            Assert.assertEquals(0, dAccessor.getRemovedRecords().size());
            Assert.assertEquals(0, dAccessor.getUpdatedRecords().size());
        }

        Assert.assertEquals(1, typeState.maxOrdinal());
        assertObject(typeState, 0, Integer.valueOf(634)); /// now, since all records were removed, we can recycle the ordinal "0", even though it was a "ghost" in the last cycle.
        assertObject(typeState, 1, Integer.valueOf(0)); /// even though new Integer(0) had an equivalent record in the previous cycle at ordinal "4", it is now assigned to recycled ordinal "1".
    }
}