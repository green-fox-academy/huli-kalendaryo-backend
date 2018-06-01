package com.greenfoxacademy.kalendaryo;


import com.greenfoxacademy.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.kalendaryo.service.KalendarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
/*@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:clear.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:clear.sql"),
})*/
public class KalendarServiceIntegrationTest {

    @InjectMocks
    KalendarService kalendarService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    private KalendarFromAndroid generateModelFromName(String name) {
        KalendarFromAndroid kalendarFromAndroid = new KalendarFromAndroid();
        kalendarFromAndroid.setCustomName(name);
        return kalendarFromAndroid;
    }

    @Test
    public void setKalendarAttribute_customNameProvided() throws ValidationException {
        String customName = "Hello";
        KalendarFromAndroid kalendarFromAndroid = generateModelFromName(customName);

        Kalendar actualKalendar = kalendarService.setKalendarAttribute(new Kalendar(), kalendarFromAndroid, "");

        assertEquals(customName, actualKalendar.getName());
    }

}
