/*-------------------------------------------------------------------------------------------------------------------*\
|  Copyright (C) 2015 eBay Software Foundation                                                                        |
|                                                                                                                     |
|  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance     |
|  with the License.                                                                                                  |
|                                                                                                                     |
|  You may obtain a copy of the License at                                                                            |
|                                                                                                                     |
|       http://www.apache.org/licenses/LICENSE-2.0                                                                    |
|                                                                                                                     |
|  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed   |
|  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for  |
|  the specific language governing permissions and limitations under the License.                                     |
\*-------------------------------------------------------------------------------------------------------------------*/

package com.paypal.selion.platform.dataprovider;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.Test;

import com.paypal.selion.platform.dataprovider.pojos.xml.Address;

/**
 * Unit tests for {@link DataProviderHelper}.
 *
 */
public class DataProviderHelperTest {

    @Test(groups = "unit")
    public void testParseIndexString() throws DataProviderException {
        String indexes = "1-3, 5, 7-8";
        List<Integer> arrayIndex = DataProviderHelper.parseIndexString(indexes);
        assertEquals(arrayIndex.size(), 6);
        assertFalse(arrayIndex.contains(6));
        assertTrue(arrayIndex.contains(7));
    }

    @Test(groups = "unit", expectedExceptions = { DataProviderException.class })
    public void testExceptionWhenParseIndexString() throws DataProviderException {
        String indexes = "1-3, 5, 7_8";
        DataProviderHelper.parseIndexString(indexes);
    }

    @Test(groups = "unit")
    public void testConvertToObjectArray() {
        Object[][] converted = DataProviderHelper.convertToObjectArray("Selion");
        assertEquals(converted.length, 1);
        assertEquals(converted[0].length, 1);

        converted = DataProviderHelper.convertToObjectArray(2014);
        assertEquals(converted.length, 1);
        assertEquals(converted[0].length, 1);

        converted = DataProviderHelper.convertToObjectArray(true);
        assertEquals(converted.length, 1);
        assertEquals(converted[0].length, 1);

        converted = DataProviderHelper.convertToObjectArray(new Date());
        assertEquals(converted.length, 1);
        assertEquals(converted[0].length, 1);

        converted = DataProviderHelper.convertToObjectArray(123.4d);
        assertEquals(converted.length, 1);
        assertEquals(converted[0].length, 1);

        converted = DataProviderHelper.convertToObjectArray(999_99_9999L);
        assertEquals(converted.length, 1);
        assertEquals(converted[0].length, 1);

        converted = DataProviderHelper.convertToObjectArray(3.14f);
        assertEquals(converted.length, 1);
        assertEquals(converted[0].length, 1);

    }

