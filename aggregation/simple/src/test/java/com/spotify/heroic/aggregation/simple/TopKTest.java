package com.spotify.heroic.aggregation.simple;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spotify.heroic.aggregation.Aggregation;
import com.spotify.heroic.aggregation.AggregationInstance;
import com.spotify.heroic.test.FakeModuleLoader;
import java.util.Optional;
import org.junit.Test;

public class TopKTest {
    private final FakeModuleLoader m = FakeModuleLoader.builder().module(Module.class).build();

    @Test
    public void serialization() throws Exception {
        final ObjectMapper mapper = m.json();

        assertEquals(new TopK(1, Optional.empty()),
            mapper.readValue(m.jsonObject().put("type", TopK.NAME).put("k", 1).string(),
                Aggregation.class));

        assertEquals(new TopKInstance(1),
            mapper.readValue(m.jsonObject().put("type", TopK.NAME).put("k", 1).string(),
                AggregationInstance.class));
    }
}
