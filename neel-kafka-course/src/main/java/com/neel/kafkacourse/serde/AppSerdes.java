/*
 * Copyright (c) 2019. Prashant Kumar Pandey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package com.neel.kafkacourse.serde;

import com.neel.kafkacourse.types.*;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import java.util.HashMap;
import java.util.Map;


public class AppSerdes extends Serdes {

    static final class AdOutSchemaSerde extends WrapperSerde<AdOutSchema> {
        AdOutSchemaSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<AdOutSchema> AdOutSchema() {
        AdOutSchemaSerde serde = new AdOutSchemaSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, AdOutSchema.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class RegionsSerde extends WrapperSerde<Regions> {
        RegionsSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<Regions> Regions() {
        RegionsSerde serde = new RegionsSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, Regions.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class CustomersSerde extends WrapperSerde<Customers> {
        CustomersSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<Customers> Customers() {
        CustomersSerde serde = new CustomersSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, Customers.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class AdTxnSerde extends WrapperSerde<AdTxn> {
        AdTxnSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<AdTxn> AdTxn() {
        AdTxnSerde serde = new AdTxnSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, AdTxn.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class PosInvoiceSerde extends WrapperSerde<PosInvoice> {
        PosInvoiceSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<PosInvoice> PosInvoice() {
        PosInvoiceSerde serde = new PosInvoiceSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, PosInvoice.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class AdClickSerde extends WrapperSerde<AdClick> {
        AdClickSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<AdClick> AdClick() {
        AdClickSerde serde = new AdClickSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, AdClick.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class AdInventoriesSerde extends WrapperSerde<AdInventories> {
        AdInventoriesSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<AdInventories> AdInventories() {
        AdInventoriesSerde serde = new AdInventoriesSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, AdInventories.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class AdOutSerde extends WrapperSerde<AdOut> {
        AdOutSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<AdOut> AdOut() {
        AdOutSerde serde = new AdOutSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, AdOut.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class EmployeesSerde extends WrapperSerde<Employees> {
        EmployeesSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<Employees> Employees() {
        EmployeesSerde serde = new EmployeesSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, Employees.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class NotificationSerde extends WrapperSerde<Notification> {
        NotificationSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<Notification> Notification() {
        NotificationSerde serde = new NotificationSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, Notification.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class HadoopRecordSerde extends WrapperSerde<HadoopRecord> {
        HadoopRecordSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<HadoopRecord> HadoopRecord() {
        HadoopRecordSerde serde = new HadoopRecordSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, HadoopRecord.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }
}