    @Test(groups = "unit")
    public void testConvertSimpleArrayToObjectArray() {
        String[] strings = { "Selion", "OpenSource" };
        Object[][] converted = DataProviderHelper.convertToObjectArray(strings);
        assertEquals(converted.length, 2, "String array to Object[][] conversion failed.");
        assertEquals(converted[0].length, 1);
        assertEquals(converted[0][0], strings[0]);
        assertEquals(converted[1][0], strings[1]);

        int[] integers = { 2014, 2015, 2016 };
        converted = DataProviderHelper.convertToObjectArray(integers);
        assertEquals(converted.length, 3, "Integer array to Object[][] conversion failed.");
        assertEquals(converted[0].length, 1);
        assertEquals(converted[0][0], integers[0]);
        assertEquals(converted[1][0], integers[1]);
        assertEquals(converted[2][0], integers[2]);

        Integer[] integersAsObject = { 2014, 2015, 2016 };
        converted = DataProviderHelper.convertToObjectArray(integersAsObject);
        assertEquals(converted.length, 3, "Integer object array to Object[][] conversion failed.");
        assertEquals(converted[0].length, 1);
        assertEquals(converted[0][0], integersAsObject[0]);
        assertEquals(converted[1][0], integersAsObject[1]);
        assertEquals(converted[2][0], integersAsObject[2]);

        char[] chars = { 'a', '@', '?' };
        converted = DataProviderHelper.convertToObjectArray(chars);
        assertEquals(converted.length, 3, "Char array to Object[][] conversion failed.");
        assertEquals(converted[0].length, 1);
        assertEquals(converted[0][0], chars[0]);
        assertEquals(converted[1][0], chars[1]);
        assertEquals(converted[2][0], chars[2]);

        short[] shorts = { 2014, 2015, 2016 };
        converted = DataProviderHelper.convertToObjectArray(shorts);
        assertEquals(converted.length, 3, "Short array to Object[][] conversion failed.");
        assertEquals(converted[0].length, 1);
        assertEquals(converted[0][0], shorts[0]);
        assertEquals(converted[1][0], shorts[1]);
        assertEquals(converted[2][0], shorts[2]);

        boolean[] booleans = { true, false, false, false };
        converted = DataProviderHelper.convertToObjectArray(booleans);
        assertEquals(converted.length, 4, "Boolean array to Object[][] conversion failed.");
        assertEquals(converted[0].length, 1);
        assertEquals(converted[0][0], booleans[0]);
        assertEquals(converted[1][0], booleans[1]);
        assertEquals(converted[2][0], booleans[2]);
        assertEquals(converted[3][0], booleans[3]);

        Boolean[] booleanAsObject = { true, false, false, false };
        converted = DataProviderHelper.convertToObjectArray(booleanAsObject);
        assertEquals(converted.length, 4, "Boolean object array to Object[][] conversion failed.");
        assertEquals(converted[0].length, 1);
        assertEquals(converted[0][0], booleanAsObject[0]);
        assertEquals(converted[1][0], booleanAsObject[1]);
        assertEquals(converted[2][0], booleanAsObject[2]);
        assertEquals(converted[3][0], booleanAsObject[3]);

        Date[] dates = { new Date(), new Date() };
        converted = DataProviderHelper.convertToObjectArray(dates);
        assertEquals(converted.length, 2, "Date array to Object[][] conversion failed.");
        assertEquals(converted[0].length, 1);
        assertEquals(converted[0][0], dates[0]);
        assertEquals(converted[1][0], dates[1]);

        double[] doubles = { 123.4d, 1.234e2 };
        converted = DataProviderHelper.convertToObjectArray(doubles);
        assertEquals(converted.length, 2, "Double array to Object[][] conversion failed.");
        assertEquals(converted[0].length, 1);
        assertEquals(converted[0][0], doubles[0]);
        assertEquals(converted[1][0], doubles[1]);

        Double[] doublesAsObject = { 123.4d, 1.234e2 };
        converted = DataProviderHelper.convertToObjectArray(doublesAsObject);
        assertEquals(converted.length, 2, "Double object array to Object[][] conversion failed.");
        assertEquals(converted[0].length, 1);
        assertEquals(converted[0][0], doublesAsObject[0]);
        assertEquals(converted[1][0], doublesAsObject[1]);

        long[] ssns = { 999_99_9999L, 4000000L, 123456789L };
        converted = DataProviderHelper.convertToObjectArray(ssns);
        assertEquals(converted.length, 3, "Long array to Object[][] conversion failed.");
        assertEquals(converted[0].length, 1);
        assertEquals(converted[0][0], ssns[0]);
        assertEquals(converted[1][0], ssns[1]);
        assertEquals(converted[2][0], ssns[2]);

        float[] constants = { 3.14f, 9.807f };
        converted = DataProviderHelper.convertToObjectArray(constants);
        assertEquals(converted.length, 2, "Float array to Object[][] conversion failed.");
        assertEquals(converted[0].length, 1);
        assertEquals(converted[0][0], constants[0]);
        assertEquals(converted[1][0], constants[1]);

        byte[] bytes = { 10, 100 };
        converted = DataProviderHelper.convertToObjectArray(bytes);
        assertEquals(converted.length, 2, "Bytes array to Object[][] conversion failed.");
        assertEquals(converted[0].length, 1);
        assertEquals(converted[0][0], bytes[0]);
        assertEquals(converted[1][0], bytes[1]);

        Object[] objects = { new Address("First street"), new Address("Second street") };
        converted = DataProviderHelper.convertToObjectArray(objects);
        assertEquals(converted.length, 2, "Typed Object array to Object[][] conversion failed.");
        assertEquals(converted[0].length, 1);

    }

    @Test(groups = "unit")
    public void testGetAllDataMultipleArgs() {
        Object[][] data = null;

        Object[][] dividends = { { 2 }, { 12 }, { 5 }, { 7 } };
        Object[][] divisors = { { 1 }, { 3 }, { 5 }, { 8 } };
        Object[][] quotientsExpected = { { 2 }, { 4 }, { 1 }, { 0 } };

        data = DataProviderHelper.getAllDataMultipleArgs(dividends, divisors, quotientsExpected);

        assertNotNull(data);

        assertEquals(data.length, 4);
        assertEquals(data[0].length, 3);
        assertEquals((int) data[2][2], 1);
    }

    @Test(groups = "unit")
    public void testGetDataByIndex() throws DataProviderException {
        Object[][] input = { { 2 }, { 12 }, { 4 }, { 7 }, { 6 }, { 8 }, { 5 }, { 8 } };
        String indexes = "1, 3, 5-6";

        Object[][] data = DataProviderHelper.getDataByIndex(input, indexes);

        assertNotNull(data);
        assertEquals(data.length, 4);
        assertEquals(data[0][0], 2);
        assertEquals(data[1][0], 4);
        assertEquals(data[2][0], 6);
        assertEquals(data[3][0], 8);
    }

    @Test(groups = "unit")
    public void testGetDataByIndexList() {
        Object[][] input = { { 2 }, { 12 }, { 4 }, { 7 }, { 6 }, { 8 }, { 5 }, { 8 } };
        List<Integer> indexes = Arrays.asList(1, 3, 5, 6);

        Object[][] data = DataProviderHelper.getDataByIndexList(input, indexes);

        assertNotNull(data);
        assertEquals(data.length, 4);
        assertEquals(data[0][0], 2);
        assertEquals(data[1][0], 4);
        assertEquals(data[2][0], 6);
        assertEquals(data[3][0], 8);
    }

    @Test(groups = "unit")
    public void testGetDataByKeys() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("tom", 1f);
        map.put("1", "One");
        map.put("actor", new Date());

        String[] keys = { "tom", "actor" };
        Object[][] objArray = DataProviderHelper.getDataByKeys(map, keys);

        assertNotNull(objArray);
        assertEquals(objArray[0].length, 1);
    }

}